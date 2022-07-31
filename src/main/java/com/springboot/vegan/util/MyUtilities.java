package com.springboot.vegan.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MyUtilities {

    public static String saveFile(MultipartFile multiPart, String path) {
        // Obtain the name of the original file
        String nameOriginal = multiPart.getOriginalFilename();
        nameOriginal = nameOriginal.replace(" ", "-");
        int numOfChars = 8;
        String nameFinal = randomAlphaNumeric(numOfChars) + "-" + nameOriginal;

        try {
            // Create the name of the file to save it on the HD
            /*File imageFile = new File(path + nameOriginal);*/
            File imageFile = new File(path + nameFinal);
            System.out.println("File: " + imageFile.getAbsolutePath());

            // Store the file on the HD
            multiPart.transferTo(imageFile);
            /*return nameOriginal;*/
            return nameFinal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    /** Method to generate a random string of characters of length = N
     * @param //count
     * @return
     * */
    public static String randomAlphaNumeric(int count) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- !=0) {
            int character = (int) (Math.random() * CHARACTERS.length());
            builder.append(CHARACTERS.charAt(character));
        }
        return builder.toString();
    }
}
