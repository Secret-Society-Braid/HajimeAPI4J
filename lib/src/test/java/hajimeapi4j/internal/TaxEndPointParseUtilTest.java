package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

class TaxEndPointParseUtilTest {

  @Test
  void constructTest() {
    ObjectMapper mapper = TestingUtil.getMapperInstance();
    TaxEndPoint instance = null;
    try {
      JsonNode responseMock = mapper.readTree(TaxEndPointParseUtilTest.class.getResourceAsStream(
          "/dataClassTemplate/taxResponseData.json"));
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
}
