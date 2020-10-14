package com.hhr.service.impl;

import com.hhr.dao.BaseBookDao;
import com.hhr.dao.BookDao;
import com.hhr.dao.impl.BaseBookDaoImpl;
import com.hhr.dao.impl.BookDaoImpl;
import com.hhr.pojo.BaseBook;
import com.hhr.pojo.Book;
import com.hhr.pojo.Page;
import com.hhr.service.BaseBookService;
import com.hhr.service.BookService;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class BaseBookServiceImpl implements BaseBookService {
    private BaseBookDao bookDao = new BaseBookDaoImpl();
    @Override
    public void addBaseBook(BaseBook book) {
        bookDao.addBaseBook(book);
    }

    @Override
    public void updateBaseBook(BaseBook book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBaseBookById(Integer id) {
        bookDao.deleteBaseBook(id);
    }

    @Override
    public BaseBook queryBaseBookForId(Integer id) {
        return bookDao.queryForOne(id);
    }

    @Override
    public List<BaseBook> queryBaseBooks() {
        return bookDao.queryForList();
    }

    @Override
    public Page<BaseBook> page(int pageNo, int pageSize) {
        Page<BaseBook> page = new Page<>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
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
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<BaseBook> items = bookDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Integer getSales(Integer id) {
        return bookDao.getSales(id);
    }

    @Override
    public Integer getStock(Integer id) {
        return bookDao.getStock(id);
    }

    @Override
    public void setSales(Integer id, Integer sales) {
        bookDao.setSales(id,sales);
    }

    @Override
    public void setStock(Integer id, Integer stock) {
        bookDao.setStock(id,stock);
    }
}
