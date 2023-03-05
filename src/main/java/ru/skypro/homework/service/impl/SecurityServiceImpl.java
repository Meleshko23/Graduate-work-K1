package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.SecurityService;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    @Override
    public boolean accessAds(Authentication authentication, Integer adsId) {
        Ads ads = adsRepository.findById(adsId).orElseThrow();
        Integer userAdsId = ads.getUser().getId();
//        User userId = userRepository.findUserByEmail(authentication.getName()).get();
        User userId = userRepository.findUserByEmail(authentication.getName()).orElseThrow(RuntimeException::new);
        return userId.equals(userAdsId) || accessRole(authentication);
    }

    @Override
    public boolean accessComments(Authentication authentication, Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Integer userIdComment = comment.getUser().getId();
        Integer userId = userRepository.findUserByEmail(authentication.getName()).get().getId();
        return userId.equals(userIdComment) || accessRole(authentication);
    }

    @Override
    public boolean accessRole(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
//        return authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().contains(Role.ADMIN.name()));
    }

}
