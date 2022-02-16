package HajimeAPI4J.api;

import java.util.LinkedHashMap;
import java.util.Objects;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.util.Checks;
import HajimeAPI4J.api.util.HajimeAPI4JImpl;
import HajimeAPI4J.exception.IllegalParameterException;

public class HajimeAPIBuilder {
    
    private Logger logger = LoggerFactory.getLogger(HajimeAPIBuilder.class);

    private String token = null;
    private LinkedHashMap<String, String> params = new LinkedHashMap<>();


    private static final String BASE_URI = "https://api.fujiwarahaji.me/v1/";

    private HajimeAPIBuilder(String token) {
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

    public HajimeAPIBuilder addParameter(HajimeAPI4J.List_Params param, String... values) {
        Checks.requireSameToken(HajimeAPI4J.Token.LIST, token);
        Checks.availableListParam(param);
        if(Checks.softRequireNonNull(params)) {
            if(params.containsValue(HajimeAPI4J.List_Type.MUSIC.toString())) {
                if(!param.canApplyForMusicToken())
                    throw new IllegalParameterException("This parameter cannot be applied for music token.");
            } else {
                if(!param.canApplyForClassificationToken())
                    throw new IllegalParameterException("This parameter cannot be applied for classification token.");
            }
        }
        if(Checks.softRequireNonNull(values)) {
            if(param.isAllowedArrayInput()) {
                StringBuilder b = new StringBuilder();
                for(String value : values) {
                    b.append(value).append(",");
                }
                params.put(param.toString(), b.toString());
            } else if (values.length > 1) {
                throw new IllegalParameterException("The parameter " + param.toString() + " does not allow array input.");
            } else {
                params.put(param.toString(), values[0]);
            }
        } else {
            params.put(param.toString(), "");
        }
        return this;
    }

    public HajimeAPIBuilder addParameter(HajimeAPI4J.Tax_Params param, String... values) {
        Checks.requireSameToken(HajimeAPI4J.Token.TAX, token);
        Checks.availableTaxParam(param);
        if(Checks.softRequireNonNull(values)) {
            if(param.isAllowedArrayInput()) {
                StringBuilder b = new StringBuilder();
                for(String value : values) {
                    b.append(value).append(",");
                }
                params.put(param.toString(), b.toString());
            } else if (values.length > 1) {
                throw new IllegalParameterException("The parameter " + param.toString() + " does not allow array input.");
            } else {
                params.put(param.toString(), values[0]);
            }
        } else {
            params.put(param.toString(), "");
        }
        return this;
    }

    public HajimeAPIBuilder addParameter(HajimeAPI4J.Music_Params param, String... values) {
        Checks.requireSameToken(HajimeAPI4J.Token.MUSIC, token);
        Checks.availableMusicParam(param);
        if(Checks.softRequireNonNull(values)) {
            if(param.isAllowedArrayInput()) {
                StringBuilder b = new StringBuilder();
                for(String value : values) {
                    b.append(value).append(",");
                }
                params.put(param.toString(), b.toString());
            } else if (values.length > 1) {
                throw new IllegalParameterException("The parameter " + param.toString() + " does not allow array input.");
            } else {
                params.put(param.toString(), values[0]);
            }
        } else {
            params.put(param.toString(), "");
        }
        return this;
    }

    public HajimeAPI4JImpl build() throws NullPointerException {
        Checks.hardRequireNonNull(token);
        HajimeAPI4J.Token tokenEnumed = null;
        for(HajimeAPI4J.Token tmp : HajimeAPI4J.Token.values()) {
            if(Objects.equals(tmp.toString(), this.token)) {
                tokenEnumed = tmp;
                break;
            }
        }
        Checks.hardRequireNonNull(tokenEnumed);
        HajimeAPI4JImpl api = new HajimeAPI4JImpl();
        api.setToken(tokenEnumed);
        api.setCache(false);
        api.setParams(params);
        api.setStatus(HajimeAPI4J.Status.NOT_INITIALIZED);
        logger.info("Client building complete. Status: INITIALIZED");
        logger.info("set token : {}", tokenEnumed);
        logger.info("cache status: {}", false);
        params.forEach((k, v) -> logger.info("param: {} = {}", k, v));
        return api;
    }

    /**
     * BASE_URIを取得します。
     * @return BASE_URI
     */
    public static String getBaseURI() {
        return BASE_URI;
    }

}
