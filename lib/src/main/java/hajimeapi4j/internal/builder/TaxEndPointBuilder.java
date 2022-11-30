package hajimeapi4j.internal.builder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
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
  private static final Pattern PATTERN_FOR_DATE_FORMAT = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

  private TaxEndPointBuilder(PickUpParameterType type, String value) {
    this.parameters = new HashMap<>();
    parameters.put(type.toString(), value);
    log.debug("init complete with: {}, {}", type, value);
  }

  /**
   * {@code tax} エンドポイントのリクエストを、API内部管理IDを指定して作成します。
   * <p>
   * このIDは楽曲DB内で使用されている管理IDであり、通常は{@code list}などで検索をかけてからAPI文字列より、リクエストを作成する方法が便利です
   *
   * @param id 指定する楽曲などAPI内部管理ID
   * @return IDを指定した新しい {@code TaxEndPointBuilder}
   */
  public static TaxEndPointBuilder createWithId(int id) {
    return create(PickUpParameterType.ID, String.valueOf(id));
  }

  /**
   * {@code tax} エンドポイントのリクエストをアイドル名を指定して作成します。
   * <p>
   * 苗字と名前の間にはスペースなどをおく必要はありません。例：藤居朋
   *
   * @param name アイドル名（苗字と名前の間のスペースなし）
   * @return アイドル名を指定した新しい {@code TaxEndPointBuilder}
   */
  public static TaxEndPointBuilder createWithIdolName(String name) {
    final String urlEncoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
    return create(PickUpParameterType.IDOL_NAME, urlEncoded);
  }

  /**
   * {@code tax} エンドポイントのリクエストをユニット名完全一致で指定して作成します。
   *
   * @param name ユニット名（完全一致）
   * @return ユニット名を指定した新しい {@code TaxEndPointBuilder}
   * @deprecated ユニット名は表記ゆれが激しいため、完全一致では目的のデータを取得できない可能性があります。部分一致検索は [@code list} エンドポイントを利用してください。
   */
  @Deprecated(since = "1.0.0")
  public static TaxEndPointBuilder createWithUnitName(String name) {
    final String urlEncoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
    return create(PickUpParameterType.UNIT_NAME, urlEncoded);
  }

  /**
   * {@code tax} エンドポイントのリクエストをライブが開催された日付を基に作成します。
   * <p>
   * 同じ日にライブがあるなどの場合を除き、「yyyy-mm-dd」形式のみ受け付け。
   *
   * @param liveDate 指定するライブの日付 yyyy-mm-dd
   * @return ライブの日付を指定した新しい {@code TaxEndPointBuilder}
   * @apiNote APIの内部処理的にはURIのスラッグを使用して検束
   */
  public static TaxEndPointBuilder createWithLiveDate(String liveDate) {
    if (!PATTERN_FOR_DATE_FORMAT.matcher(liveDate).matches()) {
      throw new IllegalArgumentException("this can only be set yyyy-mm-dd");
    }
    return create(PickUpParameterType.LIVE_DATE, liveDate);
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
