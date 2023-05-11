package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-05 13:47
* @version 1.0
*/

public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryFoPageTotalCount();

    public List<Book> queryForPageItems(int begin, int pageSize);

    public Integer queryForPageTotalCountByPrice(int min,int max);

    public List<Book> queryForPageItemsByPrice(int begin,int size,int min,int max);
}
