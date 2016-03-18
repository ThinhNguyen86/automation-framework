package libraries.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataReader {

    private Properties props;

    public DataReader(String fileName) {
        loadFile(fileName);
    }

    /**
     * Load the default properties file
     */
    public void loadFile(String fileName) {
        props = new Properties();
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);

        if (inputStream != null) {
            try {
                props.load(inputStream);
                inputStream.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get a property from the list
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
