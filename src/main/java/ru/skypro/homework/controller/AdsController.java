package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.CommentsNotFoundException;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;
    private final ImageService imageService;

    @Operation(
            summary = "Получить все объявления",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = ResponseWrapperAds.class)))
    })

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @Operation(
            summary = "Создать новое объявление",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart("properties") CreateAdsDto createAdsDto,
                                         @RequestPart("image") MultipartFile image,
                                         Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.createAds(createAdsDto, image, authentication));
    }

    @Operation(
            summary = "Посмотреть комментарии",
            description = "Получает все комментарии, которые оставили под объявлением"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = ResponseWrapperComment.class))),

            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable(name = "ad_pk", required = true) Integer adPk) {
        ResponseWrapperComment result = null;
        try {
            result = commentService.getAllCommentsForAdsWithId(adPk);
        } catch (AdsNotFoundException | CommentsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Добавить комментарий к объявлению",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = CommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<CommentDto> addComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                                  @RequestBody(required = true) CommentDto commentDto,
                                                  Authentication authentication) {
        CommentDto result = null;
        try {
            result = commentService.createNewComment(adPk, commentDto, authentication);
        } catch (AdsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Получить объявление",
            description = "Получает объявление со всеми данными о пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = FullAdsDto.class))),

            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getFullAd(@PathVariable(required = true) Integer id) {
        FullAdsDto result = null;
        try {
            result = adsService.getFullAdsById(id);
        } catch (AdsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Удалить объявление",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAds(@PathVariable(required = true) Integer id,
                                          Authentication authentication) {
        log.info("Was invoked delete ad by id = {} method", id);
        adsService.removeAds(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Обновить объявление",
            description = "Позволяет отредактировать объявление"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable(required = true) Integer id,
                                            @RequestBody CreateAdsDto createAdsDto,
                                            Authentication authentication) {
        log.info("Was invoked update ad by id = {} method", id);
        try {
            return ResponseEntity.ok(adsService.updateAdsById(id, createAdsDto, authentication));
        } catch (AdsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Посмотреть комментарий",
            description = "Позволяет просмотреть определенный комментарий к объявлению"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = CommentDto.class))),

            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> getComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                                  @PathVariable(required = true) Integer id) {
        CommentDto result = null;
        try {
            result = commentService.getComment(adPk, id);
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Удалить комментарий",
            description = "Удаляет комментарий по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                               @PathVariable(required = true) Integer id,
                                               Authentication authentication) {
        try {
            commentService.deleteComment(adPk, id, authentication);
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновить комментарий",
            description = "Позволяет редактировать комментарий"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = CommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                                     @PathVariable(required = true) Integer id,
                                                     @RequestBody CommentDto commentDto,
                                                     Authentication authentication) {
        log.info("Was invoked update ad's = {} comment by id = {} method", adPk, id);
        CommentDto result = null;
        try {
            result = commentService.updateComment(adPk, id, commentDto, authentication);
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Получить объявления пользователя",
            description = "Позволяет получить все объявления, которые создал пользователь"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = ResponseWrapperAds.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adsService.getAllAdsForUser(authentication.getName()));
    }

    //    Поиск обьявлений
    @GetMapping(params = {"search"})
    public ResponseEntity<ResponseWrapperAds> findAds(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(adsService.findAds(search));
    }

    //    Обновление фото объявления
    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable Integer id,
                                                 @RequestParam MultipartFile image,
                                                 Authentication authentication) {
        log.info("Was invoked updateAdsImage method from {}", ImageController.class.getSimpleName());
        byte[] imageBytes = imageService.updateAdsImage(id, image, authentication);
        return ResponseEntity.ok(imageBytes);
    }

}
