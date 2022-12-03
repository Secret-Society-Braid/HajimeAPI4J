package hajimeapi4j.internal.builder;

import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.RestActionImpl;
import hajimeapi4j.internal.request.Route;
import hajimeapi4j.util.Checks;
import hajimeapi4j.util.InternalUtils;
import hajimeapi4j.util.enums.MusicParameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code music} エンドポイントのリクエストを送信するためのビルダークラスです
 *
 * @see hajimeapi4j.api.endpoint.MusicEndPoint
 * @see hajimeapi4j.internal.endpoint.MusicEndPointImpl
 * @see hajimeapi4j.util.enums.MusicParameter
 * @since 5.0.0.0-Alpha.1
 */
@Slf4j
@SuppressWarnings("unused")
public class MusicEndPointBuilder {

  private final Map<String, String> parameters;

  private MusicEndPointBuilder(int id) {
    log.debug("new builder instance will be created with id: {}", id);
    this.parameters = new HashMap<>();
    this.parameters.put("id", String.valueOf(id));
  }

  /**
   * 指定したIDのリクエストを送信するためのビルダークラスを作成します。
   *
   * @param id 楽曲ID（自然数）
   * @return 新しいビルダーインスタンス
   */
  public static MusicEndPointBuilder createWith(int id) {
    Checks.validateInteger(id);
    return new MusicEndPointBuilder(id);
  }

  /**
   * データ出力の際に高速化のため省くデータを指定します
   *
   * @param hide 省くデータ
   * @return チェーンのためのこのインスタンス
   * @see MusicParameter.Hide
   */
  public MusicEndPointBuilder setHide(MusicParameter.Hide... hide) {
    final String concatenated = InternalUtils.concatWithSeparators(
        Arrays.stream(hide)
            .map(MusicParameter.Hide::toString)
            .collect(Collectors.toList()),
        "%2C");
    this.parameters.put("hide", concatenated);
    return this;
  }

  /**
   * リクエストを送信するためのインスタンスを作成します
   * <p>
   * この時点ではまだリクエストは送信されていないことに注意してください
   *
   * @return リクエストを送信するためのインスタンス
   */
  public RestAction<MusicEndPoint> build() {
    log.debug("set parameters: {}", this.parameters);
    log.info("constructing action instance...");
    RestAction<MusicEndPoint> result = new RestActionImpl<>(Route.musicRoute(),
        this.parameters);
    log.debug("complete. information: {}", result);
    return result;
  }
}
