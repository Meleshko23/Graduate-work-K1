package ru.skypro.homework.security;

import org.springframework.security.core.Authentication;

public interface SecurityService {

    void checkIfUserHasPermissionToAlter(Authentication authentication, String username);

}
