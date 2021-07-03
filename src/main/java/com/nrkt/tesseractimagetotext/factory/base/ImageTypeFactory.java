package com.nrkt.tesseractimagetotext.factory.base;

import com.nrkt.tesseractimagetotext.model.ImageTextType;
import com.nrkt.tesseractimagetotext.service.OcrService;

import org.springframework.web.multipart.MultipartFile;

public abstract class ImageTypeFactory {

    private final OcrService ocrService;

    protected ImageTypeFactory(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    public String process(ImageTextType imageTextType, MultipartFile file) {
        return ImageTextType.CLEAR_TEXT_IMAGE.equals(imageTextType) ?
                ocrService.textFromClearImage(file) :
                ocrService.textFromUnClearImage(file);
    }
}
