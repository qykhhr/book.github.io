package com.hhr.test;

import com.hhr.dao.BookDao;
import com.hhr.dao.impl.BookDaoImpl;
import com.hhr.pojo.Book;
import com.hhr.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        int i = bookDao.addBook(new Book(null, "11", new BigDecimal(11), "11", 11, 11, "11"));
        if(i <= 0){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功");
        }

    }

    @Test
    public void deleteBook() {
        int i = bookDao.deleteBook(22);
        if(i <= 0){
            System.out.println("删除失败");
        }else{
            System.out.println("删除成功");
        }
    }

    @Test
    public void queryForOne() {
        Book book = bookDao.queryForOne(11);
        System.out.println(book);
    }

    @Test
    public void queryForList() {
        List<Book> books = bookDao.queryForList();
        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void update() {

        int update = bookDao.update(new Book(21, "11", new BigDecimal(1), "11", 1, 1, "1"));
        if(update <= 0){
            System.out.println("修改失败");
        }else{
            System.out.println("修改成功");
        }
    }

    @Test
    public void queryForPageTotalCount(){
        Integer integer = bookDao.queryForPageTotalCount();
        System.out.println(integer);
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(0, 4);
//        System.out.println(books);
        for (Book book : books){
            System.out.println(book);
        }
    }
}