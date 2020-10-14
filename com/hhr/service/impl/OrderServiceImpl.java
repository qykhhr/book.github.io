package com.hhr.service.impl;

import com.hhr.dao.OrderDao;
import com.hhr.dao.OrderItemDao;
import com.hhr.dao.impl.OrderDaoImpl;
import com.hhr.dao.impl.OrderItemDaoImpl;
import com.hhr.pojo.CartItem;
import com.hhr.pojo.Order;
import com.hhr.pojo.OrderItem;
import com.hhr.pojo.Page;
import com.hhr.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItem = new OrderItemDaoImpl();

    @Override
    public void createOrder(CartItem cartItem, Integer userId) {
        String orderId = String.valueOf(System.currentTimeMillis() + userId);
        orderDao.saveOrder(new Order(orderId,new Date(),cartItem.getPrice(),0,userId));
        orderItem.saveOrderItem(new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId));
    }

    @Override
    public List<Order> showAllOrder() {
        return orderDao.queryOrders();
    }

    //发货
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public OrderItem showOrderItem(String orderId) {
        return orderItem.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrder(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    /**
     * 签收
     * @param orderId
     */
    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }

    @Override
    public Page<Order> queryForPageItemsByUserId(Integer pageNo, Integer pageSize, Integer userId) {
        Page<Order> page = new Page<>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = orderDao.queryOrderCountByUserId(userId);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);

        //设置页码
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;

        //求当前页数据
        List<Order> items = orderDao.queryForPageItemsByUserId(begin, pageSize, userId);

        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Order> queryForPageItems(Integer pageNo, Integer pageSize) {
        Page<Order> page = new Page<Order>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = orderDao.queryOrderCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);

        //设置页码
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;
        //求当前页数据
        List<Order> items = orderDao.queryForPageItems(begin, pageSize);

        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Integer queryOrderCountByUserId(Integer userId) {
        return orderDao.queryOrderCountByUserId(userId);
    }

    @Override
    public Integer queryOrderCount() {
        return orderDao.queryOrderCount();
    }


}
