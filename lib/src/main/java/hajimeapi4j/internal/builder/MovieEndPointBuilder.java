package hajimeapi4j.internal.builder;

import hajimeapi4j.api.endpoint.MovieEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.MovieRestActionImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code movie} エンドポイントにリクエストを送信するためのビルダークラスです
 *
 * @author Ranfa
 * @see MovieEndPoint
 * @since v6.0.0.0-Alpha.1
 */
@Slf4j
public class MovieEndPointBuilder extends CommonBuilder {

  private final Map<String, String> parameters;

  protected MovieEndPointBuilder(int id) {
    this.parameters = new HashMap<>();
    this.parameters.put("id", String.valueOf(id));
  }

  /**
   * 指定された{@code id}を使用して新しいビルダークラスを作成します
   * <p>
   * id へ不正な値を渡すと、API上から400エラーが返されます
   *
   * @param id 指定する {@code id}
   * @return チェーンのためのこのインスタンス
   */
  public static MovieEndPointBuilder createWith(int id) {
    return new MovieEndPointBuilder(id);
  }

  /**
   * {@code plain}パラメータを設定します
   * <p>
   * この値を指定しないか、{@code false}を指定した場合、レスポンスはHTTP (HTML) 形式で返されます
   *
   * @param plain {@code plain}パラメータに指定する値
   * @return チェーンのためのこのインスタンス
   */
  public MovieEndPointBuilder setPlain(boolean plain) {
    this.parameters.put("plain", String.valueOf(plain));
    return this;
  }

  /**
   * リクエストを送信するための {@link RestAction} インスタンスを構築します
   * <p>
   * この時点ではまだリクエストは送信されません
   *
   * @return {@link RestAction} インスタンス
   */
  public RestAction<MovieEndPoint> build() {
    log.debug("Set parameters: {}", this.parameters);
    log.info("constructing action instance...");
    RestAction<MovieEndPoint> action = new MovieRestActionImpl(this.parameters, this.rateLimit);
    log.info("constructed action instance.");
    return action;
  }
}
