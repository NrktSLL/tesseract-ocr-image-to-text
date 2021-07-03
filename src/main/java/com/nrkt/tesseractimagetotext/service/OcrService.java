package com.nrkt.tesseractimagetotext.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class OcrService {

    Tesseract tesseract;
    ImageService imageService;

    public String textFromClearImage(MultipartFile multipartFile) {
        try {
            var bufferedImage = imageService.processClearImage(multipartFile.getBytes());
            var output = ocrExport(bufferedImage);
            log.debug(output);
            return output;
        } catch (IOException | TesseractException ex) {
            log.error(ex.getMessage());
        }
        return "";
    }

    public String textFromUnClearImage(MultipartFile multipartFile) {
        try {
            var bufferedImage = imageService.processUnclearImage(multipartFile.getBytes());
            var output = ocrExport(bufferedImage);
            log.debug(output);
            return output;
        } catch (IOException | TesseractException ex) {
            log.error(ex.getMessage());
        }
        return "";
    }

    private String ocrExport(BufferedImage bufferedImage) throws TesseractException {
        return tesseract.doOCR(bufferedImage);
    }
}
