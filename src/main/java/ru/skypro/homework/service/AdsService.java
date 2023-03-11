package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.model.Ads;

public interface AdsService {
    /**
     * Возвращает объявления
     *
     * @return все объявления
     */
    ResponseWrapperAds getAllAds();

    /**
     * Возвращает объявление
     *
     * @param id - идентификатор объявления
     * @return - объявление по id
     */
    Ads getAdsById(Integer id);

    /**
     * Создание обьявления
     *
     * @param createAdsDto   - ДТО обьявления
     * @param image          - картинка обьявления
     * @param authentication - проверка авторизации
     * @return - возвращает созданное обьявление
     */
    AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile image, Authentication authentication);

    /**
     * Получить обьявление пользователя
     *
     * @param id - идентификатор объявления
     * @return - возврат обьявления пользователя
     */
    FullAdsDto getFullAdsById(Integer id);

    /**
     * Удаление обьявления
     *
     * @param id             - идентификатор объявления
     * @param authentication - проверка авторизации
     */
    void removeAds(Integer id, Authentication authentication);

    /**
     * Обновление обьявления
     *
     * @param id             - идентификатор объявления
     * @param createAdsDto   - ДТО обьявления
     * @param authentication - проверка авторизации
     * @return - возврат обновленного обьявления
     */
    AdsDto updateAdsById(Integer id, CreateAdsDto createAdsDto, Authentication authentication);

    /**
     * Получить обьявления пользователя
     *
     * @param username - идентификатор пользователя
     * @return - возврат всех обьявлений пользователя
     */
    ResponseWrapperAds getAllAdsForUser(String username);

    /**
     * Поиск объявлений
     *
     * @param search - параметр поиска
     * @return - возврат найденых обьявлений
     */
    ResponseWrapperAds findAds(String search);

}
