package controller.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Yuliia Tesliuk
 */
public class PropertiesLoader {

    public Properties getLoadedProperties(String propertiesFileName){
        Properties properties = new Properties();
        try{
            ClassLoader loader = this.getClass().getClassLoader();
            properties.load(loader.getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            //TODO log can't connect
        }
        return properties;
    }
}
