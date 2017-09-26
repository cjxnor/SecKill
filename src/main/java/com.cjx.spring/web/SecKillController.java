package com.cjx.spring.web;

import com.cjx.spring.dto.Exposer;
import com.cjx.spring.dto.SecKillExecution;
import com.cjx.spring.dto.SecKillResult;
import com.cjx.spring.entity.Stock;
import com.cjx.spring.enums.SecKillStateEnum;
import com.cjx.spring.exception.RepeatKillException;
import com.cjx.spring.exception.SecKillCloseException;
import com.cjx.spring.service.StockService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")     // url: /模块/资源/{id}/细分
public class SecKillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StockService stockService;

    //获得所有秒杀商品列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        List<Stock> stockList = stockService.getStockList();
        model.addAttribute("stockList", stockList);
        return "list";      // WEB-INF/jsp/list.jsp
    }

    // 特定 id 商品的详情
    @RequestMapping(value = "/{secId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("secId") Long secId, Model model) {

        if (secId == null) {
            return "redirect:/seckill/list";
        }

        Stock stock = stockService.getStockById(secId);
        if (stock == null) {
            return "redirect:/seckill/list";
        }
        model.addAttribute("stock", stock);

        return "detail";
    }

    // ajax ，返回 Json
    @RequestMapping(value = "/{secId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8;"})
    @ResponseBody       //表明返回值将会写入 响应体 内，而不是 model 或者 视图名
    public SecKillResult<Exposer> exposer(@PathVariable("secId") long secId) {

        SecKillResult<Exposer> secKillResult;
        // 捕获可能出现的问题
        try {
            Exposer exposer = stockService.exportSecKillUrl(secId);
            secKillResult = new SecKillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            secKillResult = new SecKillResult<Exposer>(false, e.getMessage());
        }
        return secKillResult;
    }


    //执行秒杀
    @RequestMapping(value = "/{skId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8;"})
    @ResponseBody
    public SecKillResult<SecKillExecution> execute(@PathVariable("skId") long skId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone", required = false) Long userPhone) {
        //判断 userPhone 是否为空
        if (userPhone == null) {
            return new SecKillResult<SecKillExecution>(false, "用户未注册！");
        }
        SecKillResult<SecKillExecution> secKillResult;
        //执行秒杀操作，并捕获出现的异常
        //TODO spring 全局异常？
        try {
            SecKillExecution secKillExecution = stockService.executeSecKill(skId, userPhone, md5);
            return new SecKillResult<SecKillExecution>(true, secKillExecution);
        } catch (RepeatKillException e1) {
            //重复秒杀异常
            SecKillExecution secKillExecution = new SecKillExecution(skId, SecKillStateEnum.REPEAT_KILL);
            return new SecKillResult<SecKillExecution>(true, secKillExecution);
        } catch (SecKillCloseException e2) {
            //秒杀关闭或未开启
            SecKillExecution secKillExecution = new SecKillExecution(skId, SecKillStateEnum.END);
            return new SecKillResult<SecKillExecution>(true, secKillExecution);
        } catch (Exception e) {
            //其他可能出现的异常
            e.printStackTrace();
            SecKillExecution secKillExecution = new SecKillExecution(skId, SecKillStateEnum.INNER_ERROR);
            return new SecKillResult<SecKillExecution>(true, secKillExecution);
        }
    }

    //获取系统时间
    @ResponseBody
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SecKillResult<Long> getTime() {

        Date nowTime = new Date();
        //返回时间的毫秒数
        return new SecKillResult(true,nowTime.getTime());
    }
}
