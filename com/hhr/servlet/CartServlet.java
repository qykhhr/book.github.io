package com.hhr.servlet;

import com.hhr.pojo.Book;
import com.hhr.pojo.CartItem;
import com.hhr.pojo.Page;
import com.hhr.pojo.User;
import com.hhr.service.BookService;
import com.hhr.service.CartItemService;
import com.hhr.service.impl.BookServiceImpl;
import com.hhr.service.impl.CartItemServiceImpl;
import com.hhr.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:24
 */
public class CartServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();
    CartItemService cartItemService = new CartItemServiceImpl();

    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("商品编号：" + req.getParameter("id"));
        //1、获取请求的编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        User login = (User) req.getSession().getAttribute("login");

        //调用bookService.queryById(id)得到图书的信息
        Book book = bookService.queryBookForId(id);
        //2、把图书信息，转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),login.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        cartItemService.add(cartItem);
        req.setAttribute("cartItem",cartItem);
        //重定向回商品列表页面
//        resp.sendRedirect(req.getContextPath());
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * 处理购物车的分页需求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        User login = (User) req.getSession().getAttribute("login");

        //2.调用bookService.page(pageNo,pageSize);返回Page对象
        //数据
        Page<CartItem> page = cartItemService.pageByUserId(pageNo, 4,login.getId());
        BigDecimal sumPrice = cartItemService.getSumPrice();
        Integer sumCount = cartItemService.getSumCount();
        req.setAttribute("sumPrice",sumPrice);
        req.setAttribute("sumCount",sumCount);
        //3.保存Page对象到request域中
        page.setUrl("cartServlet?action=page");
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req,resp);
    }

    /**
     * 删除指定商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        User login = (User) req.getSession().getAttribute("login");

        cartItemService.delete(id,login.getId());

        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User login = (User) req.getSession().getAttribute("login");

        cartItemService.clear(login.getId());
        req.getRequestDispatcher("/cartServlet?action=page").forward(req,resp);
    }

    /**
     * 修改购物车商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        cartItemService.update();
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        User login = (User) req.getSession().getAttribute("login");

        cartItemService.update(id,count,login.getId());
        req.getRequestDispatcher("cartServlet?action=page").forward(req,resp);
    }
}
