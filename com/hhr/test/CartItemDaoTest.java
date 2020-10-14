package com.hhr.test;

import com.hhr.dao.CartItemDao;
import com.hhr.dao.impl.CartItemDaoImpl;
import com.hhr.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author qyk
 * @date 2020:09:25
 */
public class CartItemDaoTest {

    CartItemDao cartItemDao = new CartItemDaoImpl();

    @Test
    public void pageTotal(){
        Integer integer = cartItemDao.pageTotal();
        System.out.println(integer);
    }
    @Test
    public void queryCartItems() {
        List<CartItem> cartItems = cartItemDao.queryCartItems();
        for (CartItem cartItem : cartItems){
            System.out.println(cartItem);
        }
    }

    @Test
    public void queryCartItemById() {
        CartItem cartItem = cartItemDao.queryCartItemById(1);
        System.out.println(cartItem);
    }

    @Test
    public void getTotalPrice() {
        BigDecimal totalPrice = cartItemDao.getTotalPrice(1);
        System.out.println(totalPrice);
    }

    @Test
    public void queryTotalCount() {
        Integer integer = cartItemDao.queryTotalCount(1);
        System.out.println(integer);
    }

    @Test
    public void getSumPrice() {
        BigDecimal sumPrice = cartItemDao.getSumPrice();
        System.out.println(sumPrice);
    }

    @Test
    public void querySumCount() {
        Integer count = cartItemDao.querySumCount();
        System.out.println(count);
    }

    @Test
    public void addItem() {
        int i = cartItemDao.addItem(new CartItem(1,1, "1", 1, new BigDecimal(1), new BigDecimal(1)));
        if( i > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    @Test
    public void deleteItem() {
        int i = cartItemDao.deleteItem(1,22);
        if( i > 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    public void clear() {
        int clear = cartItemDao.clear(22);
        if( clear > 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    public void updateCount() {
        int i = cartItemDao.updateCount(1, 10,22);
        if(i > 0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    @Test
    public void queryForPageItems() {
        List<CartItem> cartItems = cartItemDao.queryForPageItems(1, 4);
        for (CartItem cartItem : cartItems){
            System.out.println(cartItem);
        }
    }
}