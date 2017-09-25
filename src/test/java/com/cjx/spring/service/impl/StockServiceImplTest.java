package com.cjx.spring.service.impl;

import com.cjx.spring.dto.Exposer;
import com.cjx.spring.dto.SecKillExecution;
import com.cjx.spring.entity.Stock;
import com.cjx.spring.exception.RepeatKillException;
import com.cjx.spring.exception.SecKillCloseException;
import com.cjx.spring.exception.SecKillException;
import com.cjx.spring.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class StockServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockService stockService;

    @Test
    public void getStockList() throws Exception {
        List<Stock> stockList = stockService.getStockList();
        System.out.println(stockList);
        Date dateTime = new Date();
        System.out.println(dateTime);
    }

    @Test
    public void getStockById() throws Exception {
        Stock stock = stockService.getStockById(1001l);
        System.out.println(stock);
    }

    @Test
    public void exportSecKillUrl() throws Exception {
        //一个不存在的 id
        long skId1 = 2000l;
        Exposer exposer1 = stockService.exportSecKillUrl(skId1);
        System.out.println(exposer1);

        long skId2 = 1001l;
        Exposer exposer2 = stockService.exportSecKillUrl(skId2);
        System.out.println(exposer2);

    }

    @Test
    public void executeSecKill() throws Exception {
        long skId = 1001l;
        long userPhone = 15012345678l;
        String md5 = stockService.exportSecKillUrl(skId).getMd5();
        System.out.println(md5);
//        SecKillExecution secKillExecution1 =
//                stockService.executeSecKill(skId,userPhone,null);
        try {

            SecKillExecution secKillExecution2 =
                    stockService.executeSecKill(skId,userPhone,md5);
            System.out.println(secKillExecution2);

        }catch (RepeatKillException e1){
            logger.error(e1.getMessage(),e1);
        }catch (SecKillCloseException e2){
            logger.error(e2.getMessage(),e2);
        }catch (SecKillException e3){
            logger.error(e3.getMessage(),e3);
        }
    }

}