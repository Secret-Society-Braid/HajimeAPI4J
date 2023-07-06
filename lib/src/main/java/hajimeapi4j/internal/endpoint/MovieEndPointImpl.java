package hajimeapi4j.internal.endpoint;

import hajimeapi4j.api.endpoint.MovieEndPoint;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class MovieEndPointImpl extends EndPointImpl implements MovieEndPoint {

  private final String content;
  private final int returnStatusCode;

  public MovieEndPointImpl(@Nullable String content, int returnStatusCode) {
    this.content = content;
    this.returnStatusCode = returnStatusCode;
  }

  /**
   * クエリパラメータにて設定したIDに紐づけられている動画情報を取得します。
   *
   * @return 動画情報。何も紐づけされていない（APIが 204 を返した）場合は空の {@link Optional} が返されます。
   * @see #getReturnStatusCode()
   */
  @Nonnull
  @Override
  public Optional<String> get() {
    return Optional.ofNullable(content);
  }

  /**
   * APIより返却されたステータスコードを取得します。 返却される可能性があるステータスコードは以下の通りです。
   *
   * <ul>
   *   <li>200 - ID正常、動画情報あり</li>
   *   <li>204 - ID正常、動画情報なし</li>
   *   <li>400 - パラメータ（ID）不正</li>
   * </ul>
   *
   * @return APIより返却されたステータスコード
   */
  @Override
  public int getReturnStatusCode() {
    return this.returnStatusCode;
  }

  // EndPointImplでのメソッド群。これらはMovieEndPointImplには存在しない。

  @Override
  public int getTaxId() {
    throw new UnsupportedOperationException("movie endpoint does not contain any json field");
  }

  @Override
  public int getSongId() {
    throw new UnsupportedOperationException("movie endpoint does not contain any json field");
  }

  @Nonnull
  @Override
  public String getName() {
    throw new UnsupportedOperationException("movie endpoint does not contain any json field");
  }

  @Nonnull
  @Override
  public String getType() {
    throw new UnsupportedOperationException("movie endpoint does not contain any json field");
  }

  @Nonnull
  @Override
  public String getLink() {
    throw new UnsupportedOperationException("movie endpoint does not contain any json field");
  }

  @Nonnull
  @Override
  public String getApi() {
    throw new UnsupportedOperationException("movie endpoint does not contain any json field");
  }
}
