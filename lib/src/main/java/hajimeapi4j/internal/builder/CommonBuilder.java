package hajimeapi4j.internal.builder;

import hajimeapi4j.experimental.RateLimit;
import jakarta.annotation.Nonnull;

public abstract class CommonBuilder {

  protected RateLimit rateLimit;

  protected CommonBuilder() {
    this.rateLimit = new RateLimit(1, 1, 1000, 50, 2);
  }

  protected CommonBuilder(@Nonnull RateLimit rateLimit) {
    this.rateLimit = rateLimit;
  }

  /**
   * レートリミットを設定します。
   *
   * @param rateLimit 新しいレートリミット
   * @return チェーンのためのこのインスタンス
   */
  public CommonBuilder setRateLimit(@Nonnull RateLimit rateLimit) {
    this.rateLimit = rateLimit;
    return this;
  }
}
