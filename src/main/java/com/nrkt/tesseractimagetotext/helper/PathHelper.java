package com.nrkt.tesseractimagetotext.helper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@UtilityClass
@Slf4j
public class PathHelper {

    public String exportImagePath() {
        try {
            var folder = new File("./exported_images");
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    log.info("Directory is created!");
                } else {
                    log.debug("Directory is exit!");
                }
            }
            return folder.getPath().concat("/");
        } catch (NullPointerException exception) {
            log.error(exception.getMessage());
            return null;
        }
    }
}
