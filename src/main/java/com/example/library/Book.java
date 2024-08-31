package com.example.library;

public class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        if (isBorrowed) {
            System.out.println("This book is already borrowed.");
        } else {
            isBorrowed = true;
            System.out.println("You have borrowed the book: " + title);
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("You have returned the book: " + title);
        } else {
            System.out.println("This book wasn't borrowed.");
        }
    }

    @Override
    public String toString() {
        return title + " by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}
