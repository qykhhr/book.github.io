package com.hhr.dao;

import com.hhr.pojo.BaseBook;
import com.hhr.pojo.Book;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public interface BaseBookDao {
    /**
     * 添加图书
     * @param baseBook
     * @return
     */
    public int addBaseBook(BaseBook baseBook);

    /**
     * 根据书籍id删除指定书籍
     * @param id
     * @return
     */
    public int deleteBaseBook(Integer id);

    /**
     * 根据id查找书籍，返回一条记录
     * @param id
     * @return
     */
    public BaseBook queryForOne(Integer id);

    /**
     * 查询返回多条记录
     * @return
     */
    public List<BaseBook> queryForList();

    /**
     * 修改图书信息
     * @param baseBook
     * @return
     */
    public int update(BaseBook baseBook);

    /**
     * 查询购物车有多少数据
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询返回当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<BaseBook> queryForPageItems(int begin, int pageSize);

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
