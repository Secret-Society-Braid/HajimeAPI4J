package hajimeapi4j.internal.request;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetailedRequestRestActionImpl<T> extends AbstractRestAction<T> {

  private final String url;

  public DetailedRequestRestActionImpl(@Nonnull String url, Class<T> clazz) {
    super(clazz);
    this.url = url;
  }

  @Nonnull
  @Override
  protected String constructUrl() {
    return this.url;
  }
}
