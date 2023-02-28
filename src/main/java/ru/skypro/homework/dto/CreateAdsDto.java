package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAdsDto {
    private String description;
    private String image;
    private int price;
    private Integer pk;
    private String title;
}
