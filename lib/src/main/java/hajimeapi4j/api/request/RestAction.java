package hajimeapi4j.api.request;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.MemberSolo;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.datatype.utilizations.Live;
import hajimeapi4j.internal.datatype.utilizations.Song;
import hajimeapi4j.internal.endpoint.EndPointImpl;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * REST APIとの相互作用全般を引き受けるクラスです。
 * <p>
 * このインターフェースではリクエスト送信に深く関係するメソッドのみを定義し、ユーザー側で不必要なメソッドが実行されることを防ぐ目的があります。
 *
 * @param <T> リクエスト受信後に受け取るデータに対応したラッパークラス
 * @author Ranfa
 * @see EndPoint
 * @see EndPointImpl
 * @see Live
 * @see Song
 * @see Member
 * @see MemberSolo
 * @see Unit
 */
public interface RestAction<T> {

  /**
   * ふじわらはじめAPIに対してリクエストを非同期的に送信します。
   * <p>
   * 相互作用を行う際は {@link RestAction#handleAsync(Consumer)} を使用する方が推奨されます。
   *
   * @return 受け取るデータに対応したラッパークラスインスタンスを内包する{@link CompletableFuture}
   */
  @Nonnull
  CompletableFuture<T> handleAsync();

  /**
   * ふじわらはじめAPIに対してリクエストを非同期的に送信します。
   * <p>
   * この操作では、リクエストを送信し、そのまま同じスレッド内でデータを加工します。データの加工を別スレッドで行いたい場合は {@link RestAction#handleSync()} の利用、加工をせずにそのままデータを適用する場合は {@link RestAction#handleAsync(Consumer)} の利用が便利です。。
   *
   * @param handler データの加工処理。{@link EndPoint#getName()} など。
   * @param <U>     データの加工後の型情報
   * @return リクエスト受信後に相互作用を合成した後の{@link {@link CompletableFuture}
   */
  @Nonnull
  <U> CompletableFuture<U> handleAsync(Function<T, U> handler);

  /**
   * ふじわらはじめAPIに対してリクエストを非同期的に送信します。
   * <p>
   * 相互作用を行わず、単にデータのみを取得する場合は {@link RestAction#handleAsync()} を使用する方が推奨されます。
   *
   * @param consumer リクエスト受信後に実行する処理
   * @return リクエスト受信後に相互作用を合成した後の{@link {@link CompletableFuture}
   */
  @Nonnull
  CompletableFuture<Void> handleAsync(Consumer<T> consumer);

  /**
   * ふじわらはじめAPIに対してリクエストを同期的に送信します。
   *
   * @return 受け取るデータに対応したラッパークラスインスタンス
   */
  @Nullable
  default T handleSync() {
    return handleAsync().join();
  }
}
