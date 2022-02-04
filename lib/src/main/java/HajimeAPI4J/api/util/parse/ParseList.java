package HajimeAPI4J.api.util.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseList {

    private Logger logger = LoggerFactory.getLogger(ParseList.class);
    private JsonNode node = null;
    private List<Map<String, String>> list = null;

    public ParseList(JsonNode node) {
	this.node = node;
    }

    public ParseList converse() {
        List<Map<String, Object>> res = null;
        try {
            res = new ObjectMapper().readValue(node.traverse(), new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            logger.error("Exception while parsing json to list", e);
        }
        validation(res);
        return this;
    }

    protected void validation(List<Map<String, Object>> data) {
        Map<String, String> map = new HashMap<>();
        list = new ArrayList<>();
        data.stream().forEach(e -> {
            e.entrySet().stream().forEach(f -> map.put(f.getKey(), f.getValue().toString()));
            list.add(map);
        });
    }

    public List<Map<String, String>> parse() {
        return list;
    }
}
