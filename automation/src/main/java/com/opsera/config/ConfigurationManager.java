package com.opsera.config;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
public class ConfigurationManager {
	private Properties properties;

    public ConfigurationManager() {
    	
    	 String filePath = "src/main/resources/config.properties";
         try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
             properties = new Properties();
             properties.load(inputStream);

         } catch (IOException e) {
             e.printStackTrace();
         }
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
