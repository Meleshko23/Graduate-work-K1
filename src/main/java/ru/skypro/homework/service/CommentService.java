package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.model.Comment;

public interface CommentService {

    ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId);

    CommentDto createNewComment(Integer adsId, CommentDto commentDto);

    CommentDto getComments(Integer adPk, Integer id);

    void deleteComments(Integer adPk, Integer id, Authentication authentication);

    CommentDto updateComments(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication);

    Comment getCommentByIdAndAuthorId(Integer adPk, Integer id);

}

