package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

public interface ImageService {

    /**
     * Сохранение изображения объявления
     *
     * @param image    - изображение
     * @param savedAds - обьявление
     * @return - сохранение изображения
     */
    Image createImage(MultipartFile image, Ads savedAds);

    /**
     * Обновление изображения объявления
     *
     * @param id             - идентификатор изображения
     * @param file           - изображение
     * @param authentication - проверка авторизации
     * @return - обновление изображения
     */
    byte[] updateAdsImage(Integer id, MultipartFile file, Authentication authentication);

    /**
     * Получение изображения объявления
     *
     * @param id - идентификатор изображения
     * @return - изображение
     */
    Image getImageByAds(Integer id);

    /**
     * Обновление аватара пользователя
     *
     * @param id             - идентификатор изображения
     * @param file           - изображение
     * @param authentication - проверка авторизации
     * @return - обновленный аватар
     */
    byte[] updateImageUser(Integer id, MultipartFile file, Authentication authentication);

    /**
     * Получение аватара пользователя
     *
     * @param id - идентификатор изображения
     * @return - аватар
     */
    Image getImageByUsers(Integer id);

    /**
     * Создание аватара пользователя
     *
     * @param file           - изображение
     * @param user           - пользователь
     * @param authentication - проверка авторизации
     * @return - сохранение аватара
     */
    byte[] createImageUser(MultipartFile file, User user, Authentication authentication);
}
