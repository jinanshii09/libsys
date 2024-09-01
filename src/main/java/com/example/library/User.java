/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 package com.example.library;

 import java.util.ArrayList;
 import java.util.List;
 
 public class User {
     private final String username;
     private final List<Book> borrowedBooks;
     private final List<Book> reservedBooks;
     private double totalFines;
 
     public User(String username) {
         this.username = username;
         this.borrowedBooks = new ArrayList<>();
         this.reservedBooks = new ArrayList<>();
         this.totalFines = 0.0;
     }
 
     public String getUsername() {
         return username;
     }
 
     public void borrowBook(Book book) {
         if (borrowedBooks.size() >= 5) {
             System.out.println("User has reached the maximum limit of borrowed books.");
             return;
         }
         
         List<Book.Copy> availableCopies = book.getAvailableCopies();
         if (!availableCopies.isEmpty()) {
             Book.Copy copy = availableCopies.get(0);
             copy.borrow();
             borrowedBooks.add(book);
             System.out.println(username + " borrowed " + book.getTitle());
         } else {
             System.out.println("No available copies of " + book.getTitle() + " to borrow.");
         }
     }
 
     public void returnBook(Book book) {
         if (borrowedBooks.remove(book)) {
             book.returnBook();
             System.out.println(username + " returned " + book.getTitle());
         } else {
             System.out.println(username + " does not have " + book.getTitle() + " borrowed.");
         }
     }
 
     public void reserveBook(Book book) {
         List<Book.Copy> availableCopies = book.getAvailableCopies();
         if (!availableCopies.isEmpty()) {
             Book.Copy copy = availableCopies.get(0);
             copy.reserve();
             reservedBooks.add(book);
             System.out.println(username + " reserved " + book.getTitle());
         } else {
             System.out.println("No available copies of " + book.getTitle() + " to reserve.");
         }
     }
 
     public void cancelReservation(Book book) {
         if (reservedBooks.remove(book)) {
             book.cancelReservation();
             System.out.println(username + " cancelled reservation for " + book.getTitle());
         } else {
             System.out.println(username + " does not have a reservation for " + book.getTitle());
         }
     }
 
     public List<Book> getBorrowedBooks() {
         return new ArrayList<>(borrowedBooks);
     }
 
     public List<Book> getReservedBooks() {
         return new ArrayList<>(reservedBooks);
     }
 
     public double getTotalFines() {
         return totalFines;
     }
 
     public void addFine(double amount) {
         totalFines += amount;
     }
 }
 