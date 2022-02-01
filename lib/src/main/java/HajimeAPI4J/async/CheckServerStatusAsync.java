package HajimeAPI4J.async;

import java.util.concurrent.CompletableFuture;

import HajimeAPI4J.connect.CheckServerStatus;

public class CheckServerStatusAsync {

    private CheckServerStatusAsync() {
        throw new UnsupportedOperationException("Util class.");
    }

    public static final CompletableFuture<Boolean> checkServerStatusAsync() {
        return CompletableFuture.supplyAsync(CheckServerStatus::isServerAlive);
    }
    
}
