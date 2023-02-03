package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    Integer author;
    String createAt;
    Integer pk;
    String text;
}
