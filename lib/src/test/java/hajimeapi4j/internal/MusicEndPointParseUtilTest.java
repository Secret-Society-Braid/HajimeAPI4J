package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.util.ParseUtil;
import hajimeapi4j.util.TestingUtil;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MusicEndPointParseUtilTest {

  @Test
  void constructTest() {
    ObjectMapper mapper = TestingUtil.getMapperInstance();
    MusicEndPoint instance = null;
    try {
      JsonNode responseMock = mapper.readTree(MusicEndPointParseUtilTest.class.getResourceAsStream(
          "/dataClassTemplate/musicResponseData.json"));
      instance = ParseUtil.createMusicResponse(responseMock);
    } catch (IOException e) {
      fail(e);
    }
    // assertions

    assertNotNull(instance);
  }

}
