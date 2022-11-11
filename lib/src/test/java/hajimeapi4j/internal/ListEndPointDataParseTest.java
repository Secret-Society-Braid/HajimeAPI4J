package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.util.InternalUtils;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ListEndPointDataParseTest {

  private static final Logger log = LoggerFactory.getLogger(ListEndPointDataParseTest.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static JsonNode musicTemplate;
  private static JsonNode idolTemplate;

  @BeforeAll
  static void setUp() {
    try {
      musicTemplate = MAPPER.readTree(ListEndPointDataParseTest.class.getResource(
          "/dataClassTemplate/listWithMusicResponse.json"));
      idolTemplate = MAPPER.readTree(ListEndPointDataParseTest.class.getResource(
          "/dataClassTemplate/listWithIdolAtShinyColors.json"));
    } catch (IOException e) {
      fail(e);
    }
  }


  @Test
  void createInstanceTest() {
    List<ListEndPoint> testInstance = InternalUtils.generateListEndPointResponse(musicTemplate);

    //assertions

    // size should be 9
    assertEquals(10, testInstance.size());

    // Base abstract classes should be able to access.
    testInstance.stream()
        .map(EndPoint::getName)
        .forEachOrdered(each -> assertFalse(
            Strings.isNullOrEmpty(each)));

    // this data is requested with music query type so that we should contain Optional.empty()
    testInstance.stream()
        .map(ListEndPoint::getCv)
        .forEachOrdered(each -> assertFalse(
            each.isPresent()));

    // song_id and tax_id must have the same id value
    testInstance
        .forEach(each -> assertEquals(
            each.getSongId(), each.getTaxId()));
  }

  @Test
  void listShinyColorsIdolTest() {
    List<ListEndPoint> idolData = InternalUtils.generateListEndPointResponse(idolTemplate);

    // assertions
    // validate that element size is 27
    assertEquals(27, idolData.size());

    // validate that each element's type is idol
    idolData.forEach(each -> assertEquals(
        "idol", each.getType()));

    // validate that each elements' production is sc
    idolData.forEach(each -> assertEquals(
        "sc", each.getProduction().orElse("")));

    // validate that each element must have cv and cvkana value
    idolData.forEach(each -> {
      assertTrue(each.getCv().isPresent());
      assertTrue(each.getCvKana().isPresent());
    });
  }
}