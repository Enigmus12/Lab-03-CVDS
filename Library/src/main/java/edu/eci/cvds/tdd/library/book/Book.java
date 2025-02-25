package edu.eci.cvds.tdd.library.book;
/**
 * Class Book
 * 
 * Represents a book with a title, author, and ISBN.
 */
public class Book {
    private final String tittle;
    private final String author;
    private final String isbn;

    /**
     * Constructs a new Book instance.
     * 
     * @param title  The title of the book.
     * @param author The author of the book.
     * @param isbn   The ISBN of the book.
     */
    public Book(String tittle, String author, String isbn) {
        this.tittle = tittle;
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Retrieves the ISBN of the book.
     * 
     * @return The ISBN string.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Checks equality between two Book instances based on ISBN.
     * 
     * @param obj The object to compare with.
     * @return True if the ISBNs match, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return isbn.equals(((Book)obj).isbn);
    }
}