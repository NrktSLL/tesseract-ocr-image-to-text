package com.nrkt.tesseractimagetotext.service;

import com.nrkt.tesseractimagetotext.utils.OpenCvUtils;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class ImageService {
    public BufferedImage processClearImage(byte[] image) {

        var mat = OpenCvUtils.byteArrayToMat(image);
        mat = OpenCvUtils.gray(mat);

        return OpenCvUtils.matToBufferedImg(mat);
    }

    public BufferedImage processUnclearImage(byte[] image) {

        var mat = OpenCvUtils.byteArrayToMat(image);
        mat = OpenCvUtils.gray(mat);
        mat = OpenCvUtils.adaptiveMeanThreshold(mat);
        mat = OpenCvUtils.morphDilate(mat, 2);
        mat = OpenCvUtils.morphClose(mat, 3);
        mat = OpenCvUtils.adaptiveGaussianThreshold(mat);

        return OpenCvUtils.matToBufferedImg(mat);
    }
}
