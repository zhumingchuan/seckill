package com.cn.seckill.controller;

import com.cn.seckill.domain.MiaoshaUser;
import com.cn.seckill.service.GoodsService;
import com.cn.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2019/3/28.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/to_list")
    public String goods(Model model, MiaoshaUser user){
       model.addAttribute("user",user);
        List<GoodsVo> list = goodsService.getList();
        model.addAttribute("goodsList",list);
        return "goods_list";
    }
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user, @PathVariable("goodsId")Long goodsId){
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findOneByGoodsId(goodsId);
        model.addAttribute("goods",goodsVo);
        long start = goodsVo.getStartDate().getTime();
        long ends = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < start){
            //秒杀活动还没开始,倒计时
            model.addAttribute("miaoshaStatus",0);
            model.addAttribute("remainSeconds",(now - start)/1000);
        }else if (now >ends){
            //秒杀活动已结束
            model.addAttribute("miaoshaStatus",2);
            model.addAttribute("remainSeconds",-1);
        }else{
            model.addAttribute("miaoshaStatus",1);
            model.addAttribute("remainSeconds",0);
        }
        return "goods_detail";
    }
}
