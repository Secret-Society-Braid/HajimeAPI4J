package hajimeapi4j.api.endpoint;

import java.util.Optional;

public interface ListEndPoint extends EndPoint {

  Optional<String> getMusicType();

  Optional<String> getMusicType(String nullValue);

  Optional<String> getDate();

  Optional<String> getDate(String nullValue);

  Optional<String> getProduction();

  Optional<String> getProduction(String nullValue);

  Optional<String> getKana();

  Optional<String> getKana(String nullValue);

  Optional<String> getCv();

  Optional<String> getCv(String nullValue);

  Optional<String> getCvKana();

  Optional<String> getCvKana(String nullValue);

}
