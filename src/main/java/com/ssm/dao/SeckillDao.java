package com.ssm.dao;

import com.ssm.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId 减库存的id
     * @param killTime  减库存的时间
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据ID查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     * mybatis的注解 param 告诉他实际形参是offset,limit
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
