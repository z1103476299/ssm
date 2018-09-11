package com.ssm.service;

import com.ssm.dto.Exposer;
import com.ssm.dto.SeckillExecution;
import com.ssm.entity.Seckill;
import com.ssm.exception.RepeatKillException;
import com.ssm.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger  = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
       List<Seckill> list = seckillService.getSeckillList();
       for (Seckill se:list){
           System.out.println("---------内容-------------");
           System.out.println(se);
           System.out.println("---------内容-------------");
       }
    }

    @Test
    public void getById() {
        Seckill seckill = seckillService.getById(1000);
        System.out.println("---------内容-------------");
        System.out.println(seckill);
        System.out.println("---------内容-------------");
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        System.out.println("---------内容-------------");
        System.out.println(exposer);
        System.out.println("---------内容-------------");
        //Exposer{
        // exposed=true,
        // md5='10c898dabb5ba2afc92d22cc9d29cc2d',
        // seckillId=1000,
        // now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 1350202021;
        String md5 ="10c898dabb5ba2afc92d22cc9d29cc2d";
        try {
            SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
            System.out.println("---------内容-------------");
            System.out.println(execution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }

        //{seckillId=1000,
        // state=1,
        // stateInfo='秒杀成功',
        // successKilled=
        // SuccessKilled{seckilled=0, userPhone=1350202021, state=0, createTime=Mon Sep 10 20:54:25 CST 2018}}
    }
}