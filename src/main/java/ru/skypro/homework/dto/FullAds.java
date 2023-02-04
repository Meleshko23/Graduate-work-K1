package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class FullAds {
    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    String[] image;
    String phone;
    Integer pk;
    Integer price;
    String title;
}
