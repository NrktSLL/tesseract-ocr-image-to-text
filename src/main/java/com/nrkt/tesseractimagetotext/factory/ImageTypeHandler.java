package com.nrkt.tesseractimagetotext.factory;

import com.nrkt.tesseractimagetotext.factory.base.ImageTypeFactory;
import com.nrkt.tesseractimagetotext.factory.base.ImageTypeProcessor;
import com.nrkt.tesseractimagetotext.service.OcrService;
import org.springframework.stereotype.Component;

@Component
public class ImageTypeHandler extends ImageTypeFactory implements ImageTypeProcessor {
    protected ImageTypeHandler(OcrService ocrService) {
        super(ocrService);
    }
}
