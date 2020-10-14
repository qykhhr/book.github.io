package com.hhr.servlet;

import com.hhr.dao.OrderDao;
import com.hhr.pojo.*;
import com.hhr.service.BaseBookService;
import com.hhr.service.BookService;
import com.hhr.service.CartItemService;
import com.hhr.service.OrderService;
import com.hhr.service.impl.BaseBookServiceImpl;
import com.hhr.service.impl.BookServiceImpl;
import com.hhr.service.impl.CartItemServiceImpl;
import com.hhr.service.impl.OrderServiceImpl;
import com.hhr.utils.WebUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:26
 */
public class OrderServlet extends BaseServlet {

    private CartItemService cartItemService = new CartItemServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private BaseBookService baseBookService = new BaseBookServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CartItem> cartItems = cartItemService.quertCartItems();

        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);

        for (CartItem cartItem : cartItems) {
            Integer id = cartItem.getId();
            Integer count = cartItem.getCount();


            Integer sales = baseBookService.getSales(id);
            Integer stock = baseBookService.getStock(id);

            if(stock >= count){

                System.out.println("库存足够");

                orderService.createOrder(cartItem, userId);
                sales += count;
                stock -= count;

                cartItemService.delete(id,userId);

                baseBookService.setSales(id, sales);
                baseBookService.setStock(id, stock);

                bookService.setSales(id, sales);
                bookService.setStock(id, stock);
            }else{
                System.out.println("库存不足");

                //当库存不足时，就不会生成订单，
                cartItemService.delete(id,userId);
                resp.sendRedirect(req.getContextPath() + "/pages/cart/createOrder_failed.jsp");
            }

        }

        resp.sendRedirect(req.getContextPath() + "/pages/cart/createOrder_success.jsp");
    }


    protected void pageOrderByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User login = (User) req.getSession().getAttribute("login");

        //1.获取请求的参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        Integer countByUserId = orderService.queryOrderCountByUserId(login.getId());
///////////////////////////

        //2.调用bookService.page(pageNo,pageSize);返回Page对象
        Page<Order> page = orderService.queryForPageItemsByUserId(pageNo, 4, login.getId());

        BigDecimal sumPrice = new BigDecimal(0);
        List<Order> items = page.getItems();
        for (Order order : items){
            sumPrice = orderService.showOrderItem(order.getOrderId()).getTotalPrice();
        }
        req.setAttribute("sumPrice", sumPrice);


        //3.保存Page对象到request域中
        page.setUrl("orderServlet?action=pageOrderByUserId");
        req.setAttribute("page", page);

        req.setAttribute("countByUserId", countByUserId);

        req.getRequestDispatcher("/pages/cart/order_user.jsp").forward(req, resp);

    }

    protected void showOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String orderId = req.getParameter("orderId");
            OrderItem orderItem = orderService.showOrderItem(orderId);
            req.setAttribute("orderItem",orderItem);
            req.getRequestDispatcher("/pages/cart/orderItem_user.jsp").forward(req,resp);
    }


    protected void pageOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        Integer count = orderService.queryOrderCount();



        //2.调用bookService.page(pageNo,pageSize);返回Page对象
        Page<Order> page = orderService.queryForPageItems(pageNo, 4);


        BigDecimal sumPrice = new BigDecimal(0);
        List<Order> items = page.getItems();
        for (Order order : items){
            sumPrice = orderService.showOrderItem(order.getOrderId()).getTotalPrice();
        }
        req.setAttribute("sumPrice", sumPrice);

        //3.保存Page对象到request域中
        page.setUrl("orderServlet?action=pageOrder");

        req.setAttribute("page", page);
        req.setAttribute("items", items);

        req.setAttribute("countByUserId", count);

        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }


    /**
     * 用户签收
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void signOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }

}