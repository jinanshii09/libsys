package com.example.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int publicationYear;
    private final List<Copy> copies;
    private final double finePerDay = 1.0;

    public Book(String title, String author, String isbn, int publicationYear, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.copies = new ArrayList<>();
        for (int i = 0; i < numberOfCopies; i++) {
            copies.add(new Copy());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void borrow() {
        for (Copy copy : copies) {
            if (!copy.isBorrowed() && !copy.isReserved()) {
                copy.borrow();
                System.out.println("Book borrowed: " + this.title);
                return;
            }
        }
        throw new BookNotAvailableException("All copies of this book are currently borrowed.");
    }

    public void returnBook() {
        for (Copy copy : copies) {
            if (copy.isBorrowed()) {
                copy.returnCopy();
                System.out.println("Book returned: " + this.title);
                return;
            }
        }
        System.out.println("No borrowed copies of this book to return.");
    }

    public void reserve() {
        for (Copy copy : copies) {
            if (!copy.isBorrowed() && !copy.isReserved()) {
                copy.reserve();
                System.out.println("Book reserved: " + this.title);
                return;
            }
        }
        throw new BookNotAvailableException("All copies of this book are currently reserved.");
    }

    public double calculateFine(Copy copy) {
        if (copy.isOverdue()) {
            long daysOverdue = LocalDate.now().toEpochDay() - copy.getDueDate().toEpochDay();
            return daysOverdue * finePerDay;
        }
        return 0;
    }

    public List<Copy> getAvailableCopies() {
        List<Copy> availableCopies = new ArrayList<>();
        for (Copy copy : copies) {
            if (!copy.isBorrowed() && !copy.isReserved()) {
                availableCopies.add(copy);
            }
        }
        return availableCopies;
    }

    public List<Copy> getReservedCopies() {
        List<Copy> reservedCopies = new ArrayList<>();
        for (Copy copy : copies) {
            if (copy.isReserved()) {
                reservedCopies.add(copy);
            }
        }
        return reservedCopies;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ") (" + getAvailableCopies().size() + " copies available, " + publicationYear + ")";
    }

    void cancelReservation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Nested Copy class
    public static class Copy {
        private boolean isBorrowed;
        private boolean isReserved;
        private boolean isDamaged;
        private LocalDate borrowDate;
        private LocalDate dueDate;

        public Copy() {
            this.isBorrowed = false;
            this.isReserved = false;
            this.isDamaged = false;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public boolean isReserved() {
            return isReserved;
        }

        public boolean isDamaged() {
            return isDamaged;
        }

        public void borrow() {
            if (isBorrowed || isReserved) {
                throw new IllegalStateException("This copy is not available for borrowing.");
            } else {
                this.isBorrowed = true;
                this.borrowDate = LocalDate.now();
                this.dueDate = borrowDate.plusDays(14);
                System.out.println("Copy borrowed. Due date: " + dueDate);
            }
        }

        public void returnCopy() {
            if (isBorrowed) {
                this.isBorrowed = false;
                this.borrowDate = null;
                this.dueDate = null;
                System.out.println("Copy returned.");
            } else {
                System.out.println("This copy wasn't borrowed.");
            }
        }

        public void reserve() {
            if (isReserved || isBorrowed) {
                throw new IllegalStateException("This copy cannot be reserved.");
            } else {
                this.isReserved = true;
                System.out.println("Copy reserved.");
            }
        }

        public void cancelReservation() {
            if (isReserved) {
                this.isReserved = false;
                System.out.println("Reservation cancelled.");
            } else {
                System.out.println("This copy wasn't reserved.");
            }
        }

        public boolean isOverdue() {
            return isBorrowed && dueDate != null && LocalDate.now().isAfter(dueDate);
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public void reportDamage() {
            if (isDamaged) {
                System.out.println("This copy is already reported as damaged.");
            } else {
                this.isDamaged = true;
                System.out.println("Damage reported for this copy.");
            }
        }

        void setDueDate(LocalDate minusDays) {
            this.dueDate = dueDate;       }
    }
}
