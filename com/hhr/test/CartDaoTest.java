package com.hhr.test;

import com.hhr.dao.CartItemDao;
import com.hhr.dao.impl.CartItemDaoImpl;
import com.hhr.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author qyk
 * @date 2020:09:25
 */
public class CartDaoTest {
    CartItemDao cartDao = new CartItemDaoImpl();
    @Test
    public void addItem() {
        int i = cartDao.addItem(new CartItem(1,1, "1", 1, new BigDecimal(1), new BigDecimal(1)));
        cartDao.addItem(new CartItem(2, 1,"1", 1, new BigDecimal(1), new BigDecimal(1)));
        if(i > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    @Test
    public void deleteItem() {
        int i = cartDao.deleteItem(1,1);
        if(i > 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    public void clear() {
        int clear = cartDao.clear(1);
        if(clear > 0){
            System.out.println("清空成功");
        }else{
            System.out.println("清空失败");
        }
    }

    @Test
    public void updateCount() {
        int i = cartDao.updateCount(1, 100,22);
        if(i > 0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
}