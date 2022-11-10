package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.internal.endpoint.ListEndPointImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ListEndPointDataParseTest {

  private static final Logger log = LoggerFactory.getLogger(ListEndPointDataParseTest.class);
  private static JsonNode template;

  @BeforeAll
  static void setUp() {
    try {
      template = new ObjectMapper().readTree(ListEndPointDataParseTest.class.getResource(
          "/dataClassTemplate/listWithMusicResponse.json"));
    } catch (IOException e) {
      fail(e);
    }
  }


  @Test
  void createInstanceTest() {
    List<ListEndPoint> testInstance = new ArrayList<>();
    Iterator<JsonNode> iterable = template.elements();
    while (iterable.hasNext()) {
      JsonNode tmp = iterable.next();
      testInstance.add(ListEndPointImpl.createInstance(
          tmp.get("name").asText(),
          tmp.get("type").asText(),
          tmp.get("song_id").asInt(),
          tmp.get("link").asText(),
          tmp.get("api").asText(),
          tmp.get("music_type").asText(),
          null,
          null,
          null,
          null,
          null)
      );
    }

    //assertions

    assertEquals(9, testInstance.size());

  }

}