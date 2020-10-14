package com.hhr.servlet;

import com.hhr.pojo.Book;
import com.hhr.pojo.Page;
import com.hhr.service.BookService;
import com.hhr.service.impl.BookServiceImpl;
import com.hhr.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qyk
 * @date 2020:09:23
 */
public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * 处理首页分页的问题
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
        Page<Book> page = bookService.page(pageNo, pageSize);
        //3.保存Page对象到request域中
        page.setUrl("clientBookServlet?action=page");
        req.setAttribute("page",page);
        //4.请求转发到 pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
}
