package com.nrkt.tesseractimagetotext.utils;

import com.nrkt.tesseractimagetotext.helper.PathHelper;
import lombok.extern.slf4j.Slf4j;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import lombok.experimental.UtilityClass;

@UtilityClass
@Slf4j
public class OpenCvUtils {

    static {
        OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public BufferedImage matToBufferedImg(Mat mat) {
        try {
            return OpenCvUtils.matToBufImg(mat, ".jpg");
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public Mat gray(Mat mat) {
        var gray = new Mat();
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY, 0);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("gray.jpg"), gray);
        return gray;
    }

    public Mat gaussianBlur(Mat mat) {
        var gaussianBlur = new Mat();
        Imgproc.GaussianBlur(mat, gaussianBlur, new Size(3, 3), 0);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("gaussianBlur.jpg"), gaussianBlur);
        return gaussianBlur;
    }

    public Mat morphDilate(Mat mat, int size) {
        var dilate = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(size, size));
        Imgproc.dilate(mat, dilate, kernel);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("dilate.jpg"), dilate);
        return dilate;
    }

    public Mat morphClose(Mat mat, int size) {
        var morphology = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(size, size));
        Imgproc.morphologyEx(mat, morphology, Imgproc.MORPH_CLOSE, kernel);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("close.jpg"), morphology);
        return morphology;
    }

    public Mat adaptiveGaussianThreshold(Mat mat) {
        var threshold = new Mat();
        Imgproc.adaptiveThreshold(mat, threshold, 255,
                Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                Imgproc.THRESH_BINARY, 27, 20);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("gaussianThreshold.jpg"), threshold);
        return threshold;
    }

    public Mat adaptiveMeanThreshold(Mat mat) {
        var threshold = new Mat();
        Imgproc.adaptiveThreshold(mat, threshold, 255,
                Imgproc.ADAPTIVE_THRESH_MEAN_C,
                Imgproc.THRESH_BINARY_INV, 11, 20);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("meanThreshold.jpg"), threshold);
        return threshold;
    }

    public Mat threshold(Mat mat) {
        var threshold = new Mat();
        Imgproc.threshold(mat, threshold, 0, 255, Imgproc.THRESH_OTSU);
        Imgcodecs.imwrite(PathHelper.exportImagePath().concat("threshold.jpg"), threshold);
        return threshold;
    }

    public Mat byteArrayToMat(byte[] original) {
        return Imgcodecs.imdecode(new MatOfByte(original), Imgcodecs.IMREAD_UNCHANGED);
    }

    public BufferedImage matToBufImg(Mat matrix, String fileExtension) throws IOException {
        var matOfByte = new MatOfByte();
        Imgcodecs.imencode(fileExtension, matrix, matOfByte);
        return ImageIO.read(new ByteArrayInputStream(matOfByte.toArray()));
    }
}
