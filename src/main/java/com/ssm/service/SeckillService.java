package com.ssm.service;

import com.ssm.dto.Exposer;
import com.ssm.dto.SeckillExecution;
import com.ssm.entity.Seckill;
import com.ssm.exception.RepeatKillException;
import com.ssm.exception.SeckillCloseException;
import com.ssm.exception.SeckillException;

import java.util.List;

/**
 * 业务接口:站在"使用者"角度设计接口
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 根据id查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启输出秒杀接口地址，否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws SeckillException, SeckillCloseException, RepeatKillException;

}
