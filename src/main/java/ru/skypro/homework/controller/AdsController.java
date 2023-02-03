package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.ResponseWrapperAds;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public Collection<Ads> getAds(){
        return new ArrayList<>();
    }

    @PostMapping
    public Ads addAds(@RequestBody Ads ads){
        return new Ads();
    }

    @GetMapping("/{ad_pk}/comments")
    public Collection<Comment> getComments(@PathVariable Integer ad_pk){
        return new ArrayList<>();
    }

    @PostMapping("/{ad_pk}/comments")
    public Comment addComments(@PathVariable Integer ad_pk, @RequestBody Comment comment){
        return new Comment();
    }

    @GetMapping("/{id}")
    public Ads getFullAd(@PathVariable Integer id){
        return new Ads();
    }

    @DeleteMapping("/{id}")
    public void removeAds (@PathVariable Integer id){

    }

    @PatchMapping("/{id}")
    public CreateAds updateAds(@PathVariable Integer id, @RequestBody CreateAds createAds){
        return new CreateAds();
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public Comment getComments(@PathVariable Integer ad_pk, @PathVariable Integer id){
        return new Comment();
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public void deleteComments(@PathVariable Integer ad_pk, @PathVariable Integer id){

    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public Comment updateComments(@PathVariable Integer ad_pk, @PathVariable Integer id, @RequestBody Comment comment){
        return new Comment();
    }

    @GetMapping("/me")
    //уточнить детали, для добавления параметров(?)
    public ResponseWrapperAds getAdsMe(){
        return new ResponseWrapperAds();
    }
}
