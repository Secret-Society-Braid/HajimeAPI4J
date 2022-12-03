package hajimeapi4j.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.common.base.Strings;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.internal.builder.MusicEndPointBuilder;
import hajimeapi4j.util.enums.MusicParameter.Hide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MusicEndPointBuilderTest {

  @Test
  @Disabled("to prevent from calling massive API")
  void musicTypeTest() {
    MusicEndPointBuilder builder = MusicEndPointBuilder.createWith(3525);
    builder.setHide(
        Hide.CD_MEMBER,
        Hide.LIVE_MEMBER
    );
    MusicEndPoint endPoint = builder.build().complete();

    assertNotNull(endPoint);

    assertEquals(3525, endPoint.getSongId());

    assertFalse(Strings.isNullOrEmpty(endPoint.getName()));
  }
}
