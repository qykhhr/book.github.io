package com.hhr.service;

import com.hhr.pojo.CartItem;
import com.hhr.pojo.Order;
import com.hhr.pojo.OrderItem;
import com.hhr.pojo.Page;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public interface OrderService {

    /**
     * 创建订单
     * @param cartItem
     * @param userId
     */
    public void createOrder(CartItem cartItem, Integer userId);

    /**
     * 查询所有订单（管理员使用）
     * @return
     */
    public List<Order> showAllOrder();

    /**
     * 发货（修改订单状态）
     * @param orderId
     */
    public void sendOrder(String orderId);

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    public OrderItem showOrderItem(String orderId);

    /**
     * 查询我的订单
     * @param userId
     * @return
     */
    public List<Order> showMyOrder(Integer userId);

    /**
     * 签收订单/确认收货
     * @param orderId
     */
    public void receiveOrder(String orderId);



    /**
     * 查询指定用户订单分页的数据
     * @param
     * @param pageSize
     * @return
     */
    public Page<Order> queryForPageItemsByUserId(Integer pageNo,Integer pageSize,Integer userId);


    /**
     * 查询全部订单分页的数据（管理员）
     * @param
     * @param pageSize
     * @return
     */
    public Page<Order> queryForPageItems(Integer pageNo, Integer pageSize);

    /**
     * 返回指定用户的订单总数
     * @param id
     * @return
     */
    public Integer queryOrderCountByUserId(Integer id);

    /**
     * 返回全部用户的订单总数
     * @param
     * @return
     */
    public Integer queryOrderCount();
}
