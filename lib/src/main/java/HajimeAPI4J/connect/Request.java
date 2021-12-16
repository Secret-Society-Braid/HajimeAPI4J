package HajimeAPI4J.connect;

import HajimeAPI4J.exception.IllegalParameterException;
import HajimeAPI4J.responses.ResponseListProperty;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ふじわらはじめAPIへ「/list?type=music」のリクエストを送信します。
 * @author Ranfa
 * @since 1.0.0
 *
 */
public class Request {

    private static String requestURL;
    

    //Declare logger
    private static Logger logger = LoggerFactory.getLogger(Request.class);


    private Request() {
        throw new UnsupportedOperationException("Util class.");
    }

    public static List<Map<String, Object>> list(Map<String, Object> param) throws IOException {
        requestURL = requestURL + "/list";
        logger.info("Attempting to send request with: list");
        String paramString = parseParam(param);
        requestURL = requestURL + paramString;
        return new ObjectMapper().readValue(new URL(requestURL), ResponseListProperty.class).getResponseList();
    }

    public static Map<String, Object> tax(Map<String, Object> param) throws IOException {
        requestURL = requestURL + "/tax";
        logger.info("Attempting to send request with: tax");
        String paramString = parseParam(param);
        requestURL = requestURL + paramString;
        TypeReference<Map<String, Object>> reference = new TypeReference<Map<String, Object>>() {};
        return new ObjectMapper().readValue(new URL(requestURL), reference);
    }

    public static Map<String, Object> music(Map<String, Object> param) throws IOException {
        requestURL = requestURL + "/music";
        logger.info("Attempting to send request with: music");
        String paramString = parseParam(param);
        requestURL = requestURL + paramString;
        TypeReference<Map<String, Object>> reference = new TypeReference<Map<String, Object>>() {};
        return new ObjectMapper().readValue(new URL(requestURL), reference);
    }

    private static String parseParam(Map<String, Object> parameter) {
        if(parameter.isEmpty()) {
            throw new IllegalParameterException("Invalid parameter");
        }
        StringBuilder result = new StringBuilder();
        parameter.forEach((key, value) -> result.append("?" + key + "=" + value));
        return result.toString();
    } 
}