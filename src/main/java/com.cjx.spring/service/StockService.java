package com.cjx.spring.service;

import com.cjx.spring.dto.Exposer;
import com.cjx.spring.dto.SecKillExecution;
import com.cjx.spring.entity.Stock;
import com.cjx.spring.exception.RepeatKillException;
import com.cjx.spring.exception.SecKillCloseException;
import com.cjx.spring.exception.SecKillException;

import java.util.List;

/**
 * 业务接口，接口的设计应站在“使用者”角度
 * 方法定义的粒度，参数，返回类型（return 类型/异常）
 */
public interface StockService {

    /**
     * 返回所有秒杀记录
     * @return
     */
    List<Stock> getStockList();

    /**
     * 返回一个秒杀记录
     * @param skId
     * @return
     */
    Stock getStockById(long skId);

    /**
     * 秒杀开启时输出秒杀接口的地址，
     * 否则输出系统时间和秒杀时间
     * @param skId
     */
    Exposer exportSecKillUrl(long skId);

    /**
     * 执行秒杀操作
     * @param skId
     * @param userPhone
     * @param md5
     */
    SecKillExecution executeSecKill(Long skId, Long userPhone, String md5)
    throws SecKillException,SecKillCloseException,RepeatKillException;
}
