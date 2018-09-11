package com.ssm.service.impl;

import com.ssm.dao.SeckillDao;
import com.ssm.dao.SuccessKilledDao;
import com.ssm.dto.Exposer;
import com.ssm.dto.SeckillExecution;
import com.ssm.entity.Seckill;
import com.ssm.entity.SuccessKilled;
import com.ssm.enums.SeckillStatEnum;
import com.ssm.exception.RepeatKillException;
import com.ssm.exception.SeckillCloseException;
import com.ssm.exception.SeckillException;
import com.ssm.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeckillDao seckillDao;

    @Resource
    //@Autowired, @Resource,@Inject 都可
    private SuccessKilledDao successKilledDao;
    //用户混淆Md5
    private final String slat ="abcd2134156@qaqs.cs442";
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill==null){
            return new Exposer(false,seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }


    private String getMd5(Long seckillId){
        String base = seckillId+"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    @Override
    @Transactional
    /**
     * 使用注解控制事务方法的优点:
     * 1:开发团队达成一致约定,明确标注事务方法的编程风格
     * 2:保证事务方法的执行时间尽可能短
     * 3:不是所有的方法都需要事务
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5==null||!md5.equals(getMd5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
            try {
                Date nowTime = new Date();
                //减少库存
                int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
                if (updateCount <= 0 ){
                    //秒杀结束
                    throw new SeckillCloseException("seckill closed");
                }else {
                    //记录购买行为
                    int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                    if (insertCount <= 0) {
                        //重复秒杀
                        throw new RepeatKillException("seckill repeat");
                    } else {
                        //秒杀成功
                        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                        return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                    }
                }
            }catch (SeckillCloseException e1){
                throw  e1;
            }catch (RepeatKillException e2) {
                throw e2;
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                //所有编译异常转化为运行期异常
                throw new SeckillException("seckill inner error:"+e.getMessage());
            }
        }
}

