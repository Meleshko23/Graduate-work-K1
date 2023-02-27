package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image createImage(MultipartFile file, Ads ads) {
        Image imageToSave = new Image();
        extractInfoFromFile(file, imageToSave);
        imageToSave.setAds(ads);
        return imageRepository.save(imageToSave);
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
}
