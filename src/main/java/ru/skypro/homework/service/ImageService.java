package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

public interface ImageService {
    Image createImage(MultipartFile image, Ads savedAds);

    byte[] updateAdsImage(Integer id, MultipartFile file, Authentication authentication);

    Image getImageByAds(Integer id);

    byte[] updateImageUser(Integer id, MultipartFile file, Authentication authentication);

    Image getImageByUsers(Integer id);

    byte[] createImageUser(MultipartFile file, User user, Authentication authentication);
}
