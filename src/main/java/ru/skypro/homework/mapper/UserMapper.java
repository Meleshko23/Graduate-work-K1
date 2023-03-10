package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "image", target = "image")
    UserDto userToUserDto(User user);

    @Mapping(target = "image", ignore = true)
    User userDtoToUser(UserDto userDTO);

    @Mapping(source = "username", target = "email")
    @Mapping(target = "role", defaultValue = "USER")
    User registerToUser (RegisterReq req);

    default String mapImageToString(Image image) {
        if (image == null){
            return "";
        }
        return "/users/image/" + image.getUser().getId();
    }
}
