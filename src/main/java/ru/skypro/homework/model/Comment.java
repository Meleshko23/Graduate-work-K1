package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;


@Entity
@Data
public class Comment {
    private Integer author;
    private String createAt;
    @Id
    private Integer pk;
    private String text;

    public Comment(Integer author, String createAt, Integer pk, String text) {
        this.author = author;
        this.createAt = createAt;
        this.pk = pk;
        this.text = text;
    }

    public Comment() {

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
