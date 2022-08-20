package hajimeapi4j.api.util.internal;

import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleThreadFactory implements ThreadFactory {
    
    private static final Logger LOG = LoggerFactory.getLogger(SingleThreadFactory.class);
    
    private static final ThreadFactory INSTANCE = new SingleThreadFactory();

    private static final String THREAD_NAME = "HajimeAPI4J Concurrent Thread";

    private static volatile boolean isShutdown = false;

    private Thread workingThread;
    
    private SingleThreadFactory() {
        LOG.debug("Initiate Single Thread Factory.");
        LOG.debug("The Thread name is {}", THREAD_NAME);
        if(isShutdown) {
            throw new IllegalStateException("The thread has been stopped working. Maybe something went wrong.");
        }
    }
    
    public static ThreadFactory getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Thread newThread(Runnable r) {
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
            LOG.warn("The working thread is null. Please check the code.");
        }
    }

}
