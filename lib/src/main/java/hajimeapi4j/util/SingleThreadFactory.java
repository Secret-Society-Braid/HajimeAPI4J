package hajimeapi4j.util;

import java.util.concurrent.ThreadFactory;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleThreadFactory implements ThreadFactory {
    
    private static ThreadFactory singletonInstance = null;

    private static final String THREAD_NAME = "HajimeAPI4J Concurrent Thread";

    private static volatile boolean isShutdown = false;

    private Thread workingThread;
    
    private SingleThreadFactory() {
        log.debug("Initiate Single Thread Factory.");
        log.debug("The Thread name is {}", THREAD_NAME);
        if (isShutdown) {
            throw new IllegalStateException(
                "The thread has been stopped working. Maybe something went wrong.");
        }
    }
    
    public static ThreadFactory getInstance() {
        if(singletonInstance == null) {
            singletonInstance = new SingleThreadFactory();
        }
        return singletonInstance;
    }

    @Override
    public Thread newThread(@Nonnull Runnable r) {
        Thread thread = new Thread(r, THREAD_NAME);
        thread.setDaemon(true);
        this.workingThread = thread;
        return thread;
    }

    public synchronized void shutdown() {
        isShutdown = true;
        if(this.workingThread != null) {
            this.workingThread.interrupt();
        } else {
            log.warn("The working thread is null. Please check the code.");
        }
    }

}
