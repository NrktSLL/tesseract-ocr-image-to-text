package com.nrkt.tesseractimagetotext.controller;

import com.nrkt.tesseractimagetotext.factory.ImageTypeHandler;
import com.nrkt.tesseractimagetotext.model.ImageTextType;
import com.nrkt.tesseractimagetotext.model.TextModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("v1/ocr")
@Tag(name = "ocr", description = "OCR Service")
public class ImageController {

    ImageTypeHandler imageTypeHandler;

    @PostMapping(value = "/mediaTypes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Process the image and returns the response content in the selected media type(XML/JSON)")
    public ResponseEntity<TextModel> imageProcessingWithMediaTypes(@RequestParam("ImageType") ImageTextType imageTextType,
                                                                   @RequestPart("file") MultipartFile file) {

        var output = imageTypeHandler.process(imageTextType, file);
        var text = new TextModel(output);

        return ResponseEntity.ok(text);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/plain")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Process the image and returns the response content in Text")
    public ResponseEntity<String> imageProcessing(@RequestParam("ImageType") ImageTextType imageTextType,
                                                  @RequestPart("file") MultipartFile file) {

        var output = imageTypeHandler.process(imageTextType, file);
        return ResponseEntity.ok(output);
    }
}