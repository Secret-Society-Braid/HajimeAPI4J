package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import hajimeapi4j.util.ParseUtil;
import hajimeapi4j.util.TestingUtil;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
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

    List<EndPoint> lyrics = instance.getLyrics().orElse(Collections.emptyList());
    List<EndPoint> composer = instance.getComposer().orElse(Collections.emptyList());
    List<EndPoint> arrange = instance.getArrange().orElse(Collections.emptyList());
    List<Member> member = instance.getMember();
    List<Disc> disc = instance.getDisc().orElse(Collections.emptyList());
    List<Live> live = instance.getLive().orElse(Collections.emptyList());

    assertFalse(lyrics.isEmpty());
    assertFalse(composer.isEmpty());
    assertFalse(arrange.isEmpty());
    assertFalse(member.isEmpty());
    assertFalse(disc.isEmpty());
    assertFalse(live.isEmpty());
  }

}
