package com.example.a1449877.assignment4gson.Model.Server;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ian on 15-10-02.c
 */
public class Note {

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

    public static class NoteList {
        private static class _embedded {
            private Note[] note;
        }
        private _embedded _embedded;

        public Note[] getNotes() { return _embedded.note; }
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SZ");

        // TODO: FIX THIS SHIT
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
        return _links.createdBy.href;
    }

    public void setCreatedBy(String urlString) throws IOException {
        _links = new _links();
        _links.createdBy = new _links.createdBy();
        _links.createdBy.href = urlString;
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
        return new Gson().fromJson(noteListJson, NoteList.class).getNotes();
    }

    public String format() {
        return "{\"title\":\"" + title + "\",\"body\":\"" + body + "\",\"category\":" + category + ",\"reminder\":\"" + reminder + "\",\"created\":\"" + created + "\",\"createdBy\":\"" + _links.createdBy.href + "\"}";
    }
}
