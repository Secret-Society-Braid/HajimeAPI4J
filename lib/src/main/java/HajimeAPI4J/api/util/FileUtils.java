package HajimeAPI4J.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

    private FileUtils() { /* do nothing */ }

    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static final String ENV_FILE_NAME = "env.json";

    private static final TypeReference<Map<String, String>> TYPE_REFERENCE = new TypeReference<>() {
    };

    public static Map<String, String> readFilesFromResourceFolder(String filename) {
        try (InputStream is = FileUtils.class.getResourceAsStream("/" + filename)) {
            return new ObjectMapper().readValue(is, TYPE_REFERENCE);
        } catch (IOException e) {
            LOG.error("Cannot read resource file from disk.", e);
        }
        return Collections.emptyMap();
    }
    
}
