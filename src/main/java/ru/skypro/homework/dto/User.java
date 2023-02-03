package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    String email;
    String firstName;
    Integer id;
    String lastName;
    String phone;
    String regDate;
    String city;
    String image;
}
