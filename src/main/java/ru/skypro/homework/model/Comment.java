package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
//    private Integer author;
    private String createAt;
    private String text;

    @ManyToOne
    @JoinColumn(name = "author")
    private User user;
}
