
package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.util.JDBCUtil;

@Service
public class JDBCService {

	private int userId;

	public boolean registerUser(User user) {
		boolean flag = false;
		int rowsInserted = 0;
		String sql = "INSERT INTO userdetails (name, email, username, password) VALUES (?, ?, ?, ?)";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());

			System.out.println(rowsInserted);
			rowsInserted = preparedStatement.executeUpdate();
			System.out.println(rowsInserted);
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public int loginUser(User user) {
		String sql = "SELECT * FROM userdetails WHERE username = ? AND password = ?";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            if (resultSet.next()) {
		                userId =  resultSet.getInt("user_Id");
		            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
			userId = -1;
		}
		System.out.println(userId);
		return userId;
	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM book"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					int bookId = resultSet.getInt("bookId");

					String title = resultSet.getString("title");

					String author = resultSet.getString("author");

					String genre = resultSet.getString("genre");
					
					Double price = resultSet.getDouble("price");
					
					Boolean availability = resultSet.getBoolean("availability");


					Book book = new Book(bookId, title, author, genre, price,  availability);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return books;
	}
	
	 
	
	public List<Book> findByGenre(String genre) {
		System.out.println(genre);
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM book WHERE genre = ?"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, genre);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					int bookId = resultSet.getInt("bookId");

					String title = resultSet.getString("title");

					String author = resultSet.getString("author");

					String genre1 = resultSet.getString("genre");
					
					Double price = resultSet.getDouble("price");
					
					Boolean availability = resultSet.getBoolean("availability");

					Book book = new Book(bookId, title, author, genre1, price, availability);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return books;
	}
	
	public boolean checkAvailability(int id) {
		Boolean availability = true;
		String sql = "SELECT availability FROM book where bookId = ?"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					
					availability = resultSet.getBoolean("availability");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return availability;
	}

	public boolean addToCart(int id,int userId) {
		boolean flag = false;
		int rowsInserted = 0;
		System.out.println(checkAvailability(id));
		if(checkAvailability(id) != false) {
			String sql = "INSERT INTO cart (bookId,userId) VALUES (?, ?)";

			try (Connection connection = JDBCUtil.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, userId);
				

				System.out.println(rowsInserted);
				rowsInserted = preparedStatement.executeUpdate();
				System.out.println(rowsInserted);
				if (rowsInserted > 0) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				flag = false; // Other SQL error occurred
			}
			return flag;
		}
		else {
			return false;
		}
	}
	
	
	public List<Book> viewCart(int userId) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT b.*\r\n"
				+ "FROM book b\r\n"
				+ "INNER JOIN cart c ON b.bookId = c.bookId\r\n"
				+ "WHERE c.userId = ?"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, userId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					int bookId = resultSet.getInt("bookId");

					String title = resultSet.getString("title");

					String author = resultSet.getString("author");

					String genre = resultSet.getString("genre");
					
					Double price = resultSet.getDouble("price");
					
					Boolean availability = resultSet.getBoolean("availability");

					Book book = new Book(bookId, title, author, genre, price, availability);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return books;
	}

	public Boolean removeFromCart(int bookId, int userId) {
		boolean flag = false;
		int rowsInserted = 0;
		System.out.println("user" + userId+ " book" + bookId);
		String sql = "DELETE FROM cart\r\n"
				+ "WHERE bookId = ? "
				+ "AND userId = ? ";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, bookId);
			preparedStatement.setInt(2, userId);
			
			rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public boolean registerAdmin(Admin admin) {
		boolean flag = false;
		int rowsInserted = 0;
		String sql = "INSERT INTO admin (name, email, username, password) VALUES (?, ?, ?, ?)";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, admin.getName());
			preparedStatement.setString(2, admin.getEmail());
			preparedStatement.setString(3, admin.getUsername());
			preparedStatement.setString(4, admin.getPassword());

			System.out.println(rowsInserted);
			rowsInserted = preparedStatement.executeUpdate();
			System.out.println(rowsInserted);
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public boolean loginAdmin(User user) {
		String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean removeBookFromStore(int bookId) {
		boolean flag = false;
		int rowsInserted = 0;
		String sql = "DELETE FROM book\r\n"
				+ "WHERE bookId = ? ";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, bookId);
			
			rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public Boolean addToStore(String title, String author, String genre, double price, String availability) {
		boolean flag = false;
		int rowsInserted = 0;
		String sql = "INSERT INTO book (title,author,genre,price, availability) VALUES (?, ?, ?, ?,?)";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, genre);
			preparedStatement.setDouble(4, price);
			preparedStatement.setString(5, availability);
			

			System.out.println(rowsInserted);
			rowsInserted = preparedStatement.executeUpdate();
			System.out.println(rowsInserted);
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM userdetails"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					User user = new User();
	                user.setUserId(resultSet.getInt("user_id"));
	                user.setName(resultSet.getString("name"));
	                user.setEmail(resultSet.getString("email"));
	                user.setUsername(resultSet.getString("username"));
					users.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return users;
	}


	public Boolean removeUsers(int userId) {
		boolean flag = false;
		int rowsInserted = 0;
		System.out.println(userId);
		String sql = "DELETE FROM userdetails\r\n"
				+ "WHERE user_Id = ? ";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, userId);
			
			rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT * FROM category"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					int categoryId = resultSet.getInt("categoryId");

					String category = resultSet.getString("category");
					Category c = new Category(categoryId, category);
					categories.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return categories;
	}

	public Boolean removeCategoryFromStore(int categoryId) {
		boolean flag = false;
		int rowsInserted = 0;
		String sql = "DELETE FROM category\r\n"
				+ "WHERE categoryId = ? ";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, categoryId);
			
			rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public Boolean addCategoryInStore(String category) {
		boolean flag = false;
		int rowsInserted = 0;
		int userId = 5;
		String sql = "INSERT INTO category (category) VALUES (?)";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, category);
			

			System.out.println(rowsInserted);
			rowsInserted = preparedStatement.executeUpdate();
			System.out.println(rowsInserted);
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public Boolean removeAllFromCart(int userId) {
		boolean flag = false;
		int rowsInserted = 0;
		String sql = "DELETE FROM cart WHERE userId = ?";

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, userId);
			
			rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false; // Other SQL error occurred
		}
		return flag;
	}

	public List<String> getCategory() {
		
		List<String> category = new ArrayList<>();
		String sql = "SELECT category from category"; // Assuming your table name is "book"

		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String categorys = resultSet.getString("category");

					category.add(categorys);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or log it as needed
		}

		return category;
	}
}
