package com.cn.seckill.service;

import com.cn.seckill.dao.GoodsDao;
import com.cn.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:Zhumc
 * @date: 2019/4/1
 * @return:
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    public List<GoodsVo> getList(){
        return goodsDao.findList();
    }
    public GoodsVo findOneByGoodsId(long goodsId){
        return goodsDao.findOneByGoodsId(goodsId);
    }
}
