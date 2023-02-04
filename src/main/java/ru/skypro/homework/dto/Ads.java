package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    Integer author;
    String[] image;
    Integer pk;
    Integer price;
    String title;
}
