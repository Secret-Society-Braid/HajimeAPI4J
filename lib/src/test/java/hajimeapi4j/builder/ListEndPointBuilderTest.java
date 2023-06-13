package hajimeapi4j.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.internal.builder.ListEndPointBuilder;
import hajimeapi4j.util.enums.ListParameter.MusicType;
import hajimeapi4j.util.enums.ListParameter.Order;
import hajimeapi4j.util.enums.ListParameter.Type;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListEndPointBuilderTest {

  @Test
  void mockedTypeTest() {

    List<ListEndPoint> list = null;
    try {
      list = new ObjectMapper().readValue(
          ListEndPointBuilderTest.class.getResourceAsStream(
              "/dataClassTemplate/list/listWithMusicResponse.json"),
          new TypeReference<>() {
          });
    } catch (IOException e) {
      fail(e);
    }

    assertNotNull(list);
    assertEquals(10, list.size());

    list.parallelStream().map(EndPoint::getName).map(Strings::isNullOrEmpty)
        .forEach(Assertions::assertFalse);

    boolean allType = list.parallelStream().map(EndPoint::getType)
        .allMatch(type -> type.equals("music"));
    assertTrue(allType);

    list.parallelStream().map(ListEndPoint::getCv).map(Optional::isPresent)
        .forEach(Assertions::assertFalse);
  }

  @Test
  @Disabled("to prevent from calling massive API")
  void musicTypeTest() {
    ListEndPointBuilder musicBuilder = ListEndPointBuilder.createFor(Type.MUSIC);
    musicBuilder.setLimit(5).setMusicType(MusicType.CINDERELLA_GIRLS).setOrder(Order.DESCENDING);

    List<ListEndPoint> musicList = musicBuilder.build().handleSync();

    assertNotNull(musicList);
    assertEquals(5, musicList.size());

    musicList.stream().map(EndPoint::getName).map(Strings::isNullOrEmpty)
        .forEach(Assertions::assertFalse);

    boolean allType = musicList.stream().map(EndPoint::getType)
        .allMatch(type -> type.equals("music"));
    assertTrue(allType);

    musicList.stream().map(ListEndPoint::getCv).map(Optional::isPresent)
        .forEach(Assertions::assertFalse);
  }

}
