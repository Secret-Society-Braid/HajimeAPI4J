package hajimeapi4j.internal.request;

import hajimeapi4j.experimental.RateLimit;
import java.util.concurrent.CompletableFuture;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestAdapter {

  private static RequestAdapter instance;
  private final OkHttpClient client;

  static RequestAdapter getInstance(final OkHttpClient client) {
    if (instance == null) {
      instance = new RequestAdapter(client);
    }
    return instance;
  }

  CompletableFuture<Response> queueRequest(String uri, RateLimit rateLimit) {
    return rateLimit.submit(() -> {
      final Request request = new Request.Builder()
        .url(uri)
        .get()
        .addHeader("User-Agent", "HajimeAPI4J java wrapper developed by @hizumiaoba")
        .build();
      return client.newCall(request).execute();
    });
  }
}
