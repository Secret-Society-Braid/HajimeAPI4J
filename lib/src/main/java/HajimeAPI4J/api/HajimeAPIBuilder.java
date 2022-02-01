package HajimeAPI4J.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.util.Checks;

public class HajimeAPIBuilder {
    
    private Logger logger = LoggerFactory.getLogger(HajimeAPIBuilder.class);

    private String uri = null;
    private String token = null;
    private int limit = Integer.MIN_VALUE;
    private Map<String, String> params = new HashMap<>();


    private static final String BASE_URI = "https://api.fujiwarahaji.me/v1/";

    public HajimeAPIBuilder(String token) {
        this.token = token;
    }

    public static HajimeAPIBuilder createDefault(@Nonnull HajimeAPI4J.Token token) {
        return createDefault(token.toString());
    }

    public static HajimeAPIBuilder createDefault(@Nonnull String token) {
        return new HajimeAPIBuilder(token);
    }

    public static HajimeAPIBuilder create() {
        return new HajimeAPIBuilder(null);
    }

    public HajimeAPIBuilder setToken(@Nonnull HajimeAPI4J.Token token) {
        this.token = token.toString();
        return this;
    }

    public HajimeAPIBuilder addParameter(HajimeAPI4J.List_Params param, String value) {
        Checks.requireSameToken(HajimeAPI4J.Token.LIST, token);
        Checks.availableListParam(param);
        value = Checks.softRequireNonNull(value) ? value : "";
        params.put(param.toString(), value);
        return this;
    }

    public HajimeAPIBuilder addParameter(HajimeAPI4J.Tax_Params param, String value) {
        Checks.requireSameToken(HajimeAPI4J.Token.TAX, token);
        Checks.availableTaxParam(param);
        value = Checks.softRequireNonNull(value) ? value : "";
        params.put(param.toString(), value);
        return this;
    }

    public HajimeAPIBuilder addParameter(HajimeAPI4J.Music_Params param, String value) {
        Checks.requireSameToken(HajimeAPI4J.Token.MUSIC, token);
        Checks.availableMusicParam(param);
        value = Checks.softRequireNonNull(value) ? value : "";
        params.put(param.toString(), value);
        return this;
    }

    public HajimeAPI4J build() {
        StringBuilder sb = new StringBuilder(BASE_URI);
        sb.append(token).append("?");
        
    }

}
