package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import java.util.Optional;

public interface MusicEndPoint extends RestAction<MusicEndPoint> {

  Optional<String> getKana();

  Optional<String> getKana(String nullValue);

}
