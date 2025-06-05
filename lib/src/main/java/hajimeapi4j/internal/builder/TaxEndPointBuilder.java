package hajimeapi4j.internal.builder;

import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.RestActionImpl;
import hajimeapi4j.util.Checks;
import hajimeapi4j.util.InternalUtils;
import hajimeapi4j.util.enums.TaxParameter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code tax} エンドポイントにリクエストを送るためのビルダークラスです。
 *
 * @see hajimeapi4j.api.endpoint.TaxEndPoint
 * @see hajimeapi4j.internal.endpoint.TaxEndPointImpl
 * @see hajimeapi4j.util.enums.TaxParameter
 */
@Slf4j
@SuppressWarnings("unused")
public class TaxEndPointBuilder extends CommonBuilder {

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
   * <p>
   * **注意**
   * <p>
   * ユニット名は表記ゆれが激しいため、完全一致では目的のデータを取得できない可能性があります。部分一致検索は [@code list} エンドポイントを利用してください。
   *
   * @param name ユニット名（完全一致）
   * @return ユニット名を指定した新しい {@code TaxEndPointBuilder}
   */
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
   */
  public static TaxEndPointBuilder createWithLiveDate(String liveDate) {
    if (!PATTERN_FOR_DATE_FORMAT.matcher(liveDate).matches()) {
      throw new IllegalArgumentException("this can only be set yyyy-mm-dd");
    }
    return create(PickUpParameterType.LIVE_DATE, liveDate);
  }

  /**
   * 出力するデータの上限数を設定します。
   *
   * @param limit 出力するデータの上限（自然数）
   * @return チェーンを組むためのこのインスタンス
   */
  public TaxEndPointBuilder setLimit(int limit) {
    Checks.validateInteger(limit);
    this.parameters.put("limit", String.valueOf(limit));
    return this;
  }

  /**
   * 楽曲の種類を指定します。
   * <p>
   * この情報は複数指定可能です。
   *
   * @param type 指定する楽曲の種類
   * @return チェーンを組むためのこのインスタンス
   * @see TaxParameter.MusicType
   */
  public TaxEndPointBuilder setMusicType(TaxParameter.MusicType... type) {
    final String parameterValue =
        InternalUtils.concatWithSeparators(
            Arrays.stream(type)
                .map(TaxParameter.MusicType::toString)
                .collect(Collectors.toList()),
            "%2C");
    this.parameters.put("music_type", parameterValue);
    return this;
  }

  /**
   * データの出力の際、ソート基準となるアイテムを指定します
   *
   * @param orderBy 基準とするアイテム
   * @return チェーンのためのこのインスタンス
   */
  public TaxEndPointBuilder setOrderBy(TaxParameter.OrderBy orderBy) {
    this.parameters.put("orderby", orderBy.toString());
    return this;
  }

  /**
   * 出力データを昇順でソートするか、降順でソートするかを指定します。
   *
   * @param order 昇順降順の指定
   * @return チェーンのためのこのインスタンス
   */
  public TaxEndPointBuilder setOrder(TaxParameter.Order order) {
    this.parameters.put("order", order.toString());
    return this;
  }

  public TaxEndPointBuilder setMemberPlain(boolean memberPlain) {
    this.parameters.put("member_plain", String.valueOf(memberPlain));
    return this;
  }

  /**
   * 各種パラメータで指定された情報を使用して、リクエストを送信するためのインスタンスを作成して返します。
   * <p>
   * この時点ではまだリクエストは送信されていないことに注意してください。
   *
   * @return {@code tax} エンドポイントへリクエストを送信するためのインスタンス
   */
  public RestAction<TaxEndPoint> build() {
    log.debug("set parameters: {}", this.parameters);
    log.info("constructing action instance...");
    RestAction<TaxEndPoint> result = new RestActionImpl<>("tax", this.parameters,
      TaxEndPoint.class, this.rateLimit);
    log.debug("complete. hash information: {}", result);
    return result;
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
