package com.ssm.dao;

import com.ssm.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1000;
        long phone = 13506517948L;
        int a = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("---------内容-------------");
        System.out.println("insertCount="+a);
        System.out.println("---------内容-------------");
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1000;
        long phone = 13506517948L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println("---------内容-------------");
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        System.out.println("---------内容-------------");
    }
}