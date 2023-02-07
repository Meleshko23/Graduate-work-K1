package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {
    private Integer author;
    private List<String> image;
    private Integer pk;
    private Integer price;
    private String title;
}
