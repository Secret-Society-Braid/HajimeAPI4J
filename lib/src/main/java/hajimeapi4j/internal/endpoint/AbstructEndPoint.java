package hajimeapi4j.internal.endpoint;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.DetailedRequestRestActionImpl;
import org.jetbrains.annotations.NotNull;

public abstract class AbstructEndPoint implements EndPoint {

  /**
   * ふじわらはじめAPIで規定されている、主にカテゴリID（ふじわらはじめAPI内部管理ID）に割り当てられているJSONキー「tax_id」の情報を取得します。
   * <p>
   * この情報は {@link #getSongId()}と排他的になっており、どちらか一方が必ず参照可能です。
   *
   * @return JSONキー「tax_id」に指定されている情報
   */
  @Override
  public int getTaxId() {
    return this.getSongId();
  }

  /**
   * ふじわらはじめAPIで規定されている、主に楽曲ID（ふじわらはじめAPI内部管理ID）に割り当てられているJSONキー「song_id」の情報を取得します。
   * <p>
   * この情報は {@link #getTaxId()} と排他的になっており、どちらか一方が必ず参照可能です。
   *
   * @return JSONキー「song_id」に規定されている情報
   */
  @Override
  public int getSongId() {
    return this.getTaxId();
  }

  /**
   * 取得したJSON API情報を使用し、その情報の詳細を取得できるようにするためのユーティリティメソッドです。
   *
   * @return エンドポイントに応じた、API情報から生成したエンドポイント情報
   */
  @NotNull
  @Override
  public RestAction<EndPoint> fromApi() {
    return new DetailedRequestRestActionImpl<>(this.getApi(), EndPoint.class);
  }
}
