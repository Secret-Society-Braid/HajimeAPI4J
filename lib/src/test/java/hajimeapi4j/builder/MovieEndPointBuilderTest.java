package hajimeapi4j.builder;

import hajimeapi4j.internal.builder.MovieEndPointBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@Disabled("To prevent spamming the API")
class MovieEndPointBuilderTest {

  @BeforeEach
  void interval() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
  }

  @Test
  void noDataIdentifierTest() {
    final int id = 43; // Apple-pie Princess

    MovieEndPointBuilder builder = MovieEndPointBuilder.createWith(id);

    builder.setPlain(true).build().handleAsync(result -> {
      // assertion
      Assertions.assertFalse(result.get().isPresent());

      Assertions.assertEquals(204, result.getReturnStatusCode());
    });
  }

  @Test
  void withDataIdentifierTest() {
    final int id = 5331;

    MovieEndPointBuilder builder = MovieEndPointBuilder.createWith(id);

    builder.setPlain(true).build().handleAsync(result -> {
      // assertion
      Assertions.assertTrue(result.get().isPresent());

      Assertions.assertEquals(200, result.getReturnStatusCode());
    });
  }

  @Test
  void invalidIdentifierTest() {
    final int id = 0;

    MovieEndPointBuilder builder = MovieEndPointBuilder.createWith(id);

    builder.setPlain(true).build().handleAsync(result -> {
      // assertion
      Assertions.assertFalse(result.get().isPresent());

      Assertions.assertEquals(400, result.getReturnStatusCode());
    });
  }
}
