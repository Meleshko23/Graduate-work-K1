package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "user.id")
    Comment commentDtoToComment(CommentDto commentDto);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "comments", target = "results")
    ResponseWrapperComment commentsListToResponseWrapperComment(int size, List<Comment> comments);
}
