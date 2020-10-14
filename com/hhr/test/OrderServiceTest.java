package com.hhr.test;

import com.hhr.pojo.Order;
import com.hhr.pojo.Page;
import com.hhr.service.OrderService;
import com.hhr.service.impl.OrderServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author qyk
 * @date 2020:09:27
 */
public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
    }

    @Test
    public void showAllOrder() {
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderItem() {
    }

    @Test
    public void showMyOrder() {
    }

    @Test
    public void receiveOrder() {
    }

    @Test
    public void queryForPageItemsByUserId() {
        Page<Order> orderPage = orderService.queryForPageItemsByUserId(0, 4, 22);
        System.out.println(orderPage);
    }

    @Test
    public void queryForPageItems() {
    }

    @Test
    public void queryOrderCountByUserId() {
    }

    @Test
    public void queryOrderCount() {
    }
}