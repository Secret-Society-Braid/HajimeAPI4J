package hajimeapi4j.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

    private FileUtils() { /* do nothing */ }

    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static final String ENV_FILE_NAME = "env.json";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final TypeReference<Map<String, String>> TYPE_REFERENCE = new TypeReference<>() {
    };

    public static Map<String, String> readFilesFromResourceFolder(String filename) throws IOException {
        InputStream is = FileUtils.class.getResourceAsStream("/" + filename);
        Map<String, String> res = MAPPER.readValue(is, TYPE_REFERENCE);
        LOG.debug("Data retrieved. : {}", res);
        return res;
    }
    
}
