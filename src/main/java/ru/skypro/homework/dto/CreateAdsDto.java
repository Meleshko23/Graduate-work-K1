package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAdsDto {
    private String description;
    private String image;
    private Integer price;
    private Integer pk;
    private String title;
}
