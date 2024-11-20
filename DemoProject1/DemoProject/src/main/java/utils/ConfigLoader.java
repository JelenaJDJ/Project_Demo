package utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/main/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return  configLoader;
    }

    public String getUrl(String url) {
        String prop = properties.getProperty(url);
        if(prop != null)  return prop;
        else throw new RuntimeException("property task1Url is not specified in the config.properties file");
    }

}
