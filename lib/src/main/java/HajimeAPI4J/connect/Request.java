package HajimeAPI4J.connect;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.exception.IllegalParameterException;
import HajimeAPI4J.responses.ResponseListProperty;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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

    private String requestURL;

    protected static final String[] availableTokens = {"list", "tax", "music"};

    public static final int LIST = 0;
    public static final int TAX = 1;
    public static final int MUSIC = 2;

    //Declare logger
    private Logger logger = LoggerFactory.getLogger(Request.class);

    public Request(int property) {
        this(availableTokens[property]);
    }

    public Request(String token) {
        if(!Arrays.asList(availableTokens).contains(token)) {
            throw new IllegalParameterException("Invaild token.");
        }
        logger.info("Attempting to send request with: {}", token);
        this.requestURL = HajimeAPI4J.BASE_URI + token;
    }

    public List<Map<String, Object>> listToken(Map<String, Object> param) throws IOException {
        String paramString = parseParam(param);
        requestURL = requestURL + paramString;
        TypeReference<List<Map<String, Object>>> reference = new TypeReference<List<Map<String,Object>>>() {};
        return new ObjectMapper().readValue(new URL(requestURL), reference);
    }

    public Map<String, Object> taxOrMusic(Map<String, Object> param) throws IOException {
        String paramString = parseParam(param);
        requestURL = requestURL + paramString;
        TypeReference<Map<String, Object>> reference = new TypeReference<Map<String, Object>>() {};
        return new ObjectMapper().readValue(new URL(requestURL), reference);
    }

    public String parseParam(Map<String, Object> parameter) {
        if(parameter.isEmpty()) {
            throw new IllegalParameterException("Invalid parameter");
        }
        StringBuilder result = new StringBuilder();
        parameter.forEach((key, value) -> result.append("?" + key + "=" + value.toString()));
        return result.toString();
    }
}