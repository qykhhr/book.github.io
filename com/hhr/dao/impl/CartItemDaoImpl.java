package com.hhr.dao.impl;

import com.hhr.dao.CartItemDao;
import com.hhr.pojo.CartItem;
import com.hhr.utils.WebUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:25
 */
public class CartItemDaoImpl extends BaseDao implements CartItemDao {

    @Override
    public Integer pageTotal() {
        String sql = "select count(*) from cartItem";
        Number value = (Number) queryForSingleValue(sql);
        return value.intValue();
    }

    @Override
    public List<CartItem> queryCartItems() {
        String sql = "select * from cartItem";
        return queryForList(CartItem.class,sql);
    }

    @Override
    public CartItem queryCartItemById(Integer id) {
        String sql = "select * from cartItem where id=?";
        return queryForOne(CartItem.class,sql,id);
    }


    @Override
    public BigDecimal getTotalPrice(Integer id) {
        String sql1 = "select count from cartItem where id=?";
        String sql2 = "select price from cartItem where id=?";
        Integer count = (Integer) queryForSingleValue(sql1, id);
        BigDecimal price = (BigDecimal) queryForSingleValue(sql2, id);
        return price.multiply(new BigDecimal(count));
    }

    @Override
    public int addItem(CartItem cartItem) {
        String sql = "INSERT INTO cartItem(id,userId,`name`,`count`,price,totalPrice)VALUES(?,?,?,?,?,?)";
        return update(sql,cartItem.getId(),cartItem.getUserId(),cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice());
    }

    @Override
    public int deleteItem(Integer id,Integer userId) {
        String sql = "delete from cartItem where id=? and userId=?";
        return update(sql, id,userId);
    }

    @Override
    public int clear(Integer userId) {
        String sql = "DELETE FROM cartItem where userId=?";
        return update(sql,userId);
    }

    @Override
    public int updateCount(Integer id, Integer count,Integer userId) {
        if(count <= 0){
            return deleteItem(id,userId);
        }else{
            String sql = "update cartItem set count=?,totalPrice=? where id=? and userId=?";
            CartItem cartItem = queryCartItemById(id);
            BigDecimal price = cartItem.getPrice();
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            return update(sql,count,totalPrice,id,userId);
        }
    }

    @Override
    public Integer queryTotalCount(Integer id) {
        String sql = "select count from cartItem where id=?";
        Number value = (Number) queryForSingleValue(sql,id);
        return value.intValue();
    }

    @Override
    public BigDecimal getSumPrice() {
        String sql = "select sum(totalPrice) from cartItem";
        return (BigDecimal) queryForSingleValue(sql);
    }

    @Override
    public Integer querySumCount() {
        String sql = "SELECT SUM(`count`) FROM cartItem";
        Number value = (Number) queryForSingleValue(sql);
        String strValue = String.valueOf(value);
        return WebUtils.parseInt(strValue, 0);
    }

    @Override
    public List<CartItem> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from cartItem limit ?,?";
        return queryForList(CartItem.class,sql,begin,pageSize);
    }

    @Override
    public List<CartItem> queryForPageItemsByUserId(Integer begin, Integer pageSize, Integer userId) {
        String sql = "select * from cartItem where userId=? limit ?,?";

        return queryForList(CartItem.class,sql,userId,begin,pageSize);
    }
}
