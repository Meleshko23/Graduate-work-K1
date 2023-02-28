package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto updateUser(UserDto userDto, String name);

    UserDto getUserByEmail(String username);

    void checkIfUserHasPermissionToAlter(Authentication authentication, String username);

}
