package hajimeapi4j.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.internal.builder.ListEndPointBuilder;
import hajimeapi4j.util.enums.ListParameter.Type;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListEndPointBuilderTest {

  @Test
  void musicTypeTest() {
    ListEndPointBuilder builder = ListEndPointBuilder.createFor(Type.MUSIC);
    builder
        .setLimit(10)
        .setSearch("器");
    List<ListEndPoint> list = builder.build().complete();

    assertNotNull(list);

    assertEquals(10, list.size());

    list.stream()
        .map(EndPoint::getName)
        .forEachOrdered(each -> assertFalse(
            Strings.isNullOrEmpty(each)
        ));

    list.stream()
        .map(ListEndPoint::getMusicType)
        .map(Optional::isPresent)
        .forEach(Assertions::assertTrue);

    list.stream()
        .map(ListEndPoint::getProduction)
        .map(Optional::isPresent)
        .forEach(Assertions::assertFalse);
  }

}
