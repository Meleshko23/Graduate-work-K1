package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto updateUser(UserDto userDto, String name);

    UserDto getUserByEmail(String email);

//    boolean checkIfUserIsAdmin(Authentication authentication);

}
