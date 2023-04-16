package hajimeapi4j.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.internal.builder.ListEndPointBuilder;
import hajimeapi4j.util.enums.ListParameter.MusicType;
import hajimeapi4j.util.enums.ListParameter.Order;
import hajimeapi4j.util.enums.ListParameter.Type;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ListEndPointBuilderTest {

  @Test
  @Disabled("to prevent from calling massive API")
  void musicTypeTest() {
    ListEndPointBuilder musicBuilder = ListEndPointBuilder.createFor(Type.MUSIC);
    musicBuilder
        .setLimit(5)
        .setMusicType(MusicType.CINDERELLA_GIRLS)
        .setOrder(Order.DESCENDING);

    List<ListEndPoint> musicList = musicBuilder.build().handleSync();

    assertNotNull(musicList);
    assertEquals(5, musicList.size());

    musicList.stream()
        .map(EndPoint::getName)
        .map(Strings::isNullOrEmpty)
        .forEach(Assertions::assertFalse);

    boolean allType = musicList.stream()
        .map(EndPoint::getType)
        .allMatch(type -> type.equals("music"));
    assertTrue(allType);

    musicList.stream()
        .map(ListEndPoint::getCv)
        .map(Optional::isPresent)
        .forEach(Assertions::assertFalse);
  }

}
