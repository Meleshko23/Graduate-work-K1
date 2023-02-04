package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperComment {
    Integer count;
    Comment[] results;
}
