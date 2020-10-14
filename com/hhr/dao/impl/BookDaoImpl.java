package com.hhr.dao.impl;

import com.hhr.dao.BookDao;
import com.hhr.pojo.Book;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO book(`id` , `name` , `author` , `price` , `sales` , `stock` , `imgPath`)VALUES(?,?,?,?,?,?,?)";
        long id = System.currentTimeMillis() / 1000;
        return update(sql, id, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "DELETE FROM book WHERE id=?";
        return update(sql, id);
    }

    @Override
    public Book queryForOne(Integer id) {
        String sql = "select * from book where id=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryForList() {
        String sql = "select * from book";
        return queryForList(Book.class, sql);
    }

    @Override
    public int update(Book book) {
        String sql = "UPDATE book SET NAME=?,author=?,price=?,sales=?,stock=?,imgPath=? WHERE id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from book";
//        Number value = (Number) queryForSingleValue(sql);
//        return value.intValue();

        Number value = (Number) queryForSingleValue(sql);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer getSales(Integer id) {
        String sql = "select sales from book where id=?";
        Number number = (Number) queryForSingleValue(sql,id);
        return number.intValue();
    }

    @Override
    public Integer getStock(Integer id) {
        String sql = "select stock from book where id=?";
        Number number = (Number) queryForSingleValue(sql,id);
        return number.intValue();
    }

    @Override
    public void setSales(Integer id,Integer sales) {
        String sql = "update book set sales=? where id=?";
        update(sql,sales,id);
    }

    @Override
    public void setStock(Integer id,Integer stock) {
        String sql = "update book set stock=? where id=?";
        update(sql,stock,id);
    }
}
