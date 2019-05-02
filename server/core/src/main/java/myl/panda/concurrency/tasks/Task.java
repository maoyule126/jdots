package myl.panda.concurrency.tasks;

import myl.panda.concurrency.queues.ITaskQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public interface Task extends Runnable {
    Logger logger = LoggerFactory.getLogger(Task.class);

    @Override
    default void run() {
        try {
            execute();
        }catch (Exception e){
            logger.error("{}, {}", this.toString(), e.getMessage(), e);
        }
    }

    void execute();
}
