package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private int id;
    private String lastName;
    private String phone;
    private LocalDate regDate;
    private String city;
    private String image;
}
