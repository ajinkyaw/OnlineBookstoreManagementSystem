package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.User;


@Service
public interface BookService {
	
	    public abstract List<Book> getAllBooks();
	    
	    public abstract List<Book> findByGenre(String genre);

		public abstract Boolean addToCart(int bookId,int userId);
		
		public abstract List<Book> viewCart(int userId);

		public abstract Boolean removeFromCart(int bookId,int userId);

		public abstract Boolean removeBookFromStore(int bookId);

		public abstract Boolean addToStore(String title, String author, String genre, Double price, String availability);

		public abstract List<User> getAllUsers();

		public abstract Boolean removeUsers(int userId);

		public abstract List<Category> getAllCategories();

		public abstract Boolean removeCategoryFromStore(int categoryId);

		public abstract Boolean addCategoryInStore(String category);

		public abstract Boolean removeAllFromCart(int userId);
	    
	    
	}

