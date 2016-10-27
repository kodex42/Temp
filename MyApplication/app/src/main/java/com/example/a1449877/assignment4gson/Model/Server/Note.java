package com.example.a1449877.assignment4gson.Model.Server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

/**
 * Created by ian on 15-10-02.c
 */
public class Note {

    private long id;
    private Date created;
    private Date reminder;
    private String title;
    private String body;
    private int category;
    private User createdBy;
    private _links _links;

    private static class _links {
        private static class self { private String href; }
        private static class note { private String href; }
        private static class createdBy { private String href; }

        private self self;
        private note note;
        private createdBy createdBy;
    }

    private static class NoteList {
        private static class _embedded {
            private Note[] note;
        }
        private _embedded _embedded;
    }

    public long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedBy() {
        String url = _links.createdBy.href;
        return url;
/*
        try {
            return new User(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        */
    }

    public void setCreatedBy(String urlString) throws IOException {
        _links.createdBy.href = urlString;
        /*BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();

            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            this.createdBy = new Gson().fromJson(buffer.toString(), User.class);

        } finally {
            if (reader != null)
                reader.close();
        }*/



    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public static Note parse(String noteJson) {
        return new Gson().fromJson(noteJson, Note.class);
    }

    public String getUrl() {
        return _links.self.href;
    }

    public boolean isHasReminder() {
        return reminder != null;
    }

    public static Note[] parseArray(String noteListJson) {
        return new Gson().fromJson(noteListJson, NoteList.class);
    }

    public String format() {
        return null;
    }
}
