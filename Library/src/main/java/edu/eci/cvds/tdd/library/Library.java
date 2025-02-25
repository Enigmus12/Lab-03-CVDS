package edu.eci.cvds.tdd.library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    /**
     * Initializes a new Library instance with empty collections.
     */
    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book)  throws IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }

        if (books.containsKey(book)){
            books.put(book, books.get(book) + 1);
        }else {
            books.put(book, 1);
        }
        return true;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {

        Book book = searchBookByIsbn(isbn);

        if (book==null){
            throw new IllegalArgumentException("El libro con ISBN " + isbn + " no existe.");
        }
        if(books.containsKey(book) && verifyUser(userId) && verifyLoans(userId,book) ){

            if(books.get(book) > 0){
                User user = searchUserById(userId);
                Loan loan = new Loan(book,user);
                loans.add(loan);
                books.put(book, books.get(book) - 1);
                return loan;
            }else {
                throw new IllegalArgumentException("No hay libros disponibles para prestar.");
            }
        }
        return null;
    }

    /**
     * Searches for a user by their ID.
     *
     * @param userId The user ID.
     * @return The User object, or null if not found.
     */
    public User searchUserById(String userId){
        for(User u : users){
            if (u.getId().equals(userId)){
                return u;
            }
        }
        return null;
    }

    /**
     * Searches for a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The Book object, or null if not found.
     */
    public Book searchBookByIsbn(String isbn) {
        for (Book book : books.keySet()) {  
            if (book.getIsbn().equals(isbn)) {
                return book; 
            }
        }
        return null; 
    }

    /**
     * Verifies if a user exists.
     *
     * @param userId The user ID.
     * @return true if the user exists, false otherwise.
     */
    public boolean verifyUser(String userId) {
        for (User u : users) {
            if (u.getId().equals(userId)) {
                return true; 
            }
        }        
        return false;
    }

    /**
     * Verifies if a user has an active loan for a specific book.
     *
     * @param userId The user ID.
     * @param book The book to check.
     * @return true if the user does not have an active loan for the book, false otherwise.
     */
    public boolean verifyLoans(String userId, Book book){
        for (Loan l: loans){
            if (l.getUser().getId().equals(userId) && l.getBook().equals(book) && l.getStatus() == LoanStatus.ACTIVE){
                return false;
            }
        }
        return true;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        if (loan == null || !loans.contains(loan) || loan.getStatus() != LoanStatus.ACTIVE) {
            throw new IllegalArgumentException("El préstamo no es válido o ya fue devuelto.");
        }

        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        Book book = loan.getBook();
        if (book != null && books.containsKey(book)) {
            books.put(book, books.get(book) + 1);
        }
        return loan;
    }

    /**
     * Adds a new user to the library system.
     *
     * @param user The user to add.
     * @return true if the user was successfully added.
     */
    public boolean addUser(User user) {
        return users.add(user);
    }

}