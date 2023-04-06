package hajimeapi4j.internal.request;

import hajimeapi4j.util.InternalUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestActionImpl<T> extends AbstractRestAction<T> {

  private static final ExecutorService serv = Executors.newSingleThreadExecutor(
      InternalUtils.createInternalThreadFactory(
          "Rest action handshaking thread"
      ));
}
