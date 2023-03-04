package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    boolean accessAds(Authentication authentication, Integer adsId);

    boolean accessComments(Authentication authentication, Integer commentId);

    boolean accessRole(Authentication authentication);
}
