package hajimeapi4j.api.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;

import hajimeapi4j.api.HajimeAPI4J;
import hajimeapi4j.api.HajimeAPIBuilder;
import hajimeapi4j.api.util.internal.SingleThreadFactory;
import hajimeapi4j.exception.IllegalParameterException;
import hajimeapi4j.exception.NoSuchURIException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HajimeAPI4JImpl implements HajimeAPI4J {

    private Logger logger = LoggerFactory.getLogger(HajimeAPI4JImpl.class);

    private String uri = null;
    private HajimeAPI4J.Token token = null;
    private HajimeAPI4J.Status status = null;
    private boolean iscache = false;
    private LinkedHashMap<String, String> param = null;

    // constants
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor(SingleThreadFactory.getInstance());

    public HajimeAPI4JImpl() {
        this(HajimeAPI4J.Status.NOT_INITIALIZED);
    }

    public HajimeAPI4JImpl(HajimeAPI4J.Status status) {
        this(null, status, false, null);
    }

    public HajimeAPI4JImpl(String uri, HajimeAPI4J.Status status, boolean iscache,
            LinkedHashMap<String, String> param) {
        this.uri = uri;
        this.status = status;
        this.iscache = iscache;
        this.param = param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Token getToken() {
        return this.token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getURI() {
        if (Checks.softRequireNonNull(this.uri)) {
            return this.uri;
        }
        if (this.status.equals(Status.FINISHED) || this.status.equals(Status.FAILED)) {
            throw new IllegalStateException("This api has been already connected.");
        }
        this.logger.info("Set URI and move status to INITIALIZED");
        this.setStatus(HajimeAPI4J.Status.INITIALIZED);
        Checks.hardRequireNonNull(this.token);
        Checks.hardRequireNonNull(this.param);
        param.forEach((k, v) -> {
            try {
                param.put(k, URLEncoder.encode(v, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error("URL Encoding failed", e);
            }
        });
        Joiner urlJoiner = Joiner.on("").skipNulls();
        MapJoiner mappedJoiner = Joiner.on("&").withKeyValueSeparator("=").useForNull("");
        this.setURI(urlJoiner.join(HajimeAPIBuilder.getBaseURI(), this.token.toString(), "?", mappedJoiner.join(this.param)));
        this.setStatus(HajimeAPI4J.Status.AWAIT_SENDING);
        return this.uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setURI(String uri) {
        this.uri = uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCache() {
        return this.iscache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCache(boolean iscache) {
        this.iscache = iscache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getParams() {
        return this.param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParams(LinkedHashMap<String, String> param) {
        this.param = param;
    }

    /**
     * 指定されたトークンとパラメータ情報を使用してAPIへリクエストを送り、レスポンスを取得します。
     * 
     * @return ふじわらはじめAPIから取得したJSONデータを含むJsonNode
     * @throws IOException          APIからの読み取りが失敗した場合
     * @throws NoSuchURIException   指定されたパラメータ情報が間違っている、もしくは不正なトークンが渡された場合
     * @throws InterruptedException APIへのクールダウン期間中に割り込みを受けた場合
     */
    public JsonNode get() throws IOException, NoSuchURIException, InterruptedException {
        Map<String, String> env = FileUtils.readFilesFromResourceFolder(FileUtils.ENV_FILE_NAME);
        TimeUnit.SECONDS.sleep(1);
        Checks.hardRequireNonNull(this.token);
        Checks.hardRequireNonNull(this.param);
        switch (this.token) {
            case LIST:
                Checks.listMustHaveParam(this.param);
                break;
            case TAX:
                Checks.taxMustHaveParam(this.param);
                break;
            case MUSIC:
                Checks.musicMustHaveParam(this.param);
                break;
            default:
                throw new IllegalParameterException("Invalid token");
        }
        this.uri = this.getURI();
        Checks.serverAlive();
        this.logger.info("Requested data type: {}", this.token);
        this.param.entrySet().stream()
                .forEachOrdered(e -> this.logger.info("parameter -> {} : {}", e.getKey(), e.getValue()));
        this.logger.info("Parsed URI: {}", this.uri);
        this.setStatus(HajimeAPI4J.Status.FINISHED);
        this.logger.info("Data transfer completed");
        URLConnection conn = new URL(this.uri).openConnection();
        conn.setRequestProperty("User-Agent", "OSS HajimeAPI java wrapper library/" + env.get("version")
                + " Java-Application/Java 11 or higher. OSS Sourcecode: https://github.com/Secret-Society-Braid/HajimeAPI4J / Library developed by : @hizumiaoba (Twitter), Ranfa/hizumiaoba/Indigo_leaF P#4144 (Discord), @hizumiaoba (GitHub)");
        return new ObjectMapper().readTree(conn.getInputStream());
    }

    /**
     * 非同期的にレスポンスを取得します。
     * 使用する非同期サービスはデフォルト設定となります。
     * <p>
     * デフォルト設定のサービスはAPIへの過剰な負荷を抑えるためスレッドを一つのみ作成し実行します。
     * 
     * @return APIからのレスポンス情報を内包しているCompletableFuture
     */
    public CompletableFuture<JsonNode> getAsync() {
        return getAsync(EXECUTOR_SERVICE);
    }

    /**
     * 指定された非同期サービスを使用して非同期的にレスポンスを取得します。
     * 
     * @param service 指定するExecutorService
     * @return APIからのレスポンスを内包しているCompletableFuture
     */
    public CompletableFuture<JsonNode> getAsync(ExecutorService service) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.get();
            } catch (IOException | NoSuchURIException | InterruptedException e) {
                Thread.currentThread().interrupt();
                setStatus(Status.FAILED);
                throw new RuntimeException("Couldn't get from API", e);
            }
        }, service);
    }

    /**
     * 非同期的にレスポンスを取得する際にデフォルトで使用するExecutorServiceを返します。
     * 
     * @return デフォルトで使用しているExecutorService
     */
    public ExecutorService getDefaultExecutorService() {
        return EXECUTOR_SERVICE;
    }
}
