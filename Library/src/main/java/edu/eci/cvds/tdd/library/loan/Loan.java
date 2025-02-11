package edu.eci.cvds.tdd.library.loan;

import java.time.LocalDateTime;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;

public class Loan {
    private Book book;
    private User user;
    private LocalDateTime loanDate;
    private LoanStatus status;
    private LocalDateTime returnDate;

    public Loan (Book book, User user){
        this.book = book;
        this.user = user;
        this.loanDate = LocalDateTime.now();
        this.status = LoanStatus.ACTIVE;
        this.returnDate = LocalDateTime.now();
    }
    public Book getBook() {
        return book;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }


    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }


    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}