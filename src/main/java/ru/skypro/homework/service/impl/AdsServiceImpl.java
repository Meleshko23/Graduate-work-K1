package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final UserService userService;
//    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseWrapperAds getAllAds(String title) {
        List<Ads> allAds;

        if (!isEmpty(title)) {
            allAds = adsRepository.findByTitleContainsOrderByTitle(title);
        } else {
            allAds = adsRepository.findAll();
        }

        return adsMapper.INSTANCE.adsListToResponseWrapperAds(allAds.size(), allAds);
    }

    @Override
    public AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile image, Authentication authentication) {
        UserDto userDto = userService.getUserByEmail(authentication.getName());

        Ads ads = adsMapper.INSTANCE.createAdsDtoToAds(createAdsDto);
        ads.setUser(userMapper.INSTANCE.userDtoToUser(userDto));
        Ads savedAds = adsRepository.save(ads);

        Image adsImage = imageService.createImage(image, savedAds); // а если неск фото
        adsImage.setAds(savedAds);
        adsImage.setUser(userMapper.INSTANCE.userDtoToUser(userDto));

        return adsMapper.INSTANCE.adsToAdsDto(savedAds);
    }

    @Override
    public Ads getAdsById(Integer id) {
        return adsRepository.findById(id).orElseThrow(AdsNotFoundException::new); // обработать исключение!
    }

    @Override
    public FullAdsDto getFullAdsById(Integer id) {
        Ads ads = getAdsById(id);
        return adsMapper.INSTANCE.adsToFullAdsDto(ads);
    }

    @Override
    public void removeAds(Integer id, Authentication authentication) {
        Ads ads = getAdsById(id);

        List<Comment> comments = ads.getComments();
        comments.stream()
                .forEach(comment -> commentRepository.deleteById(comment.getId()));


//        checkIfUserCanAlterAds(authentication, ads); // доработать метод проверки
//        adsRepository.delete(ads);
        adsRepository.deleteById(id);
    }

    @Override
    public AdsDto updateAdsById(Integer id, CreateAdsDto createAdsDto, Authentication authentication) {
        Ads oldAds = getAdsById(id);

//        checkIfUserCanAlterAds(authentication, oldAds); // доработать метод проверки
        Ads infoToUpdate = adsMapper.INSTANCE.createAdsDtoToAds(createAdsDto);

        oldAds.setPrice(infoToUpdate.getPrice());
        oldAds.setTitle(infoToUpdate.getTitle());
        oldAds.setDescription(infoToUpdate.getDescription());

        Ads updatedAds = adsRepository.save(oldAds);
        return adsMapper.INSTANCE.adsToAdsDto(updatedAds);
    }

    @Override
    public ResponseWrapperAds getAllAdsForUser(String username) {
        User user = userRepository.findUserByEmail(username).get();
        if (user == null) {

        }
        List<Ads> userAdsList = adsRepository.findAdsByUser(user);
        return adsMapper.INSTANCE.adsListToResponseWrapperAds(userAdsList.size(), userAdsList);

    }

    @Override
    public ResponseWrapperAds findAds(String search) {
        List<Ads> adsDtoDtoList = adsRepository.findAds(search);
//        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
//        responseWrapperAds.setCount(adsDtoDtoList.size());
//        responseWrapperAds.setResults(adsDtoDtoList);
        return adsMapper.INSTANCE.adsListToResponseWrapperAds(adsDtoDtoList.size(), adsDtoDtoList);
    }

}



