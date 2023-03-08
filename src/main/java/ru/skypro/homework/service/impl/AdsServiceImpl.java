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
import ru.skypro.homework.security.SecurityService;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.List;

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
    private final SecurityService securityService;

    @Override
    public ResponseWrapperAds getAllAds() {
        List<AdsDto> allAdsDto = adsMapper.ListAdsToListAdsDto(adsRepository.findAll());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(allAdsDto.size());
        responseWrapperAds.setResults(allAdsDto);
        return responseWrapperAds;
    }

    @Override
    public AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile image, Authentication authentication) {
        User currentUser = userService.getUser(authentication.getName());

        Ads ads = adsMapper.INSTANCE.createAdsDtoToAds(createAdsDto);
        ads.setUser(currentUser);
        Ads savedAds = adsRepository.save(ads);

        Image adsImage = imageService.createImage(image, savedAds);
        savedAds.setImage(adsImage);

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
        securityService.checkIfUserCanAlterAds(authentication, ads); // доработать метод проверки
        List<Comment> comments = ads.getComments();
        comments.stream()
                .forEach(comment -> commentRepository.deleteById(comment.getId()));
        adsRepository.deleteById(id);
    }

    @Override
    public AdsDto updateAdsById(Integer id, CreateAdsDto createAdsDto, Authentication authentication) {
        Ads oldAds = getAdsById(id);
        securityService.checkIfUserCanAlterAds(authentication, oldAds);
//        if (securityService.accessAds(authentication, id)) {
//            Ads infoToUpdate = adsMapper.INSTANCE.createAdsDtoToAds(createAdsDto);
//            oldAds.setPrice(infoToUpdate.getPrice());
//            oldAds.setTitle(infoToUpdate.getTitle());
//            oldAds.setDescription(infoToUpdate.getDescription());
//        }
        Ads infoToUpdate = adsMapper.INSTANCE.createAdsDtoToAds(createAdsDto);
        oldAds.setPrice(infoToUpdate.getPrice());
        oldAds.setTitle(infoToUpdate.getTitle());
        oldAds.setDescription(infoToUpdate.getDescription());
        Ads updatedAds = adsRepository.save(oldAds);
        return adsMapper.INSTANCE.adsToAdsDto(updatedAds);
    }

    @Override
    public ResponseWrapperAds getAllAdsForUser(String username) {
//        User user = userRepository.findUserByEmail(username).get();
        List<Ads> userAdsList = adsRepository.findAdsByUserEmail(username);
        List<AdsDto> userAdsDtoList = adsMapper.ListAdsToListAdsDto(userAdsList);
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(userAdsDtoList.size());
        responseWrapperAds.setResults(userAdsDtoList);
        return responseWrapperAds;
    }

    @Override
    public ResponseWrapperAds findAds(String search) {
        List<Ads> adsDtoDtoList = adsRepository.findAds(search);
//        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
//        responseWrapperAds.setCount(adsDtoDtoList.size());
//        responseWrapperAds.setResults(adsDtoDtoList);
        return adsMapper.INSTANCE.adsListToResponseWrapperAds(adsDtoDtoList.size(), adsDtoDtoList);
    }

//    private void checkIfUserCanAlterAds(Authentication authentication, Ads ads) {
//        if (!Objects.equals(ads.getUser().getEmail(), authentication.getName())) {
//            throw new RuntimeException("Вы не имеете права доступа");
//        }
//    }
}



