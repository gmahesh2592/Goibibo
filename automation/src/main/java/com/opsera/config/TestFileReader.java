package com.opsera.config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFileReader {
	 public static String readFileAsString(String filePath) {
	        StringBuilder contentBuilder = new StringBuilder();

	        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
	             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

	            String line;
	            while ((line = reader.readLine()) != null) {
	                contentBuilder.append(line).append("\n");
	            }

	        } catch (IOException e) {
	            throw new RuntimeException("Error reading file: " + filePath, e);
	        }

	        return contentBuilder.toString();
	    }

}
