package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private int id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private List<String> image;
}
