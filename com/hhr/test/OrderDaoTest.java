package com.hhr.test;

import com.hhr.dao.OrderDao;
import com.hhr.dao.impl.OrderDaoImpl;
import com.hhr.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author qyk
 * @date 2020:09:26
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("111",new Date(),new BigDecimal(100),0,22));

    }
}