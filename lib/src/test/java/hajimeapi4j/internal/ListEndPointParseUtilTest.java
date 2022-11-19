package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.util.ParseUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ListEndPointParseUtilTest {

  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  void constructTest() {
    List<ListEndPoint> list = null;
    try {
      JsonNode responseMock = mapper.readTree(
          ListEndPointParseUtilTest.class.getResourceAsStream(
              "/dataClassTemplate/listWithMusicResponse.json"));

      list = ParseUtil.createResponse(responseMock);
    } catch (IOException e) {
      fail(e);
    }
    // assertion

    assertNotNull(list);

    assertEquals(10, list.size());

    list.stream()
        .map(EndPoint::getName)
        .forEachOrdered(each -> assertFalse(
            Strings.isNullOrEmpty(each)
        ));

    list.stream()
        .map(ListEndPoint::getMusicType)
        .map(Optional::isPresent)
        .forEach(Assertions::assertTrue);

    list.stream()
        .map(ListEndPoint::getProduction)
        .map(Optional::isPresent)
        .forEach(Assertions::assertFalse);
  }

  @Test
  void withLiveResponse() {
    List<ListEndPoint> list = null;
    try {
      JsonNode responseMock = mapper.readTree(ListEndPointParseUtilTest.class.getResourceAsStream(
          "/dataClassTemplate/listWithLive.json"));
      list = ParseUtil.createResponse(responseMock);
    } catch (IOException e) {
      fail(e);
    }

    assertNotNull(list);

    assertEquals(397, list.size());

    list.stream()
        .map(EndPoint::getName)
        .map(Strings::isNullOrEmpty)
        .forEach(Assertions::assertFalse);

    list.stream()
        .map(ListEndPoint::getDate)
        .map(Optional::isPresent)
        .forEach(Assertions::assertTrue);

    list.stream()
        .map(ListEndPoint::getMusicType)
        .map(Optional::isPresent)
        .forEach(Assertions::assertFalse);
  }

}
