package com.payconiq.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String baseUrl;
    public static String username;
    public static String password;
    private final static Logger logger = LoggerFactory.getLogger(Config.class);
    static {
        Properties properties = new Properties();
        try {
            String env = System.getProperty("env.USER");
            if (env == null || env.isEmpty()) {
                properties.load(new FileReader("src/main/resources/config/applicationUnderTest.properties"));

            } else {
                String configFile= "src/main/resources/config/";
                String propertiesVariable= configFile.concat(env).concat(".properties");
                properties.load(new FileReader(propertiesVariable));
                logger.debug("Environment Properties Path {}", propertiesVariable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            baseUrl = properties.getProperty("baseUrl");
            username= properties.getProperty("username");
            password=properties.getProperty("password");
        }
    }
}
