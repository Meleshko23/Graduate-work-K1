package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;

public interface ImageService {
    Image createImage(MultipartFile image, Ads savedAds);

}
