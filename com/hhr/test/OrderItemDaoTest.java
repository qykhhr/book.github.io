package com.hhr.test;

import com.hhr.dao.OrderItemDao;
import com.hhr.dao.impl.OrderItemDaoImpl;
import com.hhr.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author qyk
 * @date 2020:09:26
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"111"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通",2,new BigDecimal(100),new BigDecimal(200),"111"));
        orderItemDao.saveOrderItem(new OrderItem(null,"C++",1,new BigDecimal(100),new BigDecimal(100),"111"));

    }
}