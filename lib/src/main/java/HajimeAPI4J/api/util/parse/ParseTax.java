package HajimeAPI4J.api.util.parse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseTax {

    private Logger logger = LoggerFactory.getLogger(ParseTax.class);
    private JsonNode node = null;
    private Map<String, Object> map = null;

    private static final TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};

    public ParseTax(JsonNode node) {
        this.node = node;
    }

    public ParseTax converse() {
        try {
            map = new ObjectMapper().readValue(node.traverse(), typeRef);
        } catch (IOException e) {
            logger.error("Exception while parsing json to Tax", e);
        }
        return this;
    }

    public Map<String, Object> parse() {
        return map;
    }

    public String getName() {
        return (String) map.get("name");
    }

    public String getType() {
        return (String) map.get("type");
    }

    public int getTaxId() {
        return (int) map.get("tax_id");
    }

    public String getLink() {
        return (String) map.get("link");
    }

    public String getApi() {
        return (String) map.get("api");
    }

    public String getKana() {
        return (String) map.get("kana");
    }

    public String getCv() {
        return (String) map.get("cv");
    }

    public String getCvKana() {
        return (String) map.get("cvkana");
    }

    public String getProduction() {
        return (String) map.get("production");
    }

    public List<Map<String, Object>> getSong() {
        try {
            return new ObjectMapper().readValue(node.get("song").traverse(), new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            logger.error("Exception while parsing json to list", e);
            return Collections.emptyList();
        }
    }
    
}
