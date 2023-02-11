package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final UserService userService;

//    public AdsServiceImpl(AdsRepository adsRepository, AdsMapper adsMapper, UserMapper userMapper, ImageService imageService, UserService userService) {
//        this.adsRepository = adsRepository;
//        this.adsMapper = adsMapper;
//        this.userMapper = userMapper;
//        this.imageService = imageService;
//        this.userService = userService;
//    }

//    @Override
//    public ResponseWrapperAds getAllAds() {
//        List<Ads> adsList = adsRepository.findAll();
//        return adsMapper.adsToAdsDTO(getAdsById(Integer id));
//    }

    @Override
    public ResponseWrapperAds getAllAds() {
        return null;
    }

    @Override
    public Ads getAdsById(Integer id) {
        return null;
    }

    @Override
    public AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile image, Authentication authentication) {
        return null;
    }

    @Override
    public FullAdsDto getFullAdsById(Integer id) {
        return null;
    }

    @Override
    public void removeAds(Integer id, Authentication authentication) {

    }

    @Override
    public AdsDto updateAdsById(Integer id, CreateAdsDto createAdsDto, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseWrapperAds getAllAdsForUser(String username) {
        return null;
    }
}
