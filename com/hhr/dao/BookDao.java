package com.hhr.dao;

import com.hhr.pojo.Book;

import javax.jnlp.IntegrationService;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 根据书籍id删除指定书籍
     * @param id
     * @return
     */
    public int deleteBook(Integer id);

    /**
     * 根据id查找书籍，返回一条记录
     * @param id
     * @return
     */
    public Book queryForOne(Integer id);

    /**
     * 查询返回多条记录
     * @return
     */
    public List<Book> queryForList();

    /**
     * 修改图书信息
     * @param book
     * @return
     */
    public int update(Book book);

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
    List<Book> queryForPageItems(int begin, int pageSize);

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
