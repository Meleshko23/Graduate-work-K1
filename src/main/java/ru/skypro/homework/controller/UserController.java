package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody NewPassword newPassword){
        return new NewPassword();
    }

    @GetMapping("/me")
    public User getUser(@RequestBody User user){
        return new User();
    }

    @PatchMapping("/me")
    public User updateUser(@RequestBody User user){
        return new User();
    }

    @PatchMapping("/me/image")
    public User updateUserImage(@RequestParam(name = "image") String[] image){
        return new User();
    }
}
