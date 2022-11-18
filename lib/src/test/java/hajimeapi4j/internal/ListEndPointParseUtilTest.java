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
import hajimeapi4j.util.parse.ListParseUtil;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ListEndPointParseUtilTest {

  @Test
  void constructTest() {
    List<ListEndPoint> list = null;
    try {
      JsonNode responseMock = new ObjectMapper().readTree(
          ListEndPointParseUtilTest.class.getResourceAsStream(
              "/dataClassTemplate/listWithMusicResponse.json"));

      list = ListParseUtil.createResponse(responseMock);
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
        .map(ListEndPoint::getCv)
        .forEachOrdered(each -> assertFalse(
            each.isPresent()));
  }

}
