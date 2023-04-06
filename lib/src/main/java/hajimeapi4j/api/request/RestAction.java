package hajimeapi4j.api.request;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.MemberSolo;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import hajimeapi4j.internal.datatype.utilizations.Song;
import hajimeapi4j.internal.endpoint.EndPointImpl;

/**
 * REST APIとの相互作用全般を引き受けるクラスです。
 * <p>
 * このクラスではリクエストの送信のみに特化しており、あとから情報を付け足したり省略したりといったことはできません。
 *
 * @param <T> リクエスト受信後に受け取るデータに対応したラッパークラス
 * @author Ranfa
 * @see EndPoint
 * @see EndPointImpl
 * @see Disc
 * @see Live
 * @see Song
 * @see Member
 * @see MemberSolo
 * @see Unit
 */
public interface RestAction<T> {

}
