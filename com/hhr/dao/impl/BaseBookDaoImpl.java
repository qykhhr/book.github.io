package com.hhr.dao.impl;

import com.hhr.dao.BaseBookDao;
import com.hhr.dao.BookDao;
import com.hhr.pojo.BaseBook;
import com.hhr.pojo.Book;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class BaseBookDaoImpl extends BaseDao implements BaseBookDao {
    @Override
    public int addBaseBook(BaseBook book) {
        String sql = "INSERT INTO baseBook(`id` , `name` , `author` , `price` , `sales` , `stock` , `imgPath`)VALUES(?,?,?,?,?,?,?)";
        long id = System.currentTimeMillis() / 1000;
        return update(sql, id, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBaseBook(Integer id) {
        String sql = "DELETE FROM baseBook WHERE id=?";
        return update(sql, id);
    }

    @Override
    public BaseBook queryForOne(Integer id) {
        String sql = "select * from baseBook where id=?";
        return queryForOne(BaseBook.class, sql, id);
    }

    @Override
    public List<BaseBook> queryForList() {
        String sql = "select * from baseBook";
        return queryForList(BaseBook.class, sql);
    }

    @Override
    public int update(BaseBook book) {
        String sql = "UPDATE baseBook SET NAME=?,author=?,price=?,sales=?,stock=?,imgPath=? WHERE id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from baseBook";
//        Number value = (Number) queryForSingleValue(sql);
//        return value.intValue();

        Number value = (Number) queryForSingleValue(sql);
        return value.intValue();
    }

    @Override
    public List<BaseBook> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from baseBook limit ?,?";
        return queryForList(BaseBook.class,sql,begin,pageSize);
    }

    @Override
    public Integer getSales(Integer id) {
        String sql = "select sales from baseBook where id=?";
//        return (Integer) queryForSingleValue(sql, id);

        Number number = (Number) queryForSingleValue(sql,id);
        return number.intValue();
    }

    @Override
    public Integer getStock(Integer id) {
        String sql = "select stock from baseBook where id=?";
        Number number = (Number) queryForSingleValue(sql,id);
        return number.intValue();
    }

    @Override
    public void setSales(Integer id,Integer sales) {
        String sql = "update baseBook set sales=? where id=?";
        update(sql,sales,id);
    }

    @Override
    public void setStock(Integer id,Integer stock) {
        String sql = "update baseBook set stock=? where id=?";
        update(sql,stock,id);
    }
}
