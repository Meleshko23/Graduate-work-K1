package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    private Integer author;
    private String createAt;
    private Integer pk;
    private String text;
}
