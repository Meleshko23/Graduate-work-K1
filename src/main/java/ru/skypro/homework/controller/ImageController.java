package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Image;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    @Operation(
            summary = "Обновление изображения",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Изображение обновлено",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Image.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если по указанному id не найдено изображение"
                    )
            }
    )

    @PatchMapping("/{id}")
    public Image updateAdsImage(@PathVariable Integer id, @RequestBody Image image) {
        return new Image();
    }
}
