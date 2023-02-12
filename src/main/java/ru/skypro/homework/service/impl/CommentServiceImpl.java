package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service

public class CommentServiceImpl implements CommentService {

    private final AdsService adsService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserService userService;


    @Override
    public ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId) {
        Ads adsById = adsService.getAdsById(adsId);
        List<Comment> comments = adsById.getComments();
        return commentMapper.INSTANCE.commentsListToResponseWrapperComment(comments.size(), comments);
    }

    @Override
    public CommentDto createNewComment(Integer adsId, CommentDto commentDto) {
        Ads adsById = adsService.getAdsById(adsId);
        Comment comment = commentMapper.INSTANCE.commentDtoToComment(commentDto);
        comment.setAds(adsById);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.INSTANCE.commentToCommentDto(savedComment);
    }

    @Override
    public CommentDto getComments(Integer adPk, Integer id) {
        Comment comment = getCommentByIdAndAuthorId(adPk, id);
        return commentMapper.INSTANCE.commentToCommentDto(comment);
    }

    @Override
    public void deleteComments(Integer adPk, Integer id, Authentication authentication) {
        Comment comment = getCommentByIdAndAuthorId(adPk, id);

//        checkIfUserCanAlterComment(authentication, comment); // доработать метод проверки
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateComments(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication) {
        Comment comment = getCommentByIdAndAuthorId(adPk, id);

//        checkIfUserCanAlterComment(authentication, comment); // доработать метод проверки
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return commentMapper.INSTANCE.commentToCommentDto(comment);
    }

    @Override
    public Comment getCommentByIdAndAuthorId(Integer adPk, Integer id) {
        return commentRepository.findCommentByIdAndAuthorId(adPk, id)
                .orElseThrow(RuntimeException::new); // обработать исключение!
    }
}
