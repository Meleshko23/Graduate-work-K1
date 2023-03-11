package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

public interface UserService {

    /**
     * Обновление данных пользователя
     *
     * @param userDto - ДТО пользователя
     * @param name    - username пользователя
     * @return - 0бновленые данные пользователя
     */
    UserDto updateUser(UserDto userDto, String name);

    /**
     * Получение данных пользователя
     *
     * @param username - username пользователя
     * @return - данные пользователя
     */
    User getUser(String username);

    /**
     * Получение данных пользователя через ДТО
     *
     * @param username - username пользователя
     * @return - данные пользователя
     */
    UserDto getUserDtoByUsername(String username);

}
