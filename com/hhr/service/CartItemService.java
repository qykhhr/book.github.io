package com.hhr.service;

import com.hhr.pojo.CartItem;

import javax.jnlp.IntegrationService;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:25
 */
public interface CartItemService {

    /**
     * 查询购物车中的所有数据
     * @return
     */
    public List<CartItem> quertCartItems();

    /**
     * 获取购物车总价格（）
     * @return
     */
    public BigDecimal getSumPrice();

    /**
     * 查询购物车中有多少条数据
     * @return
     */
    public Integer getSumCount();

    /**
     * 获取购物车中单一的商品的总价格
     */
    public BigDecimal getTotalPrice(Integer id);

    /**
     * 购物车商品分页展示
     */
    public <T>T page(Integer pageNo,Integer pageSize);

    /**
     * 通过用户Id将购物车商品分页展示
     */
    public <T>T pageByUserId(Integer pageNo,Integer pageSize,Integer userId);

    /**
     * 添加商品项
     * @param cartItem
     */
    public void add(CartItem cartItem);

    /**
     * 修改商品项
     * @param
     */
    public void update(Integer id,Integer count,Integer userId);

    /**
     * 删除商品项
     * @param id
     */
    public void delete(Integer id,Integer userId);

    /**
     * 清空购物车
     */
    public void clear(Integer userId);


}
