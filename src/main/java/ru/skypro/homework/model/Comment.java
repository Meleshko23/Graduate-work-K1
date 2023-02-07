package ru.skypro.homework.model;

import java.util.Objects;

public class Comment {
    private Integer author;
    private String createAt;
    private Integer pk;
    private String text;

    public Comment(Integer author, String createAt, Integer pk, String text) {
        this.author = author;
        this.createAt = createAt;
        this.pk = pk;
        this.text = text;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return author.equals(comment.author) && createAt.equals(comment.createAt) && pk.equals(comment.pk) && text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, createAt, pk, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author +
                ", createAt='" + createAt + '\'' +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                '}';
    }
}
