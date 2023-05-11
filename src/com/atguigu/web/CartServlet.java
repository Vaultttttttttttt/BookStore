package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.iml.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-25 14:16
* @version 1.0
*/

public class CartServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    /**
    * @description: 加入购物车
    * @param: request
     * @param: response
    * @return: void
    * @author wxj27
    * @date: 2023-03-25 14:21
    */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book=bookService.queryBookById(id);
        request.getSession().setAttribute("lastBook",book.getName());
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        // 重定向回原来商品所在的地址页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if(cart!=null){
            cart.delete(id);
        }

        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void cleanItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart !=null){
            cart.clean();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        int count=WebUtils.parseInt(request.getParameter("count"),1);
        if(cart !=null){
            if(count==0){
                cart.delete(id);
            }else {
                cart.updateCount(id, count);
            }
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * @description: 加入购物车
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-25 14:21
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book=bookService.queryBookById(id);
        request.getSession().setAttribute("lastBook",book.getName());
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson=new Gson();
        String json=gson.toJson(resultMap);
        response.getWriter().write(json);

    }
}

