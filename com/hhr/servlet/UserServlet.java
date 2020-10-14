package com.hhr.servlet;

import com.hhr.pojo.User;
import com.hhr.service.UserService;
import com.hhr.service.impl.UserServiceImpl;
import com.hhr.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author qyk
 * @date 2020:09:19
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    /**
     * 注销用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁Session中的用户登录信息（或者销毁session）
        req.getSession().invalidate();
        //2.重定向回首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }
        /**
         * 登录业务
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User login = userService.login(username,password);
        if(login != null){
            boolean manager = userService.isManager(username);
            if(manager){
                //登录成功
                //使用session域保存登录成功后的信息
                req.getSession().setAttribute("loginManagerUsername",username);

                req.getRequestDispatcher("/pages/manager/manager_success.jsp").forward(req,resp);
            }else{
                //登录成功
                //使用session域保存登录成功后的信息
                req.getSession().setAttribute("login",login);

                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            }
        }else{
            req.setAttribute("msg","用户名和密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 注册业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        boolean existsUsername = userService.existsUsername(username);

        if(token!=null && token.equalsIgnoreCase(code)){
            if(!existsUsername){
                userService.regist(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }else{
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.setAttribute("code",code);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        }else{

            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}
