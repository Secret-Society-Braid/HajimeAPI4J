package HajimeAPI4J.connect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckServerStatus {
    
    // Declare logger
    private static Logger logger = LoggerFactory.getLogger(CheckServerStatus.class);

    // This class should not have public constructors.
    private CheckServerStatus() {
        throw new IllegalStateException("Util class.");
    }

    /**
     * サーバーへGETリクエストを送信し、ステータスコードを取得します。
     * APIへ過剰な負荷を掛けないようにするため、成功or失敗に関わらず1秒間のクールダウンを設けています。
     * @param uri ステータスチェックするURI
     * @return HTTP/GETしたステータスコード
     */
    public static final int getServerStatus(String uri) {
        int result = -1;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            result = conn.getResponseCode();
        } catch (IOException e) {
            logger.error("Exception was thrown during checking API server status.", e);
        } finally {
            if(conn != null) {
                conn.disconnect();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.warn("Thread has been interrupted while cooldown.", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        return result;
    }

    public static boolean isServerAlive(int statusCode) {
        String codeStr = String.valueOf(statusCode);
        if(codeStr.startsWith("2")) {
            return true;
        } else if (codeStr.startsWith("5")) {
            return false;
        } else {
            return false;
        }

    }

}
