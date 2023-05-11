package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.iml.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author wxj27
 * @version 1.0
 * @description: TODO
 * @date 2023-03-26 10:08
 */

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * @description: 生成订单
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-26 10:10
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }

        Integer id = user.getId();

        String orderId = orderService.createOrder(cart, id);

        //        request.setAttribute("orderId", orderId);
        //        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);

        request.getSession().setAttribute("orderId", orderId);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * @description: 查看所有订单
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-26 11:15
     */
    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * @description: 查看我的订单
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-27 9:10
     */
    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        Integer id = user.getId();
        List<Order> orders = orderService.showMyOrders(id);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    /**
     * @description: 查看订单详情
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-27 16:36
     */
    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String userId=request.getParameter("userId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("orderId", orderId);
        request.setAttribute("userId",userId);
        request.getRequestDispatcher("/pages/order/orderItem.jsp").forward(request, response);
    }

    /**
     * @description: 管理员发货
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-27 18:06
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        orderService.sendOrder(orderId);
        request.getRequestDispatcher("/orderServlet?action=showAllOrders").forward(request,response);
    }

    /**
     * @description: 用户确认收货
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-27 18:06
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        orderService.receiveOrder(orderId);
        request.getRequestDispatcher("orderServlet?action=showMyOrders").forward(request,response);
    }
}
