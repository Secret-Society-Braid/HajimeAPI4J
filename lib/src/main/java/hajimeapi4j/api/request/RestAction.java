package hajimeapi4j.api.request;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.EndPointImpl;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.MemberSolo;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import hajimeapi4j.internal.datatype.utilizations.Music;
import hajimeapi4j.internal.request.CompiledRoute;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
 * @see Music
 * @see Member
 * @see MemberSolo
 * @see Unit
 */
public interface RestAction<T> {

  /**
   * REST APIへのリクエストを実行されたスレッドをロックして実行します。
   * <p>
   * これはつまり、APIへのリクエストから情報パースまでを、呼び出されたスレッド内で完結させます。
   * <p>
   * 取得した情報をすぐに使用する場合、リクエストの並列性を考慮しない場合はこのメソッドをご使用ください。
   * <p>
   * 非同期的に情報を処理する場合は {@link #submit() submit} メソッドをご使用ください。
   *
   * @return APIから取得した情報をラップしたデータ
   */
  T complete();

  /**
   * REST APIへのリクエストを、ライブラリ内で独自に作成したリクエストスレッドにて行います。
   * <p>
   * 作成されるスレッドは {@link Executors#newCachedThreadPool(ThreadFactory)} にて、独自の {@link ThreadFactory}
   * 実装を用いたスレッドプールにより生成されます。
   * <p>
   * 情報を非同期的に取得し、その後のコールバックを遅延して処理させる場合はこのメソッドをご使用ください。
   * <p>
   * 同期的にデータを取得する場合は {@link #complete() complete} メソッドをご使用ください。
   *
   * @return APIから取得した情報をラップしたデータを内包する {@link CompletableFuture}
   */
  CompletableFuture<T> submit();

  /**
   * リクエストに必要なパラメータ情報をセットするメソッドです。
   * <p>
   * 通常は内部で自動的にパラメータ情報をセットするため、使用する必要はありません。
   *
   * @param params 現在のActionにセットするパラメータ情報
   * @return パラメータ情報をセットしたAPIへのURI情報
   */
  CompiledRoute constructRoute(String... params);

}
