package com.example.a1449877.assignment4gson.Model.Server;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by ian on 15-10-02.
 */
public class User {

    private long id;
    private String name;
    private String password;
    private String email;
    private List<Note> created;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public List<Note> getCreated() {
        return created;
    }

    public void setCreated(List<Note> created) {
        this.created = created;
    }

    public static User parse(String userJson) {
        return new Gson().fromJson(userJson, User.class);
    }

    public boolean isPassword(String foobar) {
        return true;
    }

    public String getUrl() {
        return null;
    }

    public static User[] parseArray(String userListJson) {
        return null;
    }

    public String format() {
        return new Gson().toJson(this);
    }
}
