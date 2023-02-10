package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

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
        return new ResponseWrapperAds();
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
                         @RequestPart MultipartFile image) {
        return new AdsDto();
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
        return new ResponseWrapperComment();
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
                                  @RequestBody(required = true) CommentDto commentDTO) {
        return new CommentDto();
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
        return new FullAdsDto();
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
    public void removeAds(@PathVariable(required = true) Integer id) {
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
                            @RequestBody CreateAdsDto createAdsDto) {
        return new AdsDto();
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
        return new CommentDto();
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
                               @PathVariable(required = true) Integer id) {
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
                                     @RequestBody CommentDto commentDTO) {
        return new CommentDto();
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
    public ResponseWrapperAds getAdsMe(@RequestParam(name = "authenticated", required = false) boolean authenticated,
                                       @RequestParam(name = "authorities[0].authority", required = false) String authority,
                                       @RequestParam(name = "credentials", required = false) Object credentials,
                                       @RequestParam(name = "details", required = false) Object details,
                                       @RequestParam(name = "principal", required = false) Object principal) {
//        if () {
            return new ResponseWrapperAds();
//        }
    }

}
