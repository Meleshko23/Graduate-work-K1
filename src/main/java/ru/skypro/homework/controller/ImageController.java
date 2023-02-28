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
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ImageDto;
import ru.skypro.homework.service.ImageService;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Operation(
            summary = "Обновить изображение",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/octet-stream", // или mediaType = "*/*"?
                            schema = @Schema(implementation = ImageDto.class))),

            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @GetMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getAdsImage(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(imageService.getAdsImage(id));
    }
}
