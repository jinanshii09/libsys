package com.example.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void testBookCreation() {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertFalse(book.isBorrowed());
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book("1984", "George Orwell");
        book.borrow();
        assertTrue(book.isBorrowed());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("To Kill a Mockingbird", "Harper Lee");
        book.borrow();
        book.returnBook();
        assertFalse(book.isBorrowed());
    }

    @Test
    public void testReturnNotBorrowedBook() {
        Book book = new Book("Moby Dick", "Herman Melville");
        book.returnBook();
        assertFalse(book.isBorrowed());
    }
}
