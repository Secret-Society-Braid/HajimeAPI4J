package hajimeapi4j.api.endpoint;

import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Music;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

public interface TaxEndPoint extends EndPoint {

  Optional<String> getKana();

  Optional<String> getCv();

  Optional<String> getCvKana();

  Optional<String> getProduction();

  Optional<String> getDate();

  Optional<String> getPlace();

  Optional<List<? extends Member>> getMember();

  Optional<Boolean> getSetListExists();

  Optional<EndPoint> getLyrics();

  Optional<EndPoint> getComposer();

  Optional<EndPoint> getArrange();

  @Nonnull
  List<Music> getMusic();

}
