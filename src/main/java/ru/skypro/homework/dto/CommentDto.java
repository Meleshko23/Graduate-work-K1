package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int author;
    private String createAt;
    private int pk;
    private String text;
}
