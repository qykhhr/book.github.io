package com.hhr.service;

import com.hhr.pojo.BaseBook;
import com.hhr.pojo.Book;
import com.hhr.pojo.Page;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public interface BaseBookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBaseBook(BaseBook book);

    /**
     * 修改图书信息
     * @param book
     */
    public void updateBaseBook(BaseBook book);

    /**
     * 通过id删除图书信息
     * @param id
     */
    public void deleteBaseBookById(Integer id);

    /**
     * 通过id查询图书信息
     * @param id
     * @return
     */
    public BaseBook queryBaseBookForId(Integer id);

    /**
     * 查询返回全部图书信息
     * @return
     */
    public List<BaseBook> queryBaseBooks();

    public Page<BaseBook> page(int pageNo, int pageSize);



    /**
     * 获取销量
     * @return
     */
    public Integer getSales(Integer id);

    /**
     * 获取库存
     * @return
     */
    public Integer getStock(Integer id);

    /**
     * 获取销量
     * @return
     */
    public void setSales(Integer id,Integer sales);

    /**
     * 获取库存
     * @return
     */
    public void setStock(Integer id,Integer stock);

}
