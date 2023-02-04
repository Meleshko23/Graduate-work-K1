package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperAds {
    Integer count;
    Ads[] results;
}
