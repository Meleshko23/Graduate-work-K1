package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;

import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public Ads getAds(@RequestBody Ads ads){
        return new Ads();
    }

    @PostMapping
    public Ads addAds(@RequestBody Ads ads){
        return new Ads();
    }

    @GetMapping("/{ad_pk}/comments")
    public Collection<Comment> getComments(@PathVariable Integer ad_pk){
        return new ArrayList<>();
    }
}
