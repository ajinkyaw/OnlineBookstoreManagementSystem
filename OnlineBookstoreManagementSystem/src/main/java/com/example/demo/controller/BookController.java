package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public ModelAndView getAllBooks(ModelAndView model) {

		List<Book> books = bookService.getAllBooks();
		System.out.println(books);
		model.addObject("books", books);
		model.setViewName("books");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/books/byGenre")
	public ModelAndView getBooksByGenre(@RequestParam(name = "genre") String selectedGenre, ModelAndView model) {
		List<Book> books = bookService.findByGenre(selectedGenre);
		model.addObject("books", books);
		model.setViewName("books"); // This should match your JSP view name
		return model;
	}

	@PostMapping("/addToCart")
	public ModelAndView addToCart(@RequestParam("bookId") int bookId, ModelAndView model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		boolean status = bookService.addToCart(bookId, userId);
		System.out.println(status);
		List<Book> books = bookService.viewCart(userId);
			System.out.println(status);
			 if (status) {
			        model.addObject("message", "Book added to cart successfully.");
			    } else {
			        model.addObject("message", "Failed to add book to cart.");
			    }
			
			model.addObject("books", books);
			model.setViewName("my-cart");
			return model;
		
	}

	@PostMapping("/removeFromCart")
	public ModelAndView removeFromCart(@RequestParam("bookId") int bookId, ModelAndView model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		boolean status = bookService.removeFromCart(bookId, userId);
		List<Book> books = bookService.viewCart(userId);
		if (status) {
	        model.addObject("message", "Book removed from cart successfully.");
	    } else {
	        model.addObject("message", "Failed to remove book from cart.");
	    }
		model.addObject("books", books);
		model.setViewName("my-cart");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/viewCart")
	public ModelAndView viewCart(ModelAndView model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Book> books = bookService.viewCart(userId);
		System.out.println(books);
		model.addObject("books", books);
		model.setViewName("my-cart");
		return model; // This corresponds to the JSP file name
	}

	@PostMapping("/removeBookFromStore")
	public ModelAndView removeBookFromStore(@RequestParam("bookId") int bookId, ModelAndView model) {
		// Add the book with the specified bookId to the user's cart
		boolean status = bookService.removeBookFromStore(bookId);
		List<Book> books = bookService.getAllBooks();
		if (status) {
	        model.addObject("message", "Book removed from store successfully.");
	    } else {
	        model.addObject("message", "Failed to remove book from store.");
	    }
		model.addObject("books", books);
		model.setViewName("bookDataForAdmin");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/getBookData")
	public ModelAndView getBookData(ModelAndView model) {

		List<Book> books = bookService.getAllBooks();
		System.out.println(books);
		model.addObject("books", books);
		model.setViewName("bookDataForAdmin");
		return model; // This corresponds to the JSP file name
	}

	@PostMapping("/addBookInStore")
	public ModelAndView addToCart(@RequestParam("title") String title, @RequestParam("author") String author,
			@RequestParam("genre") String genre, @RequestParam("price") Double price,
			@RequestParam("availability") String availability, ModelAndView model) {
		// Add the book with the specified bookId to the user's cart
		boolean status = bookService.addToStore(title, author, genre, price, availability);
		List<Book> books = bookService.getAllBooks();
		if (status) {
	        model.addObject("message", "Book added in store successfully.");
	    } else {
	        model.addObject("message", "Failed to add book in store.");
	    }
		model.addObject("books", books);
		model.setViewName("bookDataForAdmin");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/viewUsers")
	public ModelAndView viewUsers(ModelAndView model) {

		List<User> users = bookService.getAllUsers();
		System.out.println(users);
		model.addObject("users", users);
		model.setViewName("userDataForAdmin");
		return model; // This corresponds to the JSP file name
	}

	@PostMapping("/removeUsers")
	public ModelAndView removeUsers(@RequestParam("userId") int userId, ModelAndView model) {

		boolean status = bookService.removeUsers(userId);
		System.out.println(userId);
		List<User> users = bookService.getAllUsers();
		if (status) {
	        model.addObject("message", "User removed from store.");
	    } else {
	        model.addObject("message", "Failed to remove user in store.");
	    }
		model.addObject("users", users);
		model.setViewName("userDataForAdmin");
		return model; // This corresponds to the JSP file name
	}

	@PostMapping("/removeCategoryFromStore")
	public ModelAndView removeCategoryFromStore(@RequestParam("categoryId") int categoryId, ModelAndView model) {
		// Add the book with the specified bookId to the user's cart
		boolean status = bookService.removeCategoryFromStore(categoryId);
		List<Category> categories = bookService.getAllCategories();
		if (status) {
	        model.addObject("message", "Category removed from store.");
	    } else {
	        model.addObject("message", "Failed to remove category in store.");
	    }
		model.addObject("categories", categories);
		model.setViewName("category");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/getCategoryData")
	public ModelAndView getCategoryData(ModelAndView model) {

		List<Category> categories = bookService.getAllCategories();
		System.out.println(categories);
		model.addObject("categories", categories);
		model.setViewName("category");
		return model; // This corresponds to the JSP file name
	}

	@PostMapping("/addCategoryInStore")
	public ModelAndView addCategoryInStore(@RequestParam("category") String category, ModelAndView model) {
		// Add the book with the specified bookId to the user's cart
		boolean status = bookService.addCategoryInStore(category);
		List<Category> categories = bookService.getAllCategories();
		if (status) {
	        model.addObject("message", "Category added in store.");
	    } else {
	        model.addObject("message", "Failed to add category in store.");
	    }
		model.addObject("categories", categories);
		model.setViewName("category");
		return model; // This corresponds to the JSP file name
	}

	@PostMapping("/proceedToCheckout")
	public ModelAndView proceedToCheckout(@RequestParam("totalPrice") double totalPrice) {

		ModelAndView modelAndView = new ModelAndView("checkoutConfirmation");

		System.out.println(totalPrice);
		modelAndView.addObject("orderTotal", totalPrice); // Add the total price to display in the confirmation page
		return modelAndView;
	}

//	@PostMapping("/processPayment")
//	public ModelAndView makePayment() {
//		ModelAndView modelAndView = new ModelAndView("payment"); // Specify the view name
//		return modelAndView;
//	}
	
	@PostMapping("/processPayment")
	public ModelAndView makePayment(@RequestParam("upiId") String upiId, @RequestParam("amount") double amount) {
	    ModelAndView modelAndView = new ModelAndView(); // Specify the view name

	    // Check if the UPI ID contains both "ok" and "@"
	    if (upiId.contains("ok") && upiId.contains("@")) {
	    	modelAndView.addObject("message", "Payment successful.");
	    	modelAndView.setViewName("payment");
	    } else {
	    	
	        modelAndView.addObject("message", "Invalid UPI ID format. UPI ID must contain both 'ok' and '@'.");
	        modelAndView.setViewName("failedPayment");
	    }

	    return modelAndView;
	}


	@PostMapping("/removeAllFromCart")
	public ModelAndView removeAllFromCart(ModelAndView model, HttpSession session) {
		// Add the book with the specified bookId to the user's cart
		Integer userId = (Integer) session.getAttribute("userId");
		bookService.removeAllFromCart(userId);
		List<Book> books = bookService.viewCart(userId);
		model.setViewName("userDashboard");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/logout")
	public ModelAndView logout(ModelAndView model) {

		model.setViewName("welcome");
		return model; // This corresponds to the JSP file name
	}

	@GetMapping("/userLogout")
	public ModelAndView userLogout(ModelAndView model) {

		model.setViewName("welcome");
		return model; // This corresponds to the JSP file name
	}
	
	@GetMapping("/userDashboard")
	public ModelAndView userDashboard(ModelAndView model) {

		model.setViewName("userDashboard");
		return model; // This corresponds to the JSP file name
	}
	
	@GetMapping("/adminDashboard")
	public ModelAndView adminDashboard(ModelAndView model) {

		model.setViewName("adminDashboard");
		return model; // This corresponds to the JSP file name
	}

}