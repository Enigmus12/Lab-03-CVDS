package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;  

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;

    @Before
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddNewBook() {
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        boolean added = library.addBook(book);
        assertTrue("El libro se agrego correctamente", added);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddBookShouldThrowExceptionWhenNull() {
        library.addBook(null);
    }

    @Test
    public void testLoanBookCorrectly() {
        Book book = new Book("Tittle 1", "Author 1", "isbn1");
        library.addBook(book);
        User user = new User("user1","id1");
        library.addUser(user);
        Loan loan = library.loanABook("id1", "isbn1");
        assertNotNull("El préstamo se realizo porque esta el libro", loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanBookFailIfBookNotExists() {
        User user = new User("user1","id1");
        library.addUser(user);

        Loan loan = library.loanABook("nonExistentUser", "isbn1");
        assertNull("El préstamo es nulo porque no hay libros", loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanBookFailIfUserNotExists() {
        User user = new User("user1","id1");
        library.addUser(user);

        Loan loan = library.loanABook("nonExistentUser", "isbn1");
        assertNull("El préstamo es nulo porque no hay libros", loan);
    }

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
}
