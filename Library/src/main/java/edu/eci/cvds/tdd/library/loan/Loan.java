package edu.eci.cvds.tdd.library.loan;

import java.time.LocalDateTime;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;

/**
 * Represents a loan in the library system.
 * A loan associates a book with a user and keeps track of loan details.
 */
public class Loan {
    private Book book;
    private User user;
    private LocalDateTime loanDate;
    private LoanStatus status;
    private LocalDateTime returnDate;

    /**
     * Creates a new loan with the specified book and user.
     * The loan date is set to the current date and time.
     * The status is set to ACTIVE.
     * The return date is initialized to the current date and time.
     *
     * @param book The book being loaned.
     * @param user The user borrowing the book.
     */
    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.loanDate = LocalDateTime.now();
        this.status = LoanStatus.ACTIVE;
        this.returnDate = LocalDateTime.now();
    }

    /**
     * Gets the book associated with this loan.
     *
     * @return The book being loaned.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Gets the user who borrowed the book.
     *
     * @return The user associated with this loan.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this loan.
     *
     * @param user The new user to be associated with the loan.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the date and time when the loan was created.
     *
     * @return The loan date.
     */
    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    /**
     * Gets the current status of the loan.
     *
     * @return The loan status.
     */
    public LoanStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the loan.
     *
     * @param status The new loan status.
     */
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    /**
     * Sets the return date of the loan.
     *
     * @param returnDate The date and time when the book is returned.
     */
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
