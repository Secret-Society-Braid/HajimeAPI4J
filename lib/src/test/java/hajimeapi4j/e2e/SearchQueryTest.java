package hajimeapi4j.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.internal.builder.ListEndPointBuilder;
import hajimeapi4j.util.enums.ListParameter.MusicType;
import hajimeapi4j.util.enums.ListParameter.Type;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Disabled("To prevent from excessive API calls")
class SearchQueryTest {

  @Test
  void testSearchQuery() {
    ListEndPointBuilder builder = ListEndPointBuilder.createFor(Type.MUSIC);
    builder.setLimit(1).setMusicType(MusicType.CINDERELLA_GIRLS).setSearch("お願い");

    builder.build().handleAsync((result) -> {
      assertEquals(result.size(), 1);
      ListEndPoint d = result.get(0);
      assertEquals(d.getType(), Type.MUSIC.toString());
      assertEquals(d.getMusicType().orElse("unknown"), MusicType.CINDERELLA_GIRLS.toString());
    }).join();
  }
}
