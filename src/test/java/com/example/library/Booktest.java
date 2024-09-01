package com.example.library;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void testBookCreation() {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 1925, 1);
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("9780743273565", book.getIsbn());
        assertEquals(1925, book.getPublicationYear());
        assertEquals(1, book.getCopies().size());
        assertFalse(book.getCopies().get(0).isBorrowed());
        System.out.println("Book created: " + book);
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book("1984", "George Orwell", "9780451524935", 1949, 1);
        book.borrow();
        assertTrue(book.getCopies().get(0).isBorrowed());
        System.out.println("Book borrowed: " + book);
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("To Kill a Mockingbird", "Harper Lee", "9780060935467", 1960, 1);
        book.borrow();
        book.returnBook();
        assertFalse(book.getCopies().get(0).isBorrowed());
        System.out.println("Book returned: " + book);
    }

    @Test
    public void testReportDamage() {
        Book book = new Book("Moby Dick", "Herman Melville", "9781503280786", 1851, 1);
        Book.Copy copy = book.getCopies().get(0);
        copy.reportDamage();
        assertTrue(copy.isDamaged());
        System.out.println("Damage reported for: " + book);
    }

    @Test
    public void testReserve() {
        Book book = new Book("Reserved Book", "Author", "123456789", 2021, 1);
        book.reserve();
        assertTrue(book.getCopies().get(0).isReserved());
        System.out.println("Book reserved: " + book);
    }

    @Test
    public void testCancelReservation() {
        Book book = new Book("Book to Cancel Reservation", "Author", "987654321", 2021, 1);
        book.reserve();
        book.getCopies().get(0).cancelReservation();
        assertFalse(book.getCopies().get(0).isReserved());
        System.out.println("Reservation canceled for: " + book);
    }

    // @Test
    // public void testCalculateFine() {
    //     Book book = new Book("Fine Book", "Author", "1234567890", 2021, 1);
    //     Book.Copy copy = book.getCopies().get(0);
    //     copy.borrow();
    //     copy.setDueDate(LocalDate.now().minusDays(10)); // Simulate overdue by 10 days
    //     copy.returnCopy();

    //     // Debug: Print the due date, return date, and calculated fine
    //     System.out.println("Due Date: " + copy.getDueDate());
    //     System.out.println("Return Date: " + LocalDate.now());
    //     double fine = book.calculateFine(copy);
    //     System.out.println("Calculated Fine: " + fine);

    //     assertTrue(fine > 0);
    //     assertEquals(10, fine); // Assuming finePerDay is 1.0 and overdue by 10 days
    // }

    @Test
    public void testGetAvailableCopies() {
        Book book = new Book("Available Copies Book", "Author", "0000000000", 2021, 3);
        List<Book.Copy> availableCopies = book.getAvailableCopies();
        assertEquals(3, availableCopies.size()); // All copies should be available initially
        System.out.println("Available copies: " + availableCopies.size());
    }

    @Test
    public void testGetReservedCopies() {
        Book book = new Book("Reserved Copies Book", "Author", "1111111111", 2021, 2);
        book.reserve();
        List<Book.Copy> reservedCopies = book.getReservedCopies();
        assertEquals(1, reservedCopies.size()); // One copy should be reserved
        System.out.println("Reserved copies: " + reservedCopies.size());
    }
}
