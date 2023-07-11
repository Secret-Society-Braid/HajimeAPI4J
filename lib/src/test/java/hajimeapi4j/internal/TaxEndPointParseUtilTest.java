package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Song;
import hajimeapi4j.util.ParseUtil;
import hajimeapi4j.util.TestingUtil;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TaxEndPointParseUtilTest {

  private static final Logger log = LoggerFactory.getLogger(TaxEndPointParseUtilTest.class);

  @Test
  void constructTest() {
    ObjectMapper mapper = TestingUtil.getMapperInstance();
    TaxEndPoint instance = null;
    try {
      JsonNode responseMock = mapper.readTree(TaxEndPointParseUtilTest.class.getResourceAsStream(
          "/dataClassTemplate/tax/taxResponseData.json"));
      instance = ParseUtil.createTaxResponse(responseMock);
    } catch (IOException e) {
      fail(e);
    }

    // assertions
    assertNotNull(instance);

    List<Member> members = instance.getMember().orElse(Collections.emptyList());
    List<Song> songs = instance.getSong();

    assertFalse(members.isEmpty());
    assertFalse(songs.isEmpty());
  }

  @Test
  void singleIdolParseTest() {
    ObjectMapper mapper = TestingUtil.getMapperInstance();
    TaxEndPoint instance = null;
    try {
      JsonNode responseMock = mapper.readTree(TaxEndPointParseUtilTest.class.getResourceAsStream(
          "/dataClassTemplate/tax/taxSingleIdolResponse.json"));
      instance = ParseUtil.createTaxResponse(responseMock);
    } catch (IOException e) {
      fail(e);
    }

    // assertions
    assertNotNull(instance);
    log.debug(instance.toString());

    assertNotNull(instance.getName());
    assertNotNull(instance.getType());
    assertNotEquals(0, instance.getTaxId());
    assertNotNull(instance.getLink());
    assertNotNull(instance.getApi());
    assertTrue(instance.getKana().isPresent());
    assertTrue(instance.getCv().isPresent());
    assertTrue(instance.getCvKana().isPresent());
    assertTrue(instance.getProduction().isPresent());

    assertEquals(8, instance.getSong().size());
  }

  @Test
  void plainMemberResponseTest() {
    ObjectMapper mapper = TestingUtil.getMapperInstance();
    TaxEndPoint instance = null;

    try {
      instance = mapper.readValue(TaxEndPointParseUtilTest.class.getResourceAsStream("/dataClassTemplate/v3/tax/plainMemberUnitResponse.json"), TaxEndPoint.class);
    } catch (IOException e) {
      fail(e);
    }

    // assertions
    assertNotNull(instance);

    assertNotNull(instance.getName());
    assertNotNull(instance.getType());
    assertNotEquals(0, instance.getTaxId());
    assertNotNull(instance.getLink());
    assertNotNull(instance.getApi());

    assertEquals(5, instance.getSong().size());
    instance.getMember().orElse(Collections.emptyList()).forEach(member -> {
      assertNotNull(member.getName());
      assertThrows(UnsupportedOperationException.class, member::getType);
    });
  }
}
