package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.security.SecurityService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final SecurityService securityService;

    @Override
    public Image createImage(MultipartFile file, Ads ads) {
        Image imageToSave = new Image();
        extractInfoFromFile(file, imageToSave);
        imageToSave.setAds(ads);
        return imageRepository.save(imageToSave);
    }

    @Override
    public byte[] createImageUser(MultipartFile file, User user, Authentication authentication) {
        securityService.checkIfUserHasPermissionToAlter(authentication, user.getEmail());
        Image imageToSave = new Image();
        extractInfoFromFile(file, imageToSave);
        imageToSave.setUser(user);
        Image save = imageRepository.save(imageToSave);
        return save.getData();
    }

    @Override
    public byte[] updateAdsImage(Integer id, MultipartFile file, Authentication authentication) {
        Image oldImage = getImageFromDB(id);
        securityService.checkIfUserHasPermissionToAlter(authentication, oldImage.getAds().getUser().getEmail());
        extractInfoFromFile(file, oldImage);
        Image savedImage = imageRepository.save(oldImage);
        return savedImage.getData();
    }

    @Override
    public byte[] updateImageUser(Integer id, MultipartFile file, Authentication authentication) {
        Image oldImage = getImageFromDB(id);
        if (oldImage == null){
            oldImage = new Image();
        }
        securityService.checkIfUserHasPermissionToAlter(authentication, oldImage.getUser().getEmail());
        extractInfoFromFile(file, oldImage);
        Image savedImage = imageRepository.save(oldImage);
        return savedImage.getData();
    }

    private Image getImageFromDB(Integer id) {
        return imageRepository.findById(id).orElseThrow(RuntimeException::new); // обработать исключение!
    }

    private void extractInfoFromFile(MultipartFile file, Image imageToSave) {
        if (file.isEmpty()) {
            throw new RuntimeException(); // обработать исключение!
        }
        byte[] imageData;
        try {
            imageData = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Problems with uploaded image");
        }
        imageToSave.setData(imageData);
        imageToSave.setFileSize(file.getSize());
        imageToSave.setMediaType(file.getContentType());
    }

    public Image getImageByAds(Integer id){
        return imageRepository.findImageByAdsId(id);
    }

    public Image getImageByUsers(Integer id){
        return imageRepository.findImageByUserId(id);
    }
}
