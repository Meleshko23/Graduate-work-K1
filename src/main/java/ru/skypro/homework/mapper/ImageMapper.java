package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ImageDto;
import ru.skypro.homework.model.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

//    ImageDto imageToImageDto(Image image);

    Image imageDtoToImage(ImageDto imageDto);
}
