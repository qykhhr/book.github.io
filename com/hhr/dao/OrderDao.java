package com.hhr.dao;

import com.hhr.pojo.Order;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public interface OrderDao {

    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    /**
     * 查询全部订单（管理员）
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 修改订单状态（管理员）
     * @param orderId
     * @param status
     */
    public void changeOrderStatus(String orderId,Integer status);

    /**
     * 通过用户编号查询订单信息（管理员/用户）
     * @param userId
     * @return
     */
    public List<Order> queryOrderByUserId(Integer userId);


    /**
     * 查询指定用户订单分页的数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Order> queryForPageItemsByUserId(Integer begin,Integer pageSize,Integer userId);

    /**
     * 查询指定用户的订单总数
     * @param userId
     * @return
     */
    public Integer queryOrderCountByUserId(Integer userId);


    /**
     * 查询全部订单分页的数据（管理员）
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Order> queryForPageItems(Integer begin,Integer pageSize);

    /**
     * 查询全部的订单总数（管理员）
     * @param
     * @return
     */
    public Integer queryOrderCount();

}
