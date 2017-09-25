package com.cjx.spring.dao;

import com.cjx.spring.entity.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 配置 Spring 和 Junit 整合，Junit 启动时加载 IOC 容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉 Junit Spring 的配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class StockDaoTest {

    @Autowired
    private StockDao stockDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Stock stock = stockDao.queryById(id);
        System.out.println(stock);
    }

    @Test
    public void reduceNumber() throws Exception {

        long id = 1000;
        String dateString = "2017-10-01 12:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString);

        int flag = stockDao.reduceNumber(id,date);
        System.out.println(flag);
    }

    @Test
    public void queryAll() throws Exception {

        List<Stock> stockList = stockDao.queryAll(0,3);
        System.out.println(stockList);
    }

}