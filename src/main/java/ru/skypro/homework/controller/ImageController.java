package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Image;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    @Operation(
            summary = "Получить изображение",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Image.class))),

            @ApiResponse(responseCode = "404", description = "Not Found")
    })

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image updateAdsImage(@PathVariable(required = true) Integer id,
                                @RequestPart MultipartFile image) {
        return new Image();
    }
}
