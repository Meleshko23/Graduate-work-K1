package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.CommentsNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;

import static ru.skypro.homework.mapper.CommentMapper.INSTANCE;

@Slf4j
@RequiredArgsConstructor
@Service

public class CommentServiceImpl implements CommentService {

    private final AdsService adsService;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final CommentRepository commentRepository;
    private final UserService userService;


    @Override
    public ResponseWrapperComment getAllCommentsForAdsWithId(Integer adsId) {
        Ads adsById = adsService.getAdsById(adsId); // возможно исключение AdsNotFoundException

        List<Comment> comments = adsById.getComments();
        if (comments.isEmpty()) {
            throw new CommentsNotFoundException();
        }
        return INSTANCE.commentsListToResponseWrapperComment(comments.size(), comments);
    }

    @Override
    public CommentDto createNewComment(Integer adsId, CommentDto commentDto) {
        Ads ads = adsService.getAdsById(adsId); // возможно исключение AdsNotFoundException
        Comment comment = INSTANCE.commentDtoToComment(commentDto);
        comment.setAds(ads);
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

//        checkIfUserCanAlterComment(authentication, comment); // доработать метод проверки
//        commentRepository.delete(comment);

        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto, Authentication authentication) {
        Comment comment = commentRepository.findAdsComment(adPk, id)
                .orElseThrow(CommentNotFoundException::new);

        UserDto user = userService.getUserByEmail(authentication.getName());
        User currentUser = userMapper.userDtoToUser(user);

//        Comment updateComment = INSTANCE.commentDtoToComment(commentDto);

////        checkIfUserCanAlterComment(authentication, comment); // доработать метод проверки
        comment.setText(commentDto.getText());
        comment.setCreateAt(commentDto.getCreateAt());
        comment.setUser(currentUser);
        commentRepository.save(comment);

        return INSTANCE.commentToCommentDto(comment);

    }

//    @Override
//    public Comment getCommentById(Integer id) {
//        return commentRepository.findById(Long.valueOf(id))
//                .orElseThrow(RuntimeException::new); // обработать исключение!
//    }

    @Override
    public List<Comment> findCommentsByAdsId(Integer adsId) {
        return commentRepository.findCommentsByAdsId(adsId);
    }

//    @Override
//    public void deleteCommentById(Integer id) {
//        commentRepository.deleteById(id);
//    }
}
