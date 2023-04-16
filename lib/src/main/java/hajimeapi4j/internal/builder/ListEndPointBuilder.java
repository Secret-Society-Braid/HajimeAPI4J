package hajimeapi4j.internal.builder;

import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.endpoint.ListEndPointImpl;
import hajimeapi4j.internal.request.ArrayResponseRestActionImpl;
import hajimeapi4j.util.Checks;
import hajimeapi4j.util.InternalUtils;
import hajimeapi4j.util.enums.ListParameter;
import hajimeapi4j.util.enums.ListParameter.OrderBy;
import hajimeapi4j.util.enums.ListParameter.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code list} エンドポイントにリクエストを送信するためのビルダークラスです
 *
 * @see ListEndPoint
 * @see ListEndPointImpl
 * @see ListParameter
 */
@Slf4j
@SuppressWarnings("unused")
public class ListEndPointBuilder {

  private final Map<String, String> parameters;
  private final boolean musicTypeSelected;
  private static final String INAPPLICABLE_QUERY_EXCEPTION_STRING = "you cannot set this parameter!";

  /**
   * 指定された{@code type}を使用して新しいビルダークラスを作成します
   * <p>
   * 文字列から直接作成することは存在しないパラメータを作成してしまう可能性があるため、{@link
   * ListEndPointBuilder#createFor(ListParameter.Type)} をご利用ください
   *
   * @param type 指定する {@code type}
   * @return チェーンのためのこのインスタンス
   */
  public static ListEndPointBuilder createFor(String type) {
    return new ListEndPointBuilder(type);
  }

  /**
   * 指定された {@code Type}列挙を使用して新しいビルダークラスを作成します
   *
   * @param type 指定する {@code type}
   * @return チェーンのためのこのインスタンス
   * @see ListParameter.Type
   */
  public static ListEndPointBuilder createFor(ListParameter.Type type) {
    return createFor(type.toString());
  }

  private ListEndPointBuilder(String type) {
    this.parameters = new HashMap<>();
    this.musicTypeSelected = type.equals(Type.MUSIC.toString());
    this.parameters.put("type", type);
  }

  /**
   * 出力するデータの上限を設定します。
   * <p>
   * このパラメータは {@link ListParameter.Type#MUSIC} を指定した場合のみ指定可能です。
   * <p>
   * それ以外での呼び出しは {@link UnsupportedOperationException} がスローされます
   *
   * @param amount 出力するデータの上限数
   * @return チェーンのためのこのインスタンス
   */
  public ListEndPointBuilder setLimit(int amount) {
    if (!this.musicTypeSelected) {
      throw new UnsupportedOperationException(INAPPLICABLE_QUERY_EXCEPTION_STRING);
    }

    Checks.validateInteger(amount);
    this.parameters.put("limit", String.valueOf(amount));
    return this;
  }

  /**
   * 楽曲の種類を指定します。
   * <p>
   * この情報は複数指定可能です。
   * <p>
   * このパラメータは {@link ListParameter.Type#MUSIC} を指定した場合のみ指定可能です
   * <p>
   * それ以外での呼び出しは {@code UnsupportedOperationException} がスロ－されます
   *
   * @param types 指定する楽曲の種類
   * @return チェーンを組むためのこのインスタンス
   * @see ListParameter.MusicType
   */
  public ListEndPointBuilder setMusicType(ListParameter.MusicType... types) {
    if (!this.musicTypeSelected) {
      throw new UnsupportedOperationException(INAPPLICABLE_QUERY_EXCEPTION_STRING);
    }

    String concatenated = InternalUtils.concatWithSeparators(
        Arrays.stream(types)
            .map(ListParameter.MusicType::toString)
            .collect(Collectors.toList()),
        "%2C");
    this.parameters.put("music_type", concatenated);
    return this;
  }

  /**
   * 出力データを昇順でソートするか、降順でソートするかを指定します。
   *
   * @param order 昇順降順の指定
   * @return チェーンのためのこのインスタンス
   * @see ListParameter.Order
   */
  public ListEndPointBuilder setOrder(ListParameter.Order order) {
    this.parameters.put("order", order.toString());
    return this;
  }

  /**
   * データの出力の際、ソート基準となるアイテムを指定します
   * <p>
   * このパラメータは以下の場合に {@link UnsupportedOperationException} をスローします
   * <ul>
   * <li> {@link ListParameter.Type} が {@link ListParameter.Type#MUSIC MUSIC} であるときに {@link OrderBy#isApplicableForMusic()} の値が {@code false} であるパラメータを指定した場合</li>
   * <li> {@link ListParameter.Type} が {@link ListParameter.Type#MUSIC MUSIC} 以外であるときに {@link OrderBy#isApplicableForMusic()} の値が {@code true} であるパラメータを指定した場合</li>
   * </ul>
   *
   * @param orderBy 基準とするアイテム
   * @return チェーンのためのこのインスタンス
   * @see ListParameter.OrderBy
   */
  public ListEndPointBuilder setOrderBy(ListParameter.OrderBy orderBy) {
    if (((this.musicTypeSelected) && (!orderBy.isApplicableForMusic())) || (
        (!this.musicTypeSelected) && (orderBy.isApplicableForMusic()))) {
      throw new UnsupportedOperationException(INAPPLICABLE_QUERY_EXCEPTION_STRING);
    }
    this.parameters.put("orderby", orderBy.toString());
    return this;
  }

  /**
   * 検索を掛けるキーワードを指定します。
   * <p>
   * この情報は自動的にURLエンコードされ、パラメータが作成されます
   *
   * @param search 検索キーワード
   * @return チェーンのためのこのインスタンス
   */
  public ListEndPointBuilder setSearch(String search) {
    final String encoded = URLEncoder.encode(search, StandardCharsets.UTF_8);
    this.parameters.put("search", encoded);
    return this;
  }

  /**
   * アイドルのプロダクションを指定します
   * <p>
   * この情報は複数指定可能です
   *
   * @param production 指定するプロダクション
   * @return チェーンのためのこのインスタンス
   * @see ListParameter.Production
   */
  public ListEndPointBuilder setProduction(ListParameter.Production... production) {
    if (this.musicTypeSelected) {
      throw new UnsupportedOperationException(INAPPLICABLE_QUERY_EXCEPTION_STRING);
    }
    String concatenated = InternalUtils.concatWithSeparators(
        Arrays.stream(production)
            .map(ListParameter.Production::toString)
            .collect(Collectors.toList()),
        "%2C");
    this.parameters.put("production", concatenated);
    return this;
  }

  /**
   * リクエストを送信するためのインスタンスを作成します
   * <p>
   * この時点ではまだリクエストは送信されていないことに注意してください
   *
   * @return リクエストを送信するためのインスタンス
   */
  public RestAction<List<ListEndPoint>> build() {
    log.debug("set parameters: {}", this.parameters);
    log.info("constructing action instance...");
    RestAction<List<ListEndPoint>> result = new ArrayResponseRestActionImpl<>("list",
        this.parameters);
    log.debug("complete. information: {}", result);
    return result;
  }
}
