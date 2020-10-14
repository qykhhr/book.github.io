package com.hhr.service.impl;

import com.hhr.dao.CartItemDao;
import com.hhr.dao.impl.CartItemDaoImpl;
import com.hhr.pojo.Book;
import com.hhr.pojo.CartItem;
import com.hhr.pojo.Page;
import com.hhr.service.BaseBookService;
import com.hhr.service.BookService;
import com.hhr.service.CartItemService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:25
 */
public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao = new CartItemDaoImpl();
    private BookService bookService = new BookServiceImpl();
    private BaseBookService baseBookService = new BaseBookServiceImpl();
    @Override
    public Page<CartItem> page(Integer pageNo, Integer pageSize) {
        Page<CartItem> page = new Page<CartItem>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = cartItemDao.pageTotal();
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
        List<CartItem> items = cartItemDao.queryForPageItems(begin,pageSize);

        //设置当前页数据
        page.setItems(items);
        return page;
    }


    @Override
    public List<CartItem> quertCartItems() {
        return cartItemDao.queryCartItems();
    }

    @Override
    public BigDecimal getSumPrice() {
        return cartItemDao.getSumPrice();
    }

    @Override
    public Integer getSumCount() {
        return cartItemDao.querySumCount();
    }

    @Override
    public BigDecimal getTotalPrice(Integer id) {
        return cartItemDao.getTotalPrice(id);
    }

    @Override
    public Page<CartItem> pageByUserId(Integer pageNo, Integer pageSize, Integer userId) {
        Page<CartItem> page = new Page<CartItem>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = cartItemDao.pageTotal();
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
        List<CartItem> items = cartItemDao.queryForPageItemsByUserId(begin,pageSize,userId);

        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public void add(CartItem cartItem) {
        CartItem cartItem1 = cartItemDao.queryCartItemById(cartItem.getId());

        int id = cartItem.getId();
        int sales = bookService.getSales(id);
        int stock = bookService.getStock(id);
        Integer userId = cartItem.getUserId();

        if(cartItem1 == null){
            if(stock > 0) {
                stock--;

                cartItemDao.addItem(cartItem);
                cartItemDao.updateCount(id, 1,userId);
                bookService.setStock(id, stock);
            }
        }else{
            int count = cartItem1.getCount();
            if(stock > 0){
                stock--;
                count++;
                cartItemDao.updateCount(id,count,userId);
                bookService.setStock(id,stock);
            }
        }

    }

    @Override
    public void update(Integer id,Integer count,Integer userId) {
        //先查看购物车中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem item = cartItemDao.queryCartItemById(id);
        if(item != null){
//            Integer stock = bookService.getStock(id);
            Integer stock = baseBookService.getStock(id);

            if(stock >= count){

                stock -= count;
                cartItemDao.updateCount(id,count,userId);
                bookService.setStock(id,stock);
            }else{
                count = stock;
                stock -= count;
                cartItemDao.updateCount(id,count,userId);
                bookService.setStock(id,stock);
            }
        }
    }

    @Override
    public void delete(Integer id,Integer userId) {
        CartItem cartItem = cartItemDao.queryCartItemById(id);
        Integer stock = bookService.getStock(id);
        Integer count = cartItem.getCount();
        Integer sumStock = stock + count;
        bookService.setStock(id,sumStock);
        cartItemDao.deleteItem(id,userId);
    }

    @Override
    public void clear(Integer userId) {

        List<CartItem> cartItems = cartItemDao.queryCartItems();
        for (CartItem cartItem : cartItems){
            Integer count = cartItem.getCount();
            Integer id = cartItem.getId();
            Integer stock = bookService.getStock(id);
            Integer sumStock = stock + count;
            bookService.setStock(id,sumStock);
        }
        cartItemDao.clear(userId);
    }
}
