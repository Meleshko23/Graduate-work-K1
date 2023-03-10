package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.security.SecurityService;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static ru.skypro.homework.mapper.CommentMapper.INSTANCE;

@Slf4j
@RequiredArgsConstructor
@Service

public class CommentServiceImpl implements CommentService {

    private final AdsService adsService;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final SecurityService securityService;

    @Override
    public ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId) {
        Ads adsById = adsService.getAdsById(adsId);
        List<Comment> comments = adsById.getComments();
        return INSTANCE.commentsListToResponseWrapperComment(comments.size(), comments);
    }

    @Override
    public CommentDto createNewComment(Integer adsId, CommentDto commentDto, Authentication authentication) {
        log.info("Was invoked createNewComment method from {}", CommentService.class.getSimpleName());
        Ads ads = adsService.getAdsById(adsId);
        User currentUser = userService.getUser(authentication.getName());

        Comment comment = INSTANCE.commentDtoToComment(commentDto);
        comment.setAds(ads);
        comment.setUser(currentUser);
        comment.setCreateAt(LocalDate.now());
        Comment result = commentRepository.save(comment);
        return INSTANCE.commentToCommentDto(result);
    }

    @Override
    public CommentDto getComment(Integer adPk, Integer id) {
        Comment comment = commentRepository.findAdsComment(adPk, id).orElseThrow(CommentNotFoundException::new);
        return INSTANCE.commentToCommentDto(comment);
    }

    @Override
    public void deleteComment(Integer adPk, Integer id, Authentication authentication) {
        Comment comment = commentRepository.findAdsComment(adPk, id).orElseThrow(CommentNotFoundException::new);
        securityService.checkIfUserHasPermissionToAlter(authentication, comment.getUser().getEmail());
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication) {
        Comment comment = commentRepository.findAdsComment(adPk, id)
                .orElseThrow(CommentNotFoundException::new);
        securityService.checkIfUserHasPermissionToAlter(authentication, comment.getUser().getEmail());
        comment.setText(commentDto.getText());
        comment.setCreateAt(LocalDate.parse(commentDto.getCreateAt()));
        commentRepository.save(comment);
        return INSTANCE.commentToCommentDto(comment);
    }

}
