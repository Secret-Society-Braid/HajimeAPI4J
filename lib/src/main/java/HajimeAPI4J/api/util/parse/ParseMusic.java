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

import HajimeAPI4J.api.util.datatype.Member;

public class ParseMusic {

    private Logger logger = LoggerFactory.getLogger(ParseMusic.class);

    private JsonNode node = null;
    private Map<String, Object> map = null;

    private static final TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
    private static final TypeReference<List<Map<String, Object>>> listTypeRef = new TypeReference<List<Map<String, Object>>>() {};

    public ParseMusic(JsonNode node) {
        this.node = node;
    }

    public ParseMusic converse() {
        try {
            map = new ObjectMapper().readValue(node.traverse(), typeRef);
        } catch (IOException e) {
            logger.error("Exception while parsing json to Music", e);
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

    public int getMusicId() {
        return (int) map.get("music_id");
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

    public String getMusicType() {
        return (String) map.get("music_type");
    }

    public List<Map<String, Object>> getLyrics() {
        try {
            return new ObjectMapper().readValue(node.get("lyrics").traverse(), listTypeRef);
        } catch (IOException e) {
            logger.error("Exception while parsing json to Lyrics", e);
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> getComposer() {
        try {
            return new ObjectMapper().readValue(node.get("composer").traverse(), listTypeRef);
        } catch (IOException e) {
            logger.error("Exception while parsing json to Composer", e);
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> getArrange() {
        try {
            return new ObjectMapper().readValue(node.get("arrange").traverse(), listTypeRef);
        } catch (IOException e) {
            logger.error("Exception while parsing json to Arrange", e);
            return Collections.emptyList();
        }
    }

    public String getLyricsUrl() {
        return (String) map.get("lyrics_url");
    }

    public List<Member> getMember() {
        try {
            return new ObjectMapper().readValue(node.get("member").traverse(), new TypeReference<List<Member>>() {});
        } catch (IOException e) {
            logger.error("Exception while parsing json to Member", e);
            return Collections.emptyList();
        }
    }

    public List
}
