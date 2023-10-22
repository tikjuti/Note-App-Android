package com.tikjuti.ghichu;


public class Note {
    private int id;
    private String title;
    private String content;

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public Note () {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
