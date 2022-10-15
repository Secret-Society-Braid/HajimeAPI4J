package hajimeapi4j.api.endpoint;

import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.EndPointImpl;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * ふじわらはじめAPIにて提供されるエンドポイントの共通仕様を定義します。
 * <p>
 * 基本的にAPI側で規定されている共通仕様に則っていますが、一部Javaの仕様を優先させているため、APIと対応するラッパークラスの挙動が異なる場合があります。
 * </p>
 * アノテーション「{@link Nonnull}」で修飾されているGetterはそのエンドポイントにおいて必ず値が存在することを表します。
 * <p>
 * また、{@link Optional}でラップされているGetterの扱いについてはエンドポイント、レスポンスごとに変わりますのでご了承ください。
 * </p>
 * このクラスで実装されているGetterは全てのエンドポイント、データクラスで情報が格納されていることが保証されているため、全てのGetterに{@link
 * Nonnull}アノテーションを付加しています。
 *
 * @author Ranfa
 * @see RestAction
 * @see <a href="https://api.fujiwarahaji.me/v2/doc">APIv2ドキュメント</a>
 * @since 5.0.0
 */
public interface EndPoint extends RestAction<EndPoint> {

  /**
   * ふじわらはじめAPIで規定されている、主に「名称」「曲名」などに割り当てられているJSONキー「name」の情報を取得します。
   * @return JSONキー「name」に指定されている情報
   */
  @Nonnull
  String getName();

  /**
   * ふじわらはじめAPIで規定されている、主に出力データタイプの規定に割り当てられているJSONキー「type」の情報を取得します。
   * @return JSONキー「type」に指定されている情報
   */
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
   *
   * @return エンドポイントに応じた、API情報から生成したエンドポイント情報
   */
  @Nonnull
  @CheckReturnValue
  RestAction<EndPoint> fromApi();

  boolean checkEmpty();

  @CheckReturnValue
  static EndPoint createEmpty() {
    return EndPointImpl.createEmpty();
  }
}
