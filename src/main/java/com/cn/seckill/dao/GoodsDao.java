package com.cn.seckill.dao;

import com.cn.seckill.domain.Goods;
import com.cn.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */
public interface GoodsDao {
    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from goods g left join miaosha_goods mg on g.id = mg.goods_id")
    List<GoodsVo> findList();
    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from goods g left join miaosha_goods mg on g.id = mg.goods_id where g.id = #{goodsId}")
    GoodsVo findOneByGoodsId(@Param("goodsId")long goodsId);
}
