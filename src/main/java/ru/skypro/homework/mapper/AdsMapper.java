package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.model.Ads;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    AdsDto adsToAdsDto(Ads ads);

    Ads adsDtoToAds(AdsDto adsDto);

    List<AdsDto> ListAdsToListAdsDto(List<Ads> ads);

    Ads createAdsDtoToAds(CreateAdsDto createAdsDto);

    FullAdsDto adsToFullAdsDto(Ads ads);

    ResponseWrapperAds adsListToResponseWrapperAds(int size, List<Ads> userAdsList);
}
