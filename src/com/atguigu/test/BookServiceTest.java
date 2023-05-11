package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.iml.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"java 从入门到放弃" , "国哥" , new BigDecimal(80), 9999 , 9 , "static/img/default.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"java 从入门到放弃" , "苏珊" , new BigDecimal(80), 9999 , 9 , "static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for(Book book: bookService.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        Page<Book> pages = bookService.page(2, 4);
        for(Book book:pages.getItems()){
            System.out.println(book);
        }
    };

    @Test
    public void pageByPrice(){
        Page<Book> pages=bookService.pageByPrice(1,4,50,80);
        for(Book book:pages.getItems()){
            System.out.println(book);
        }
    };
}