package HajimeAPI4J.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HajimeAPIBuilder {
    
    private Logger logger = LoggerFactory.getLogger(HajimeAPIBuilder.class);

    private String token = null;
    private String type = null;
    private int limit = Integer.MIN_VALUE;
    private Map<String, String> param = new HashMap<>();


    private static final String BASE_URI = "https://api.fujiwarahaji.me/v1/";

    public HajimeAPIBuilder(@Nonnull String token) {
        this.token = token;
    }

    public static HajimeAPIBuilder createDefault(@Nonnull HajimeAPI4J.Token token) {
        return createDefault(token.toString());
    }

    public static HajimeAPIBuilder createDefault(@Nonnull String token) {
        return new HajimeAPIBuilder(token).applyDefault();
    }

    private HajimeAPIBuilder applyDefault() {
        return this;
    }

    public HajimeAPIBuilder addParameter(String paramName, String value) {
        Objects.requireNonNull(paramName, "Parameter name must not be null.");
        Objects.requireNonNull(value, "Parameter value must not be null.");
        if(this.token.equals(HajimeAPI4J.Token.LIST.toString())) {
            
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(BASE_URI);
        return sb;
    }

}
