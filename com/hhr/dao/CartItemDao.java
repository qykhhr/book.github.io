package com.hhr.dao;

import com.hhr.pojo.CartItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:25
 */
public interface CartItemDao {

    /**
     * 查询购物车表中有几条不重复的数据
     * @return
     */
    public Integer pageTotal();

    /**
     * 查询全部图书信息
     * @return
     */
    public List<CartItem> queryCartItems();

    /**
     * 通过id查询商品信息
     * @return
     */
    public CartItem queryCartItemById(Integer id);

    /**
     * 获取一种商品总价格（）
     * @return
     */
    public BigDecimal getTotalPrice(Integer id);

    /**
     * 查询购物车中有多少数据
     * @return
     */
    public Integer queryTotalCount(Integer id);


    /**
     * 获取购物车总价格（）
     * @return
     */
    public BigDecimal getSumPrice();

    /**
     * 查询购物车中有多少数据
     * @return
     */
    public Integer querySumCount();


    /**
     * 添加商品项
     * @param cartItem
     * @return
     */
    public int addItem(CartItem cartItem);

    /**
     * 删除商品项
     * @param id
     * @return
     */
    public int deleteItem(Integer id,Integer userId);

    /**
     * 清空购物车
     * @return
     */
    public int clear(Integer userId);

    /**
     * 修改商品数量
     * @param id
     * @param count
     * @return
     */
    public int updateCount(Integer id, Integer count,Integer userId);



    /**
     * 返回展示页面的数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<CartItem> queryForPageItems(int begin, int pageSize);

    public List<CartItem> queryForPageItemsByUserId(Integer begin,Integer pageSize,Integer userId);
}
