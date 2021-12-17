package HajimeAPI4J.async;

import HajimeAPI4J.connect.CheckServerStatus;

public class CheckServerStatusAsync implements Runnable {

    private boolean result = false;

    @Override
    public void run() {
        this.result = CheckServerStatus.isServerAlive();
    }

    public boolean getResult() {
        return result;
    }
    
}
