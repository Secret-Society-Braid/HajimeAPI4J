package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * ふじわらはじめAPIにて提供されるエンドポイントの共通仕様を定義します。
 * <p>
 * 基本的にAPI側で規定されている共通仕様に則っていますが、一部Javaの仕様を優先させているため、APIと対応するラッパークラスの挙動が異なる場合があります。
 * </p>
 * アノテーション「{@code @Nonnull}で修飾されているGetterはそのエンドポイントにおいて必ず値が存在することを表します。
 * <p>
 *   また、{@link Optional}でラップされているGetterの扱いについてはエンドポイント、レスポンスごとに変わりますのでご了承ください。
 * </p>
 *
 * @since 5.0.0
 * @author Ranfa
 * @see RestAction
 */
public interface EndPoint extends RestAction<EndPoint> {

  @Nonnull
  String getName();

  @Nonnull
  String getType();

  int getTaxId();

  int getSongId();

  @Nonnull
  String getLink();

  @Nonnull
  String getApi();

  /**
   * 取得したJSON API情報を使用し、その情報の詳細を取得できるようにするためのユーティリティメソッドです。
   * @return エンドポイントに応じた、API情報から生成したエンドポイント情報
   */
  @Nonnull
  @CheckReturnValue
  EndPoint fromApi();

  boolean checkEmpty();
}
