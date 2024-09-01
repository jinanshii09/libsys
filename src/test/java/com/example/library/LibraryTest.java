package com.example.library;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
        library.addBook(new Book("Brave New World", "Aldous Huxley", "9780060850524", 1932, 3));
        library.addBook(new Book("1984", "George Orwell", "9780451524935", 1949, 2));
        library.addUser("Alice");
        library.addUser("Bob");
    }

    @Test
    public void testDonateBook() {
        Book book = new Book("Donated Book", "Donor", "9781234567897", 2024, 1);
        library.donateBook(book);
        assertTrue(library.getAvailableBooks().contains(book));
    }

    @Test
    public void testUserActivityLogging() {
        library.borrowBook("Alice", "Brave New World");
        List<String> activities = library.getUserActivities().stream()
                .filter(activity -> activity.getUsername().equals("Alice"))
                .findFirst()
                .orElseThrow()
                .getActivities();
        assertTrue(activities.stream().anyMatch(activity -> activity.contains("Borrowed: Brave New World")));
    }

    @Test
    public void testBookConditionReporting() {
        Book book = new Book("Damaged Book", "Author", "9789876543210", 2023, 1);
        library.addBook(book);
        Book.Copy copy = book.getCopies().get(0);
        copy.reportDamage();
        assertTrue(copy.isDamaged());
    }

    @Test
    public void testMultipleBranches() {
        Library branch1 = new Library();
        Library branch2 = new Library();

        Book bookBranch1 = new Book("Book Branch 1", "Author", "9781111111111", 2000, 1);
        branch1.addBook(bookBranch1);

        Book bookBranch2 = new Book("Book Branch 2", "Author", "9782222222222", 2001, 1);
        branch2.addBook(bookBranch2);

        assertTrue(branch1.getAvailableBooks().contains(bookBranch1));
        assertTrue(branch2.getAvailableBooks().contains(bookBranch2));
    }

    @Test
    public void testSearchBooksByTitle() {
        List<Book> books = library.getAvailableBooks().stream().filter(b -> b.getTitle().contains("Brave")).toList();
        assertEquals(1, books.size());
        assertEquals("Brave New World", books.get(0).getTitle());
    }

    @Test
    public void testSearchBooksByAuthor() {
        List<Book> books = library.getAvailableBooks().stream().filter(b -> b.getAuthor().contains("Orwell")).toList();
        assertEquals(1, books.size());
        assertEquals("1984", books.get(0).getTitle());
    }

    @Test
    public void testGenerateReport() {
        library.generateReport();
        // Manually verify the output or use additional assertions if you have report parsing logic
    }

    // @Test
    // public void testCancelReservation() {
    //     // Setup for reservation
    //     library.reserveBook("Alice", "Brave New World");
    //     Book book = library.getAvailableBooks().stream()
    //             .filter(b -> b.getTitle().equals("Brave New World"))
    //             .findFirst()
    //             .orElseThrow();
    //     assertFalse(book.getReservedCopies().isEmpty());

    //     // Cancel reservation
    //     library.cancelReservation("Alice", "Brave New World");
    //     assertTrue(book.getReservedCopies().isEmpty());
    // }

    @Test
    public void testCheckOverdueBooks() {
        // Borrow a book to make it overdue
        Book book = new Book("Overdue Book", "Author", "9783333333333", 2024, 1);
        library.addBook(book);
        Book.Copy copy = book.getCopies().get(0);
        copy.borrow();
        copy.setDueDate(LocalDate.now().minusDays(10)); // Simulate overdue

        // Check overdue books
        library.checkOverdueBooks(); // Output should include the overdue book
    }

    // @Test
    // public void testFineCalculation() {
    //     Book book = new Book("Fine Book", "Author", "9784444444444", 2024, 1);
    //     library.addBook(book);
    //     library.borrowBook("Alice", "Fine Book");
    //     Book.Copy copy = book.getCopies().get(0);
    //     copy.borrow(); // Borrow the copy
    //     copy.setDueDate(LocalDate.now().minusDays(10)); // Simulate overdue
    //     copy.returnCopy(); // Return the copy

    //     // Calculate fine
    //     double fine = book.calculateFine(copy);
    //     assertTrue(fine > 0, "Fine should be greater than 0 for overdue books.");
    //     assertEquals(10.0, fine, "Fine should be equal to the number of overdue days times the fine per day.");
    // }

    @Test
    public void testAddUser() {
        library.addUser("Charlie");
        assertTrue(library.getUsers().stream().anyMatch(user -> user.getUsername().equals("Charlie")));
    }

    @Test
    public void testAddDuplicateUser() {
        library.addUser("Alice"); // Should not add duplicate user
        assertEquals(2, library.getUsers().size()); // Only 2 unique users should be present
    }
}
