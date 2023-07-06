package hajimeapi4j.internal;

import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.builder.ListEndPointBuilder;
import hajimeapi4j.internal.builder.MusicEndPointBuilder;
import hajimeapi4j.internal.builder.TaxEndPointBuilder;
import hajimeapi4j.util.enums.ListParameter;
import hajimeapi4j.util.enums.MusicParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("This test is not for CI")
class RestActionTest {

  @BeforeEach
  void interval() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
  }

  @Test
  void listHandleAsyncTest() {
    // preconditioning
    ListEndPointBuilder builder = ListEndPointBuilder.createFor(ListParameter.Type.MUSIC)
      .setLimit(5)
      .setMusicType(ListParameter.MusicType.MILLION_LIVE);

    // assertion
    builder.build().handleAsync((list) -> {
      // list is not null and length is 5
      assertNotNull(list);
      assertEquals(5, list.size());

      // each list element is not null
      list.forEach(Assertions::assertNotNull);

      // all elements in list must be type of million_live
      list.forEach((element) -> assertTrue(element.getMusicType().isPresent()));
      list.forEach((element) -> assertEquals(ListParameter.MusicType.MILLION_LIVE.toString(), element.getMusicType().orElse("unknown")));
    });
  }

  @Test
  void taxHandleSyncTest() {
    // preconditioning
    TaxEndPointBuilder builder = TaxEndPointBuilder.createWithIdolName("天海春香");
    TaxEndPoint response = builder.build().handleSync();

    // assertion
    // response is not null
    assertNotNull(response);

    // assert each field
    assertEquals("天海春香", response.getName());
    assertEquals("idol", response.getType());
    assertEquals(1107, response.getTaxId());
    assertEquals("あまみはるか", response.getKana().orElse("unknown"));
    assertEquals("765", response.getProduction().orElse("unknown"));
    assertFalse(response.getSong().isEmpty());
    //
  }

  @Test
  void musicHandleAsyncTest() {
    // preconditioning
    MusicEndPointBuilder builder = MusicEndPointBuilder.createWith(5331); // Crossing!
    builder.setHide(MusicParameter.Hide.CD_MEMBER, MusicParameter.Hide.LIVE_MEMBER);

    // assertion
    CompletableFuture<MusicEndPoint> response = builder.build().handleAsync();

    response.thenAccept((music) -> {
      // music is not null
      assertNotNull(music);

      // assert each field
      assertEquals(5331, music.getSongId());
      assertEquals("Crossing!", music.getName());
      assertEquals("music", music.getType());
      assertTrue(music.getComposer().isPresent());
      assertTrue(music.getLyrics().isPresent());
      assertFalse(music.getMember().isEmpty());
    });
  }
}
