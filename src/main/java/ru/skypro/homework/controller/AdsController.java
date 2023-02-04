package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseWrapperAds getAllAds() {
        return new ResponseWrapperAds();
    }

    @PostMapping
    public Ads addAds(@RequestBody CreateAds createAds, @RequestParam(name = "image") String[] image) {
        return new Ads();
    }

    @GetMapping("/{ad_pk}/comments")
    public ResponseWrapperComment getComments(@PathVariable Integer ad_pk) {
        return new ResponseWrapperComment();
    }

    @PostMapping("/{ad_pk}/comments")
    public Comment addComments(@PathVariable Integer ad_pk, @RequestBody Comment comment) {
        return new Comment();
    }

    @GetMapping("/{id}")
    public FullAds getFullAd(@PathVariable Integer id) {
        return new FullAds();
    }

    @DeleteMapping("/{id}")
    public void removeAds(@PathVariable Integer id) {
    }

    @PatchMapping("/{id}")
    public Ads updateAds(@PathVariable Integer id, @RequestBody CreateAds createAds) {
        return new Ads();
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public Comment getComments(@PathVariable Integer ad_pk, @PathVariable Integer id) {
        return new Comment();
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public void deleteComments(@PathVariable Integer ad_pk, @PathVariable Integer id) {
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public Comment updateComments(@PathVariable Integer ad_pk, @PathVariable Integer id, @RequestBody Comment comment) {
        return new Comment();
    }

    @GetMapping("/me")
    //уточнить детали, для добавления параметров(?)
    public ResponseWrapperAds getAdsMe() {
        return new ResponseWrapperAds();
    }
}
