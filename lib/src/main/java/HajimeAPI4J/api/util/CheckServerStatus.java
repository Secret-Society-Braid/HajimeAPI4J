package HajimeAPI4J.api.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckServerStatus {
    
    // Declare logger
    private static Logger logger = LoggerFactory.getLogger(CheckServerStatus.class);

    // This class should not have public constructors.
    private CheckServerStatus() { /* do nothing */}

    /**
     * サーバーへテスト用のURIでGETリクエストを送信し、ステータスコードを取得します。
     * APIへ過剰な負荷を掛けないようにするため、成功or失敗に関わらず1秒間のクールダウンを設けています。
     * @return サーバーからのレスポンスが成功の場合は<code>TRUE</code>、失敗なら<code>FALSE</code>
     */
    public static final boolean isServerAlive() {
        Map<String, String> env = null;
        try {
            env = FileUtils.readFilesFromResourceFolder(FileUtils.ENV_FILE_NAME);
        } catch (IOException e) {
            logger.error("Failed to read env.json file.", e);
            return false;
        }
        String uri = "https://api.fujiwarahaji.me/v1/list?type=idol&production=765";
        int statusCode = -1;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "OSS HajimeAPI java wrapper library/"+ env.get("version") + " Java-Application/Java 11 or higher. OSS Sourcecode: https://github.com/Secret-Society-Braid/HajimeAPI4J / Library developed by : @hizumiaoba (Twitter), Ranfa/hizumiaoba/Indigo_leaF P#4144 (Discord), @hizumiaoba (GitHub)");
            conn.connect();
            statusCode = conn.getResponseCode();
        } catch (IOException e) {
            logger.error("Exception was thrown during checking API server status.", e);
        } finally {
            if(conn != null) {
                conn.disconnect();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    logger.warn("Thread has been interrupted while cooldown.", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        String codeStr = String.valueOf(statusCode);
        return codeStr.startsWith("2");
    }
}
