package com.hhr.servlet;


import com.hhr.pojo.BaseBook;
import com.hhr.pojo.Book;
import com.hhr.pojo.Page;
import com.hhr.service.BaseBookService;
import com.hhr.service.BookService;
import com.hhr.service.impl.BaseBookServiceImpl;
import com.hhr.service.impl.BookServiceImpl;
import com.hhr.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
    private BaseBookService baseBookService = new BaseBookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize);返回Page对象
//        Page<Book> page = bookService.page(pageNo, pageSize);
        Page<BaseBook> page = baseBookService.page(pageNo, pageSize);
        //3.保存Page对象到request域中
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);
        //4.请求转发到 pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

        /**
         * 查询所有图书
         *
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有图书信息
//        List<Book> books = bookService.queryBooks();
        List<BaseBook> books = baseBookService.queryBaseBooks();
        //保存到request域中
        req.setAttribute("books", books);
        //请求转发到"/pages/manager/book_manager.jsp"页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 添加图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo = Integer.MAX_VALUE;
        //1.获取请求的参数==》封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        BaseBook baseBook = WebUtils.copyParamToBean(req.getParameterMap(), new BaseBook());
        //2.调用bookService.addBook()方法保存图书
        bookService.addBook(book);
        baseBookService.addBaseBook(baseBook);

        //3.跳到图书页面
        //manager/bookServlet?action=list
        //请求转发的"/"表示到工程名
        //请求重定向"/"表示端口号
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 修改图书信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数封装成JavaBean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        BaseBook baseBook = WebUtils.copyParamToBean(req.getParameterMap(), new BaseBook());
        //2.调用bookService.updateBook( book ); 修改图书信息
        bookService.updateBook(book);
        baseBookService.updateBaseBook(baseBook);
        //3.重定向回图书列表管理页面
        //地址：/工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 删除图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        bookService.deleteBookById(id);//删除
        baseBookService.deleteBaseBookById(id);//管理员删除底层书籍
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }


    /**
     * 通过图书编号获取图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookForId(id);
        //3.保存图书到request域中
        req.setAttribute("book",book);
        //3.请求转发到: pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
}