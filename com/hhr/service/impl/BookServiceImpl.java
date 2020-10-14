package com.hhr.service.impl;

import com.hhr.dao.BookDao;
import com.hhr.dao.impl.BookDaoImpl;
import com.hhr.pojo.Book;
import com.hhr.pojo.Page;
import com.hhr.service.BookService;

import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public Book queryBookForId(Integer id) {
        return bookDao.queryForOne(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryForList();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

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
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
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
