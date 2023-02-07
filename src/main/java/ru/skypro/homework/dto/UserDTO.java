package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String firstName;
    private Integer id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private List<String> image;
}
