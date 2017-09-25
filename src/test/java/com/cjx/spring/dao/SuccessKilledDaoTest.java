package com.cjx.spring.dao;

import com.cjx.spring.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void queryByIdWithStock() throws Exception {
        long id = 1002;
        long userPhone = 18712345678l;
        SuccessKilled successKilled = successKilledDao.queryByIdWithStock(id,userPhone);
        System.out.println(successKilled);
    }

    @Test
    public void insertSuccessKilled() throws Exception {

        long id = 1003;
        long userPhone = 18712345678l;
        int flag = successKilledDao.insertSuccessKilled(id,userPhone);
        System.out.println(flag);
    }
}