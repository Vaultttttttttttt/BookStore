package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.iml.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-18 9:48
* @version 1.0
*/

public class ClientBookServlet extends BaseServlet{
    BookService bookService=new BookServiceImpl();
    /**
     * @description:处理分页功能
     * @param: request
     * @param: response
     * @return: void
     * @author wxj27
     * @date: 2023-03-14 9:37
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);


        Page<Book> page= bookService.page(pageNo,pageSize);

        page.setUrl("client/bookServlet?action=page");

        request.setAttribute("page",page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(request.getParameter("min"),0);
        int max=WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);

        Page<Book> page= bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");

        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }

        page.setUrl(sb.toString());

        request.setAttribute("page",page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }



}
