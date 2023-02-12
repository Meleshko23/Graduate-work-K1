package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

@Slf4j
//@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    private final CommentService commentService;

    public AdsController(AdsService adsService, CommentService commentService) {
        this.adsService = adsService;
        this.commentService = commentService;
    }

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
    public ResponseWrapperAds getAllAds() {
        return adsService.getAllAds();
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdsDto addAds(@RequestPart(name = "properties") CreateAdsDto createAdsDto,
                         @RequestPart MultipartFile image,
                         Authentication authentication) {
        return adsService.createAds(createAdsDto, image, authentication);
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

    @GetMapping("/{ad_pk}/comments")
    public ResponseWrapperComment getComments(@PathVariable(name = "ad_pk", required = true) Integer adPk) {
        return commentService.getAllCommentsForAdsWithId(adPk);
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

    @PostMapping("/{ad_pk}/comments")
    public CommentDto addComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                  @RequestBody(required = true) CommentDto commentDto) {
        return commentService.createNewComment(adPk, commentDto);
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

    @GetMapping("/{id}")
    public FullAdsDto getFullAd(@PathVariable(required = true) Integer id) {
        return adsService.getFullAdsById(id);
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

    @DeleteMapping("/{id}")
    public void removeAds(@PathVariable(required = true) Integer id,
                          Authentication authentication) {
        adsService.removeAds(id, authentication);
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

    @PatchMapping("/{id}")
    public AdsDto updateAds(@PathVariable(required = true) Integer id,
                            @RequestBody CreateAdsDto createAdsDto,
                            Authentication authentication) {
        return adsService.updateAdsById(id, createAdsDto, authentication);
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

    @GetMapping("/{ad_pk}/comments/{id}")
    public CommentDto getComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                  @PathVariable(required = true) Integer id) {
        return commentService.getComments(adPk, id);
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

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public void deleteComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                               @PathVariable(required = true) Integer id,
                               Authentication authentication) {
        commentService.deleteComments(adPk, id, authentication);
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

    @PatchMapping("/{ad_pk}/comments/{id}")
    public CommentDto updateComments(@PathVariable(name = "ad_pk", required = true) Integer adPk,
                                     @PathVariable(required = true) Integer id,
                                     @RequestBody CommentDto commentDto,
                                     Authentication authentication) {
        return commentService.updateComments(adPk, id, commentDto, authentication);
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

    @GetMapping("/me")
    public ResponseWrapperAds getAdsMe(@RequestParam(name = "authenticated", required = false) boolean authenticated, // @RequestParam delete???
                                       @RequestParam(name = "authorities[0].authority", required = false) String authority,
                                       @RequestParam(name = "credentials", required = false) Object credentials,
                                       @RequestParam(name = "details", required = false) Object details,
                                       @RequestParam(name = "principal", required = false) Object principal,
                                       Authentication authentication) {
        return adsService.getAllAdsForUser(authentication.getName());
    }

}
