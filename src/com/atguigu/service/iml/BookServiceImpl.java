package com.atguigu.service.iml;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.iml.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-05 14:40
* @version 1.0
*/

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<>();

        //设置每页数量
        page.setPageSize(pageSize);
        //设置总数量
        Integer pageTotalCount=bookDao.queryFoPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //设置总页码
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize!=0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置页码
        page.setPageNo(pageNo);
        //获取当前页数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();
        //设置每页数量
        page.setPageSize(pageSize);
        //设置总数量
        Integer pageTotalCount=bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //设置总页码
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize!=0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置页码
        page.setPageNo(pageNo);
        //获取当前页数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
