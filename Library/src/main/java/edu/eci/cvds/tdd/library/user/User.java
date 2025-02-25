package edu.eci.cvds.tdd.library.user;

/**
 * Represents a user in the library system.
 */
public class User {
    private String name;
    private String id;

    /**
     * Creates a new user with the specified name and ID.
     *
     * @param name The name of the user.
     * @param id The unique identifier of the user.
     */
    public User(String name, String id){
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user ID.
     */
    public String getId() {
        return id;
    }
}