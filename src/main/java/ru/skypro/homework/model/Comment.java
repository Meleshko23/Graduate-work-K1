package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
@Data
public class Comment {
    private Integer author;
    private String createAt;
    @Id
    private Integer pk;
    private String text;
}
