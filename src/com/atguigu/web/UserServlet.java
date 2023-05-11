package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.iml.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserServlet extends BaseServlet {
    /**
     * @description:处理登录的功能
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-04 15:25
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        if (userService.existUsername(username)) {
            User loginUser = userService.login(new User(null, username, password, null));
            if (loginUser != null) {
                request.getSession().setAttribute("user", loginUser);
                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "密码错误");
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "用户名不存在");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    /**
     * @description:处理注册的功能
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-04 15:26
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        String true_code = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        if (true_code != null && code.equalsIgnoreCase(true_code)) {
            UserService userService = new UserServiceImpl();
            if (userService.existUsername(username)) {
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                User user = new User();
                WebUtils.copyParamToBean(request, user);
                userService.registUser(user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");

        UserService userService=new UserServiceImpl();
        boolean existUsername = userService.existUsername(username);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existsUsername",existUsername);
        Gson gson=new Gson();
        String json=gson.toJson(resultMap);

        response.getWriter().write(json);
    }
}
