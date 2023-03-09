package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

//    private final CommentRepository commentRepository;
//    private final AdsRepository adsRepository;
//    private final UserRepository userRepository;

//    @Override
//    public boolean accessAds(Authentication authentication, Integer adsId) {
//        Ads ads = adsRepository.findById(adsId).orElseThrow();
//        Integer userAdsId = ads.getUser().getId();
//        User userId = userRepository.findUserByEmail(authentication.getName()).get();
////        User userId = userRepository.findUserByEmail(authentication.getName()).orElseThrow(RuntimeException::new);
//        return userId.equals(userAdsId) || accessRole(authentication);
//    }
//
//    @Override
//    public boolean accessComments(Authentication authentication, Integer commentId) {
//        Comment comment = commentRepository.findById(commentId).orElseThrow();
//        Integer userIdComment = comment.getUser().getId();
//        Integer userId = userRepository.findUserByEmail(authentication.getName()).get().getId();
//        return userId.equals(userIdComment) || accessRole(authentication);
//    }
//
//    @Override
//    public boolean accessRole(Authentication authentication) {
//        return authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
////        return authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().contains(Role.ADMIN.name()));
//    }
//
//    @Override
//    public void accessImage(Authentication authentication, String username) {
//
//    }

    @Override
    public void checkIfUserHasPermissionToAlter(Authentication authentication, String username) {
        boolean matchUser = authentication.getName().equals(username);
        boolean userIsAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().contains(Role.ADMIN.name()));

        if (!(userIsAdmin || matchUser)) {
            throw new RuntimeException(); // обработать исключение!
        }
    }

//    @Override
//    public void checkIfUserCanAlterComment(Authentication authentication, Comment comment) {
//        if (!Objects.equals(comment.getUser().getEmail(), authentication.getName())) {
//            throw new RuntimeException("Вы не имеете права доступа");
//        }
//    }

//    @Override
//    public void checkIfUserCanAlterAds(Authentication authentication, Ads ads) {
//        if (!Objects.equals(ads.getUser().getEmail(), authentication.getName())) {
//            throw new RuntimeException("Вы не имеете права доступа");
//        }
//    }

}
