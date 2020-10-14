package com.hhr.dao.impl;

import com.hhr.dao.OrderDao;
import com.hhr.pojo.Order;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    /**
     * 保存订单
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order` (orderId,createTime,price,`status`,userId)VALUES(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    /**
     * 查询全部订单
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        String sql = "SELECT * FROM `order`";
        return queryForList(Order.class,sql);
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     */
    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "UPDATE `order` SET `status`=? WHERE orderId=?";
        update(sql,status,orderId);
    }

    /**
     * 通过用户id查找订单信息
     * @param userId
     * @return
     */
    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "SELECT * FROM `order` WHERE userId=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryForPageItemsByUserId(Integer begin, Integer pageSize,Integer userId) {
        String sql = "SELECT * FROM `order` where userId=? LIMIT ?,?";
        return queryForList(Order.class,sql,userId,begin,pageSize);
    }

    @Override
    public Integer queryOrderCountByUserId(Integer userId) {
        String sql = "SELECT COUNT(*) FROM `order` WHERE userId=?";
        Number value = (Number) queryForSingleValue(sql,userId);
        return value.intValue();
    }

    @Override
    public List<Order> queryForPageItems(Integer begin, Integer pageSize) {
        String sql = "SELECT * FROM `order` LIMIT ?,?";
        return queryForList(Order.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryOrderCount() {
        String sql = "SELECT COUNT(*) FROM `order`";
        Number value = (Number) queryForSingleValue(sql);
        return value.intValue();
    }
}
