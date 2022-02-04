package HajimeAPI4J.api.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.exception.IllegalParameterException;
import HajimeAPI4J.exception.NoSuchURIException;
import HajimeAPI4J.exception.ServerNotRespondError;

public class HajimeAPI4JImpl implements HajimeAPI4J {

    private Logger logger = LoggerFactory.getLogger(HajimeAPI4JImpl.class);

    private String uri = null;
    private HajimeAPI4J.Token token = null;
    private HajimeAPI4J.Status status = null;
    private boolean iscache = false;
    private Map<String, String> param = null;

    // constants
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newWorkStealingPool();

    public HajimeAPI4JImpl() {
        this(HajimeAPI4J.Status.NOT_INITIALIZED);
    }

    public HajimeAPI4JImpl(HajimeAPI4J.Status status) {
        this(null, status, false, null);
    }

    public HajimeAPI4JImpl(String uri, HajimeAPI4J.Status status, boolean iscache, Map<String, String> param) {
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
        return status;
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
        return token;
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
        if(Checks.softRequireNonNull(uri)) {
            return uri;
        }
        logger.info("Set URI and move status to INITIALIZED");
        this.setStatus(HajimeAPI4J.Status.INITIALIZED);
        Checks.hardRequireNonNull(token);
        Checks.hardRequireNonNull(param);
        StringBuilder result = new StringBuilder(HajimeAPIBuilder.getBaseURI());
        result.append(token.toString()).append("?");
        param.forEach((k, v) -> {
            try {
                result.append(k).append("=").append(URLEncoder.encode(v, "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Failed to encode parameter", e);
            }
        });
        result.deleteCharAt(result.length() - 1);
        this.setURI(result.toString());
        this.setStatus(HajimeAPI4J.Status.AWAIT_SENDING);
        return uri;
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
        return iscache;
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
        return param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParams(Map<String, String> param) {
        this.param = param;
    }

    public JsonNode get() throws IOException, ServerNotRespondError, NoSuchURIException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Checks.hardRequireNonNull(token);
        Checks.hardRequireNonNull(param);
        switch (token) {
            case LIST:
                Checks.listMustHaveParam(param);
                break;
            case TAX:
                Checks.taxMustHaveParam(param);
                break;
            case MUSIC:
                Checks.musicMustHaveParam(param);
                break;
            default:
                throw new IllegalParameterException("Invalid token");
        }
        uri = getURI();
        Checks.serverAlive();
        logger.info("Requested data type: {}", token);
        param.forEach((k, v) -> logger.info("Requested parameter: {} = {}", k, v));
        logger.info("Parsed URI: {}", uri);
        this.setStatus(HajimeAPI4J.Status.FINISHED);
        logger.info("Data transfer completed");
        return new ObjectMapper().readTree(new URL(uri));
    }

    public CompletableFuture<JsonNode> getAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return get();
            } catch (IOException | NoSuchURIException | InterruptedException e) {
                Thread.currentThread().interrupt();
                this.setStatus(HajimeAPI4J.Status.FAILED);
                throw new RuntimeException("Couldn't get from API", e);
            }
        }, EXECUTOR_SERVICE);
    }

    public CompletableFuture<JsonNode> getAsync(ExecutorService service) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return get();
            } catch (IOException | NoSuchURIException | InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Couldn't get from API", e);
            }
        }, service);
    }

    public ExecutorService getDefaultExecutorService() {
        return EXECUTOR_SERVICE;
    }
}
