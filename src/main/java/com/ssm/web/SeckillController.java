package com.ssm.web;

import com.ssm.dto.Exposer;
import com.ssm.dto.SeckillExecution;
import com.ssm.dto.SeckillResult;
import com.ssm.entity.Seckill;
import com.ssm.enums.SeckillStatEnum;
import com.ssm.exception.RepeatKillException;
import com.ssm.exception.SeckillCloseException;
import com.ssm.exception.SeckillException;
import com.ssm.service.SeckillService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")//模块
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
        List<Seckill> list =  seckillService.getSeckillList();
        model.addAttribute("list",list);
        //list.jsp + model = ModelAndView
        return "list";// /WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill==null){
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    //ajax接口 返回json
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable Long seckillId){
        SeckillResult<Exposer> result ;

        try{
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            e.printStackTrace();
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,
    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")long seckillId,
     @PathVariable("md5") String md5,@CookieValue(value = "killPhone",required = false) Long phone){

       if (phone==null){
           return new SeckillResult<SeckillExecution>(false,"未注册");
       }
        SeckillResult<SeckillExecution> result;
        try{
            SeckillExecution execution = seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (RepeatKillException e){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return  new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.END);
            return  new SeckillResult<SeckillExecution>(true,execution);
        }catch (Exception e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.INNER_ERROR);
            return  new SeckillResult<SeckillExecution>(true,execution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){

        Date now = new Date();
        return new SeckillResult(true,now.getTime());

    }
}
