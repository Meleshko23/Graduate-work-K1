package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;

public interface CommentService {

    ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId);

    CommentDto createNewComment(Integer adsId, CommentDto commentDto, Authentication authentication);

    CommentDto getComment(Integer adPk, Integer id);

    void deleteComment(Integer adPk, Integer id, Authentication authentication);

    CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication);

}

