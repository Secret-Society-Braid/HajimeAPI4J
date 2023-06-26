package hajimeapi4j.internal.datatype.utilizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

class SongTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static List<Song> data;

  @BeforeAll
  static void setUp() {
    try {
      TaxEndPoint mock = MAPPER.readValue(SongTest.class.getResourceAsStream("/dataClassTemplate/v3/tax/discResponse.json"), TaxEndPoint.class);
      data = mock.getSong();
    } catch (IOException e) {
      fail(e);
    }
    Assertions.assertFalse(data.isEmpty());
  }

  @Test
  void getMusicType() {
    data
      .parallelStream()
      .map(Song::getMusicType)
      .forEach(t -> Assertions.assertEquals("ml", t));
  }

  @Test
  void getYoutube() {
    data
      .parallelStream()
      .map(Song::getYoutube)
      .map(Optional::isPresent)
      .forEach(Assertions::assertTrue);
  }

  @Test
  void getFlag() {
    data
      .parallelStream()
      .map(Song::getFlag)
      .map(l -> l.orElse(Collections.emptyList()))
      .map(List::isEmpty)
      .forEach(Assertions::assertFalse);
  }
}