package hajimeapi4j.api.endpoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.errorprone.annotations.CheckReturnValue;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import jakarta.annotation.Nonnull;
import java.util.Optional;

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
 * @since 5.0.0.0-Alpha.1
 */
@JsonDeserialize(as = EndPointImpl.class)
public interface EndPoint {

  /**
   * ふじわらはじめAPIで規定されている、主に「名称」「曲名」などに割り当てられているJSONキー「name」の情報を取得します。
   *
   * @return JSONキー「name」に指定されている情報
   */
  String getName();

  /**
   * ふじわらはじめAPIで規定されている、主に出力データタイプの規定に割り当てられているJSONキー「type」の情報を取得します。
   *
   * @return JSONキー「type」に指定されている情報
   */
  String getType();

  /**
   * 空のインスタンスを作成します
   *
   * @return 空情報のインスタンス
   */
  @CheckReturnValue
  static EndPoint createEmpty() {
    return EndPointImpl.createEmpty();
  }

  /**
   * ふじわらはじめAPIで規定されている、主にカテゴリID（ふじわらはじめAPI内部管理ID）に割り当てられているJSONキー「tax_id」の情報を取得します。
   * <p>
   * この情報は {@link #getSongId()}と排他的になっており、どちらか一方が必ず参照可能です。
   *
   * @return JSONキー「tax_id」に指定されている情報
   */
  int getTaxId();

  /**
   * ふじわらはじめAPIで規定されている、主に楽曲ID（ふじわらはじめAPI内部管理ID）に割り当てられているJSONキー「song_id」の情報を取得します。
   * <p>
   * この情報は {@link #getTaxId()} と排他的になっており、どちらか一方が必ず参照可能です。
   *
   * @return JSONキー「song_id」に規定されている情報
   */
  int getSongId();

  /**
   * ふじわらはじめAPIで規定されている、主にそのカテゴリページへのURLに割り当てられているJSONキー「link」の情報を取得します。
   *
   * @return JSONキー「link」に規定されている情報
   */
  String getLink();

  /**
   * 取得したJSON API情報を使用し、その情報の詳細を取得できるようにするためのユーティリティメソッドです。
   *
   * @return エンドポイントに応じた、API情報から生成したエンドポイント情報
   */
  @Nonnull
  @CheckReturnValue
  RestAction<EndPoint> fromApi();

  /**
   * ふじわらはじめAPIで規定されている、主にそのカテゴリの詳細情報を格納しているAPIへのURI情報に割り当てられているJSONキー「api」の情報を取得します。
   *
   * @return JSONキー「api」に規定されている情報
   */
  String getApi();

  /**
   * このインスタンスに格納されている情報が空であるか確認します。
   * <p>
   * 空である判定は
   * <ul>
   *   <li>{@link #getName()} が空文字列である</li>
   *   <li>{@link #getType()} が空文字列である</li>
   *   <li>{@link #getSongId()} もしくは {@link #getTaxId()} が{@code -1} である</li>
   *   <li>{@link #getLink()} が空文字列である</li>
   *   <li>{@link #getApi()} が空文字列である</li>
   * </ul>
   * 以上の条件で判定を行います。
   *
   * @return インスタンスが空である場合は {@code true}, そうでない場合は {@code false}
   */
  boolean checkEmpty();
}
