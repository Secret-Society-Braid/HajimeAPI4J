package hajimeapi4j.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
          "/dataClassTemplate/music/musicResponseData.json"));
      instance = ParseUtil.createMusicResponse(responseMock);
    } catch (IOException e) {
      fail(e);
    }
    // assertions

    assertNotNull(instance);

    // assertion for arrays
    assertTrue(instance.getLyrics().isPresent());
    assertTrue(instance.getComposer().isPresent());
    assertTrue(instance.getArrange().isPresent());
    assertTrue(instance.getDisc().isPresent());
    assertTrue(instance.getLive().isPresent());

    assertFalse(instance.getMember().isEmpty());

    // assertion for other fields
    assertNotNull(instance.getName());
    assertNotNull(instance.getType());
    assertNotEquals(0, instance.getSongId());
    assertEquals(0, instance.getTaxId());
    assertNotNull(instance.getLink());
    assertNotNull(instance.getApi());
    assertNotNull(instance.getLyricsUrl());
    assertTrue(instance.getDigitalReleaseExists());
  }

}
