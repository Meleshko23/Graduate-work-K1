package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentService {

    ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId);

    CommentDto createNewComment(Integer adsId, CommentDto commentDto);

    CommentDto getComment(Integer adPk, Integer id);

    void deleteComment(Integer adPk, Integer id, Authentication authentication);

    CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication);

//    Comment getCommentById(Integer id);

    List<Comment> findCommentsByAdsId(Integer adsId);

//    void deleteCommentById(Integer id);
}

