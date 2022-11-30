package hajimeapi4j.internal.builder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code tax} エンドポイントにリクエストを送るためのビルダークラスです。
 *
 * @see hajimeapi4j.api.endpoint.TaxEndPoint
 * @see hajimeapi4j.internal.endpoint.TaxEndPointImpl
 * @see hajimeapi4j.util.enums.TaxParameter
 */
@Slf4j
public class TaxEndPointBuilder {

  private final Map<String, String> parameters;

  private TaxEndPointBuilder(PickUpParameterType type, String value) {
    this.parameters = new HashMap<>();
    parameters.put(type.toString(), value);
    log.debug("init complete with: {}, {}", type, value);
  }

  public static TaxEndPointBuilder createWithId(int id) {
    return create(PickUpParameterType.ID, String.valueOf(id));
  }

  public static TaxEndPointBuilder createWithIdolName(String name) {
    final String urlEncoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
    return create(PickUpParameterType.IDOL_NAME, urlEncoded);
  }

  @Deprecated
  public static TaxEndPointBuilder createWithUnitName(String name) {
    final String urlEncoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
    return create(PickUpParameterType.UNIT_NAME, urlEncoded);
  }

  private static TaxEndPointBuilder create(PickUpParameterType type, String value) {
    return new TaxEndPointBuilder(type, value);
  }

  private enum PickUpParameterType {
    ID,
    IDOL_NAME,
    UNIT_NAME,
    LIVE_DATE;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }
}
