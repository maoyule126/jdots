package myl.panda.concurrency.queues;

import myl.panda.concurrency.pools.TaskPool;
import myl.panda.concurrency.tasks.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * create by maoyule on 2019/5/2
 */
public class SimpleTaskQueue extends AbstractTaskQueue {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTaskQueue.class);

    private boolean isRunning = true;
    /**
     * 采用默认的任务管理器
     *
     * @param taskPool
     */
    public SimpleTaskQueue(TaskPool taskPool) {
        super(taskPool);
    }

    @Override
    protected void preInit() {
        this.list = new ConcurrentLinkedQueue<>();
    }

    @Override
    protected void doRun() {
        while (isRunning) {
            Task task = list.poll();
            if (task == null) {
                break;
            }
            try {
                task.run();
            } catch (Exception e) {
                logger.error("{}, {}", this.toString(), e.getMessage(), e);
            }
        }
    }
}
