package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;

public interface CommentService {

    /**
     * Комментраии объявления
     *
     * @param adsId - идентификатор обьявления
     * @return - все комментраии объявления
     */
    ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId);

    /**
     * Создание комментария
     *
     * @param adsId          - идентификатор обьявления
     * @param commentDto     - ДТО комментария
     * @param authentication - проверка авторизации
     * @return - созданный комментарий
     */
    CommentDto createNewComment(Integer adsId, CommentDto commentDto, Authentication authentication);

    /**
     * Получение комментария
     *
     * @param adPk - идентификатор объявления
     * @param id   - идентификатор комментария
     * @return - найденый комментарий
     */
    CommentDto getComment(Integer adPk, Integer id);

    /**
     * Удаление комментария
     *
     * @param adPk           - идентификатор объявления
     * @param id             - идентификатор комментария
     * @param authentication - проверка авторизации
     */
    void deleteComment(Integer adPk, Integer id, Authentication authentication);

    /**
     * Обновление комментария
     *
     * @param adPk           - идентификатор объявления
     * @param id             - идентификатор комментария
     * @param commentDto     ДТО комментария
     * @param authentication - проверка авторизации
     * @return - обновленный комментарий
     */
    CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication);

}

