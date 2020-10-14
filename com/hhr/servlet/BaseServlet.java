package com.hhr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author qyk
 * @date 2020:09:20
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码问题
        req.setCharacterEncoding("UTF-8");
        //解决相应乱码问题
        resp.setContentType("text/html;charset=UTF-8");
        //获取地址栏action参数
        String action = req.getParameter("action");


        try {
            /*
             * 客户端必须传method方法
             * 获取参数，用来识别处理方法,判断是哪一个方法，是哪一个就调用哪一个
             * 得到方法名，通过反射来调用方法, 得到当前类的Class对象,然后调用安的方法进行查询，得到Method
             * 我们要得到当前类的方法，所以需要得到当前类的Class
             * getDeclaredMethod 可以获取任意方法 像，protected修饰的
             * getMethod 只可以获取 修饰符为 public 的方法
             */
            //通过action参数鉴别字符串，通过反射获取相应的业务、方法
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用目标业务方法
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
