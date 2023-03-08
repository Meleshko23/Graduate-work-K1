package ru.skypro.homework.security;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;

public interface SecurityService {
//    boolean accessAds(Authentication authentication, Integer adsId);
//
//    boolean accessComments(Authentication authentication, Integer commentId);
//
//    boolean accessRole(Authentication authentication);
//
//    void accessImage(Authentication authentication, String username);

    void checkIfUserHasPermissionToAlter(Authentication authentication, String username);

    void checkIfUserCanAlterComment(Authentication authentication, Comment comment);

    void checkIfUserCanAlterAds(Authentication authentication, Ads ads);
}
