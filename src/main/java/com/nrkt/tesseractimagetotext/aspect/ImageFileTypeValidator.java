package com.nrkt.tesseractimagetotext.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Aspect
@Component
class ImageFileTypeValidator {

    @Before("execution(* com.nrkt.tesseractimagetotext.controller.ImageController.*(..))")
    public void forImageProcessingWithMediaTypes(JoinPoint joinPoint) {
        validateImageContent(joinPoint);
    }

    private void validateImageContent(JoinPoint joinPoint) {
        var multipartFile = joinPoint.getArgs()[1];
        if (multipartFile instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) multipartFile;
            var contentType = Objects.requireNonNull(file.getContentType());
            if (Boolean.FALSE.equals(validateSupportedContentType(contentType))) {
                throw new IllegalArgumentException("please select one of the supported image types (png, jpg, jpeg) ");
            }
        }
    }

    private boolean validateSupportedContentType(String contentType) {
        return contentType.equals(ImageContentType.JPEG.contentType)
                || contentType.equals(ImageContentType.JPG.contentType)
                || contentType.equals(ImageContentType.PNG.contentType);
    }

    private enum ImageContentType {
        JPG("image/jpg"),
        JPEG("image/jpeg"),
        PNG("image/png");

        public final String contentType;

        ImageContentType(String contentType) {
            this.contentType = contentType;
        }
    }
}
