package edu.eci.cvds.tdd.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

public class LibraryTest {

    private Library library;

    /**
     * Initializes a new Library instance before each test.
     */
    @Before
    public void setUp() {
        library = new Library();
    }

    /**
     * Tests adding a new book to the library.
     */
    @Test
    public void testAddNewBook() {
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        boolean added = library.addBook(book);
        assertTrue("El libro se agrego correctamente", added);
    }

    /**
     * Tests that adding a null book throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddBookShouldThrowExceptionWhenNull() {
        library.addBook(null);
    }

    /**
     * Tests successful loaning of a book.
     */
    @Test
    public void testLoanBookCorrectly() {
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        library.addBook(book);
        User user = new User("user1","id1");
        library.addUser(user);
        Loan loan = library.loanABook("id1", "isbn1");
        assertNotNull("El préstamo se realizo porque esta el libro", loan);
    }

    /**
     * Tests that loaning a non-existent book throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoanBookFailIfBookNotExists() {
        User user = new User("user1","id1");
        library.addUser(user);

        Loan loan = library.loanABook("nonExistentUser", "isbn1");
        assertNull("El préstamo es nulo porque no hay libros", loan);
    }

    /**
     * Tests that loaning a book by a non-existent user throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoanBookFailIfUserNotExists() {
        User user = new User("user1","id1");
        library.addUser(user);

        Loan loan = library.loanABook("nonExistentUser", "isbn1");
        assertNull("El préstamo es nulo porque no hay libros", loan);
    }

    /**
     * Tests that loaning a book when no copies are available results in a null loan.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoanBookNoAmount() {
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        library.addBook(book);
        User user = new User("user1","id1");
        User user2 = new User("user2","id2");
        library.addUser(user);
        library.addUser(user2);
        Loan loan = library.loanABook("id1", "isbn1");
        Loan loan2 = library.loanABook("id2", "isbn1");
        assertNull("El préstamo no se realizo porque no hay libro", loan2);
    }

    /**
     * Tests that loaning a book that is already loaned results in a null loan.
     */
    @Test
    public void testLoanBookButWasLoan() {
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        library.addBook(book);
        User user = new User("user1","id1");
        library.addUser(user);
        Loan loan = library.loanABook("id1", "isbn1");
        Loan loan2 = library.loanABook("id1", "isbn1");
        assertNull("El préstamo no se realizo porque no hay libro", loan2);
    }

    /**
     * Tests the successful return of a book loan.
     */
    @Test
    public void testReturnLoanValid(){
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        library.addBook(book);
        User user = new User("user1","id1");
        library.addUser(user);
        Loan loan = library.loanABook("id1", "isbn1");
        assertEquals(library.returnLoan(loan).getStatus(),LoanStatus.RETURNED);
    }

    /**
     * Tests returning a loan that is not present in the system throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReturnLoanNoPresentInLoansOfSystem(){
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        User user = new User("user1","id1");
        Loan loan = new Loan(book,user);
        assertEquals(library.returnLoan(loan).getStatus(),LoanStatus.RETURNED);
    }

    /**
     * Tests returning a null loan throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReturnLoanNull(){
        assertEquals(library.returnLoan(null).getStatus(),LoanStatus.RETURNED);
    }

    /**
     * Tests returning a loan that has already been returned throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReturnLoanAlreadyReturned(){
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        library.addBook(book);
        User user = new User("user1","id1");
        library.addUser(user);
        Loan loan = library.loanABook("id1", "isbn1");
        library.returnLoan(loan);
        assertEquals(library.returnLoan(loan).getStatus(),LoanStatus.RETURNED);
    }


}
