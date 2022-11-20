package hajimeapi4j.util;

import com.google.common.base.Strings;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountingThreadFactory implements ThreadFactory {

    private static final String THREAD_NAME = "HajimeAPI4J Concurrent Thread";
    private final AtomicInteger count = new AtomicInteger(0);
    private final String threadIdentifier;

    public CountingThreadFactory(String threadIdentifier) {
        this.threadIdentifier =
            Strings.isNullOrEmpty(threadIdentifier) ? THREAD_NAME : threadIdentifier;
        log.debug("Initiate Single Thread Factory.");
        log.debug("The Thread name is {}", this.threadIdentifier);
    }

    @Override
    public Thread newThread(@Nonnull Runnable r) {
        Thread thread = new Thread(r, this.threadIdentifier + "-worker " + count.incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}
