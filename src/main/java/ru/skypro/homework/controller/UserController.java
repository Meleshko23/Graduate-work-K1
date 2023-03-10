package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @Operation(
            summary = "Установить пароль",
            description = "Позволяет обновить пароль пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = NewPassword.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok(newPassword);
    }

    @Operation(
            summary = "Получить данные",
            description = "Выводит данные о пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        log.info("Was invoked get user method");
        return ResponseEntity.ok(userService.getUserDtoByUsername(authentication.getName()));
    }

    @Operation(
            summary = "Обновить данные",
            description = "Позволяет обновить информацию о пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              Authentication authentication) {
        log.info("Was invoked update user method");
        return ResponseEntity.ok(userService.updateUser(userDto, authentication.getName()));
    }

    @Operation(
            summary = "Обновить изображение",
            description = "Обновить изображение пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateUserImage(@RequestParam MultipartFile image, Authentication authentication) {
        log.info("Was invoked updateUserImage method from {}", UserController.class.getSimpleName());
        User user = userService.getUser(authentication.getName());
        byte[] imageUser;
        if (user.getImage() == null) {
            imageUser = imageService.createImageUser(image, user, authentication);
        } else {
            imageUser = imageService.updateImageUser(user.getImage().getId(), image, authentication);
        }
        return ResponseEntity.ok(imageUser);
    }

    @Operation(
            summary = "Показать изображение",
            description = "Получить изображение пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImageUser(@PathVariable Integer id) {
        log.info("Was invoked getImageUser method from {}", UserController.class.getSimpleName());
        Image imageUser = imageService.getImageByUsers(id);
        return ResponseEntity.ok(imageUser.getData());
    }
}
