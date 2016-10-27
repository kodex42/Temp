package com.example.a1449877.assignment4gson.Model.Server;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by ian on 15-10-02.
 */
public class User {

    private String name;
    private String password;
    private String email;
    private List<Note> created;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    private static class _links {
        private static class self { private String href; }
        private static class note { private String href; }
        private static class created { private String href; }

        private User._links.self self;
        private User._links.note note;
        private User._links.created created;
    }

    public static class UserList {
        private static class _embedded {
            private User[] user;
        }
        private _embedded _embedded;

        public User[] getUsers() { return _embedded.user; }
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

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    public String getUrl() {
        return null;
    }

    public static User[] parseArray(String userListJson) {
        return new Gson().fromJson(userListJson, UserList.class).getUsers();
    }

    public String format() {
        return new Gson().toJson(this);
    }
}
