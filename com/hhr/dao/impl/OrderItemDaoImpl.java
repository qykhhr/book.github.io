package com.hhr.dao.impl;

import com.hhr.dao.OrderItemDao;
import com.hhr.pojo.Order;
import com.hhr.pojo.OrderItem;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO orderItem (`name`,`count`,`price`,totalPrice,orderId)VALUES(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public OrderItem queryOrderItemByOrderId(String orderId) {
        String sql = "SELECT * FROM orderItem WHERE orderId=?";
        return queryForOne(OrderItem.class,sql,orderId);
    }

}
