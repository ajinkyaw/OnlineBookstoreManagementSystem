package com.example.demo.model;

//Book.java
public class Book {
 private int bookId;
 private String title;
 private String author;
 private String genre;
 private double price;
 private boolean availability;
 

 
public Book(int bookId, String title, String author, String genre, double price, boolean availability) {
	super();
	this.bookId = bookId;
	this.title = title;
	this.author = author;
	this.genre = genre;
	this.setPrice(price);
	this.availability = availability;
}
public boolean isAvailability() {
	return availability;
}
public void setAvailability(boolean availability) {
	this.availability = availability;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}


public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
@Override
public String toString() {
	return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", genre=" + genre + ", availability="
			+ availability + "]";
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
 

 // Getters and setters
}

