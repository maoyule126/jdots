package myl.panda.concurrency.queues;

import myl.panda.concurrency.BaseTaskFactory;

/**
 * create by maoyule on 2019/1/9
 */
public class TaskQueue extends SimpleTaskQueue {
    public TaskQueue() {
        super(BaseTaskFactory.getFactory().getMainPool());
    }
}
