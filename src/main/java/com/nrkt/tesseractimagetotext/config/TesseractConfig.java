package com.nrkt.tesseractimagetotext.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static net.sourceforge.tess4j.ITessAPI.TessOcrEngineMode;
import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TesseractConfig {

    @Value("${tesseract.data.language}")
    String tesseractDataLanguage;
    @Value("${tesseract.data.path}")
    String tesseractDataPath;

    @Bean
    public Tesseract tesseract() {
        var tesseract = new Tesseract();
        tesseract.setDatapath(tesseractDataPath);
        tesseract.setLanguage(tesseractDataLanguage);
        tesseract.setPageSegMode(TessPageSegMode.PSM_AUTO_OSD);//http://tess4j.sourceforge.net/docs/docs-1.2/net/sourceforge/tess4j/TessAPI1.TessPageSegMode.html
        tesseract.setOcrEngineMode(TessOcrEngineMode.OEM_LSTM_ONLY); //http://tess4j.sourceforge.net/docs/docs-4.0/net/sourceforge/tess4j/ITessAPI.TessOcrEngineMode.html
        return tesseract;
    }
}

