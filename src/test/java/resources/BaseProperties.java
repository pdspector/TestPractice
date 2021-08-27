package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseProperties {
    private Properties properties = new Properties();

    /**
     * Constructor of the class:
     * -loadPropertiesFile
     */
    public BaseProperties() {
        loadPropertiesFile();
    }

    /**
     * Set the location of config.properties and load properties from this file
     */
    private void loadPropertiesFile() {
        FileInputStream fileInput = null;
        InputStream fileInput2 = null;
    	
        File file = new File("src" + File.separator + "test" + File.separator + "java" + File.separator + "resources" + File.separator + "config.properties");
        if (file.getAbsolutePath().contains("tmp") || file.getAbsolutePath().contains("private")) {
        	fileInput2 = getClass().getResourceAsStream("/resources/config.properties"); 
        } else {
	        try {
	            fileInput = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            throw new RuntimeException("The path to the config.properties file is not valid." + e);
	        }
	    }
        
        //load properties file
        if (file.getAbsolutePath().contains("tmp") || file.getAbsolutePath().contains("private")) {

	        try {
	            properties.load(fileInput2);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        } else {
        	try {
	            properties.load(fileInput);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
    }

    public String getLocalPath() {
        return properties.getProperty("LOCAL_PATH");
    }

    public String getValue(String propertyName) {
        return properties.getProperty(propertyName);
    }
    
    public void setValue(String key, String value) {
    	properties.setProperty(key, value);
    }
}
