package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.User;

import java.util.List;

@Service("BookServiceImpl")
public class BookServiceImpl implements BookService {
    @Autowired
    private JDBCService jdbcService;

    public List<Book> getAllBooks() {
        // Implement code to fetch all books from the database using JDBCService
        return jdbcService.getAllBooks();
    }
    
    public List<Book> findByGenre(String genre) {
        // Implement code to fetch all books from the database using JDBCService
        return jdbcService.findByGenre(genre);
    }

	@Override
	public Boolean addToCart(int id,int userId) {
		// TODO Auto-generated method stub
		return jdbcService.addToCart(id,userId);
	}

	@Override
	public List<Book> viewCart(int userId) {
		// TODO Auto-generated method stub
		return jdbcService.viewCart(userId);
	}

	@Override
	public Boolean removeFromCart(int bookId,int userId) {
		System.out.println(userId);
		// TODO Auto-generated method stub
		return jdbcService.removeFromCart(bookId, userId);
	}

	@Override
	public Boolean removeBookFromStore(int bookId) {
		// TODO Auto-generated method stub
		return jdbcService.removeBookFromStore(bookId);
	}

	@Override
	public Boolean addToStore(String title, String author, String genre, Double price, String availability) {
		// TODO Auto-generated method stub
		return jdbcService.addToStore(title, author, genre, price, availability);
	}

	@Override
	public List<User> getAllUsers() {
		return jdbcService.getAllUsers();
	}

	@Override
	public Boolean removeUsers(int userId) {
		return jdbcService.removeUsers(userId);
		
	}

	@Override
	public List<Category> getAllCategories() {
		return jdbcService.getAllCategories();
	}

	@Override
	public Boolean removeCategoryFromStore(int categoryId) {
		return jdbcService.removeCategoryFromStore(categoryId);
	}

	@Override
	public Boolean addCategoryInStore(String category) {
		return jdbcService.addCategoryInStore(category);
	}

	@Override
	public Boolean removeAllFromCart(int userId) {
		return jdbcService.removeAllFromCart(userId);
	}
    
   
}
