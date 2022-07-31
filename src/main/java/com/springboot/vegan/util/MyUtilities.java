package com.springboot.vegan.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MyUtilities {

    public static String saveFile(MultipartFile multiPart, String path) {
        // Obtain the name of the original file
        String nameOriginal = multiPart.getOriginalFilename();

        try {
            // Create the name of the file to save it on the HD
            File imageFile = new File(path + nameOriginal);
            System.out.println("File: " + imageFile.getAbsolutePath());

            // Store the file on the HD
            multiPart.transferTo(imageFile);
            return nameOriginal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
}
