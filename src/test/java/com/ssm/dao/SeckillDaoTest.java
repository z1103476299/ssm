package com.ssm.dao;

import com.ssm.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合,junit启动是加载spring-ioc容器
 * spring-test,junt
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入dao实现依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println("---------内容-------------");
        System.out.println(seckill.getName());
        System.out.println(seckill);
        System.out.println("---------内容-------------");
    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0,2);
        for (Seckill sec :seckills){
            System.out.println("---------内容-------------");
            System.out.println(sec);
            System.out.println("---------内容-------------");
        }
    }

    @Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("---------内容-------------");
        System.out.println("updateCount="+updateCount);
        System.out.println("---------内容-------------");

    }

}