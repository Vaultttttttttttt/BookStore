package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.iml.BaseDao;
import com.atguigu.dao.iml.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"java 从入门到放弃" , "国哥" , new BigDecimal(80), 9999 , 9 , "static/img/default.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"java 从入门到放弃" , "苏珊" , new BigDecimal(80), 9999 , 9 , "static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        List<Book> list=bookDao.queryBooks();
        for(Book book:list){
            System.out.println(book);
        }
    }

    @Test
    public void queryFoPageTotalCount(){
        System.out.println(bookDao.queryFoPageTotalCount());
    };

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(8, 4);
        for(Book book:books){
            System.out.println(book);
        }
    };

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(50,80));
    };

    @Test
    public void queryForPageItemsByPrice(){
        List<Book> books = bookDao.queryForPageItemsByPrice(2, 4,50,80);
        for(Book book:books){
            System.out.println(book);
        }
    };
}