package edu.eci.cvds.tdd.library.user;

public class User {
    private String name;
    private String id;

    public User(String name, String id){
        this.name=name;
        this.id=id;
    }
    public String getId() {
        return id;
    }

}