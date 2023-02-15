package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "images", target = "image")
    AdsDto adsToAdsDto(Ads ads);

    @Mapping(source = "author", target = "user.id")
    @Mapping(source = "pk", target = "id")
    Ads adsDtoToAds(AdsDto adsDto);

    List<AdsDto> ListAdsToListAdsDto(List<Ads> ads);

    Ads createAdsDtoToAds(CreateAdsDto createAdsDto);

    @Mapping(source = "id", target = "pk")
    FullAdsDto adsToFullAdsDto(Ads ads);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "userAdsList", target = "results")
    ResponseWrapperAds adsListToResponseWrapperAds(int size, List<Ads> userAdsList);

    default String mapImageToString(Image image) {
        return Arrays.toString(image.getData());
    }
}
