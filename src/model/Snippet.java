package model;

import java.time.LocalDateTime;

public class Snippet {
    private int id;
    private String title;
    private String code;
    private String language;
    private LocalDateTime createdAt;

    public Snippet(int id, String title, String code, String language, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.language = language;
        this.createdAt = createdAt;
    }

    public Snippet(String title, String code, String language, String createdAtStr) {
        this(-1, title, code, language, LocalDateTime.parse(createdAtStr));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return title + " (" + language + ")";
    }
}
