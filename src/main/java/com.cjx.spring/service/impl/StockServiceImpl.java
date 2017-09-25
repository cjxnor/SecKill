package com.cjx.spring.service.impl;

import com.cjx.spring.dao.StockDao;
import com.cjx.spring.dao.SuccessKilledDao;
import com.cjx.spring.dto.Exposer;
import com.cjx.spring.dto.SecKillExecution;
import com.cjx.spring.entity.Stock;
import com.cjx.spring.entity.SuccessKilled;
import com.cjx.spring.enums.SecKillStateEnum;
import com.cjx.spring.exception.RepeatKillException;
import com.cjx.spring.exception.SecKillCloseException;
import com.cjx.spring.exception.SecKillException;
import com.cjx.spring.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * StockService 实现类
 */
@Service
public class StockServiceImpl implements StockService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // md5 盐值字符串(随机的字符串)，用于混淆 md5
    private final String salt = "jglanglka-23184%&)(*@)!*_@)#￥…&*！@#";

    // dao 依赖注入，由 mybatis 实现的 dao
    @Autowired
    private StockDao stockDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Stock> getStockList() {
        return stockDao.queryAll(0, 4);
    }

    public Stock getStockById(long skId) {
        return stockDao.queryById(skId);
    }

    /**
     * 秒杀开启时输出秒杀接口的地址，
     * 否则输出系统时间和秒杀时间
     *
     * @param skId
     */
    public Exposer exportSecKillUrl(long skId) {
        Stock stock = stockDao.queryById(skId);
        //判断商品信息是否为空
        if (stock == null) {
            return new Exposer(false, skId);
        }

        //当前时间
        long nowTime = new Date().getTime();
        //秒杀开始时间
        long startTime = stock.getStartTime().getTime();
        //秒杀结束时间
        long endTime = stock.getEndTime().getTime();
        //判断秒杀还未开始 或者 已经结束
        if (startTime > nowTime || endTime < nowTime) {
            //返回 商品 id, 秒杀开启、结束时间和现在的时间
            return new Exposer(false, skId, nowTime, startTime, endTime);
        }
        String md5 = this.getMd5(skId);
        return new Exposer(true, md5, skId);
    }

    /**
     * 执行秒杀操作
     *
     * @param skId
     * @param userPhone
     * @param md5
     */
    @Transactional
    public SecKillExecution executeSecKill(Long skId, Long userPhone, String md5)
            throws SecKillException, SecKillCloseException, RepeatKillException {

        // 捕获所有异常，包括数据库超时，或者数据库连接断
        try {
            if (md5 == null || !md5.equals(this.getMd5(skId))) {
                throw new SecKillException("Seckill data rewrite!");
            }
            //执行秒杀逻辑：减库存 和 记录购买行为
            Date nowDate = new Date();
            //减库存
            int updateCount = stockDao.reduceNumber(skId, nowDate);
            if (updateCount <= 0) {
                //没有更新到记录，秒杀结束或者秒杀未开始或者库存不足
                //TODO 上述三种情况还不能区分 （在高并发情况下库存不方便读取？）
                throw new SecKillCloseException("Seckill closed!");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(skId, userPhone);
                if (insertCount <= 0) {
                    //插入失败，代表重复秒杀
                    throw new RepeatKillException("Repeat kill!");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithStock(skId, userPhone);
                    return new SecKillExecution(skId, SecKillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SecKillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            //所有编译期异常转化为运行期异常，这样事务就能回滚
            throw new SecKillException("Seckill inner error " + e.getMessage());
        }
    }

    //根据 skId 和 salt 生成 md5 字符串
    private String getMd5(long skId) {
        String base = skId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
