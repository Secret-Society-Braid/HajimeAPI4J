package hajimeapi4j.api.endpoint;

import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Arrange;
import hajimeapi4j.internal.datatype.utilizations.Composer;
import hajimeapi4j.internal.datatype.utilizations.Lyrics;
import hajimeapi4j.internal.datatype.utilizations.Music;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

public interface TaxEndPoint extends EndPoint {

  Optional<String> getKana();

  Optional<String> getKana(String nullValue);

  Optional<String> getCv();

  Optional<String> getCv(String nullValue);

  Optional<String> getCvKana();

  Optional<String> getCvKana(String nullValue);

  Optional<String> getProduction();

  Optional<String> getProduction(String nullValue);

  Optional<String> getDate();

  Optional<String> getDate(String nullValue);

  Optional<String> getPlace();

  Optional<String> getPlace(String nullValue);

  Optional<List<? extends Member>> getMember();

  Optional<List<? extends Member>> getMember(List<Member> nullValue);

  Optional<Boolean> getSetListExists();

  Optional<Boolean> getSetListExists(boolean nullValue);

  Optional<Lyrics> getLyrics();

  Optional<Lyrics> getLyrics(Lyrics nullValue);

  Optional<Composer> getComposer();

  Optional<Composer> getComposer(Composer nullValue);

  Optional<Arrange> getArrange();

  Optional<Arrange> getArrange(Arrange nullValue);

  @Nonnull
  List<Music> getMusic();

}
