package com.hhr.pojo;

import java.math.BigDecimal;

/**购物车商品项
 * @author qyk
 * @date 2020:09:24
 */
public class CartItem {
    private Integer id;//商品编号
    private Integer userId;//用户id
    private String name;//商品名字
    private Integer count;//商品数量
    private BigDecimal price;//价格（单价）
    private BigDecimal totalPrice;//总价（单个商品不同数量的总价）

    private Integer sumCount;//购物车中总共的商品数量
    private BigDecimal sumPrice;//购物车中总共商品所需的价格

    public CartItem() {
    }

    public CartItem(Integer id, Integer userId, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getSumCount() {
        return sumCount;
    }

    public void setSumCount(Integer sumCount) {
        this.sumCount = sumCount;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", sumCount=" + sumCount +
                ", sumPrice=" + sumPrice +
                '}';
    }
}
