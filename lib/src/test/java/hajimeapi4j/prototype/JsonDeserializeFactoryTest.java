package hajimeapi4j.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import hajimeapi4j.util.SerializeTestDataClass;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDeserializeFactoryTest {

  private static final ObjectMapper MAPPER;
  private static final Logger log = LoggerFactory.getLogger(JsonDeserializeFactoryTest.class);

  static {
    MAPPER = new ObjectMapper();
    MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  public static SerializeTestDataClass parseJson() throws IOException {
    return MAPPER.readValue(
        JsonDeserializeFactoryTest.class.getResourceAsStream("/prototypeData/exampleJsonData.json"),
        SerializeTestDataClass.class);
  }

  @Test
  void test() {
    try {
      SerializeTestDataClass testee = parseJson();

      assertEquals(0, testee.getSongId());
      log.info("deserialized data: {}", testee);
    } catch (IOException e) {
      fail(e);
    }
  }
}
