package com.example.a1449877.assignment4gson.Model.Server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Objects;

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

    public User(String urlString) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();

            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            User user = new Gson().fromJson(buffer.toString(), User.class);

            id = user.getId();
            name = user.getName();
            password = user.getPassword();
            email = user.getEmail();
            created = user.getCreated();

        } finally {
            if (reader != null)
                reader.close();
        }


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

    public boolean isPassword(String password) {
        return this.password.equals(password);
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
