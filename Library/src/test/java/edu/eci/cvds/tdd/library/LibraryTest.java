package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;  

import org.junit.Before;
import org.junit.Test;
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
    public void testLoanBookFailIfBookNotAvailable() {
        Loan loan = library.loanABook("user123", "isbnNotExists");
        assertNotNull("El préstamo no debería ser nulo si el libro está disponible", loan);
    }

}
