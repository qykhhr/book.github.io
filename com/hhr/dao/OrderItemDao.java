package com.hhr.dao;

import com.hhr.pojo.Order;
import com.hhr.pojo.OrderItem;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);


    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    public OrderItem queryOrderItemByOrderId(String orderId);

}
