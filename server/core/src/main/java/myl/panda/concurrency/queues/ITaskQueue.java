package myl.panda.concurrency.queues;

import myl.panda.concurrency.tasks.Task;

/**
 * create by maoyule on 2019/1/8
 */
public interface ITaskQueue extends Runnable {
    /**
     * 添加任务
     * @param task
     */
    void add(Task task);
}
