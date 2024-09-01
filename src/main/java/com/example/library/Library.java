package com.example.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    private final List<User> users;
    private final List<Donation> donations;
    private final List<UserActivity> userActivities;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        donations = new ArrayList<>();
        userActivities = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book);
    }

    public void donateBook(Book book) {
        books.add(book);
        donations.add(new Donation(book, LocalDate.now()));
        System.out.println("Donated book: " + book);
    }

    public void addUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("User already exists.");
                return;
            }
        }
        users.add(new User(username));
        userActivities.add(new UserActivity(username));
        System.out.println("Added user: " + username);
    }

    public void borrowBook(String username, String title) {
        User user = findUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.borrow();
                user.borrowBook(book);
                UserActivity activity = findUserActivity(username);
                if (activity != null) {
                    activity.logBorrowing(book);
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String username, String title) {
        User user = findUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                user.returnBook(book);
                UserActivity activity = findUserActivity(username);
                if (activity != null) {
                    activity.logReturning(book);
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void reserveBook(String username, String title) {
        User user = findUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.reserve();
                user.reserveBook(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void cancelReservation(String username, String title) {
        User user = findUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.cancelReservation();
                user.cancelReservation(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void checkOverdueBooks() {
        for (Book book : books) {
            for (Book.Copy copy : book.getCopies()) {
                if (copy.isOverdue()) {
                    System.out.println("Overdue book: " + book.getTitle() + ", Due Date: " + copy.getDueDate());
                }
            }
        }
    }

    public void generateReport() {
        System.out.println("Library Report:");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + getAvailableBooks().size());
        checkOverdueBooks();
        for (User user : users) {
            System.out.println("User: " + user.getUsername() + " | Borrowed Books: " + ((List<Book>) user.getBorrowedBooks()).size());
        }
        for (Donation donation : donations) {
            System.out.println("Donation: " + donation.getBook().getTitle() + ", Date: " + donation.getDate());
        }
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.getAvailableCopies().isEmpty()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private UserActivity findUserActivity(String username) {
        for (UserActivity activity : userActivities) {
            if (activity.getUsername().equals(username)) {
                return activity;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public static class Donation {
        private final Book book;
        private final LocalDate date;

        public Donation(Book book, LocalDate date) {
            this.book = book;
            this.date = date;
        }

        public Book getBook() {
            return book;
        }

        public LocalDate getDate() {
            return date;
        }
    }

    public static class UserActivity {
        private final String username;
        private final List<String> activities;

        public UserActivity(String username) {
            this.username = username;
            this.activities = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public List<String> getActivities() {
            return activities;
        }

        public void logBorrowing(Book book) {
            activities.add("Borrowed: " + book.getTitle() + " on " + LocalDate.now());
        }

        public void logReturning(Book book) {
            activities.add("Returned: " + book.getTitle() + " on " + LocalDate.now());
        }

    }
}
