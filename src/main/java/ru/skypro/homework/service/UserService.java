package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

public interface UserService {

    UserDto updateUser(UserDto userDto, String name);

    User getUser(String username);

    UserDto getUserDtoByUsername(String username);

    void checkIfUserHasPermissionToAlter(Authentication authentication, String username);

}
