package com.nrkt.tesseractimagetotext.factory.base;

import com.nrkt.tesseractimagetotext.model.ImageTextType;

import org.springframework.web.multipart.MultipartFile;

public interface ImageTypeProcessor {
    String process(ImageTextType imageTextType, MultipartFile file);
}
