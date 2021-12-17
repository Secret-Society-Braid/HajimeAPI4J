package mock;

import java.util.Map;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.exception.IllegalParameterException;

public class ParseURLMock {

    private static final String BASE = HajimeAPI4J.BASE_URI;

    public static String parseURL(Map<String, Object> param) {
        if(param.isEmpty()) {
            throw new IllegalParameterException("Invalid parameter");
        }
        StringBuilder result = new StringBuilder(BASE);
        result.append("list");
        param.forEach((key, value) -> result.append("?" + key + "=" + value.toString()));
        return result.toString();
    }
    
}
