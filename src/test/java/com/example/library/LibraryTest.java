package com.example.library;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {

    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Brave New World", "Aldous Huxley");
        library.addBook(book);
        List<Book> books = library.getBooks();
        assertEquals(1, books.size());
        assertEquals("Brave New World", books.get(0).getTitle());
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book("Fahrenheit 451", "Ray Bradbury");
        library.addBook(book);
        library.borrowBook("Fahrenheit 451");
        assertTrue(book.isBorrowed());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("Dune", "Frank Herbert");
        library.addBook(book);
        library.borrowBook("Dune");
        library.returnBook("Dune");
        assertFalse(book.isBorrowed());
    }

    @Test
    public void testViewAvailableBooks() {
        Book book1 = new Book("Ender's Game", "Orson Scott Card");
        Book book2 = new Book("Neuromancer", "William Gibson");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> availableBooks = library.getAvailableBooks();
        assertEquals(2, availableBooks.size());
        assertTrue(availableBooks.contains(book1));
        assertTrue(availableBooks.contains(book2));
    }
}
