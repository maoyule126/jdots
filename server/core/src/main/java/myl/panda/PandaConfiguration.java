package myl.panda;

import myl.panda.concurrency.BaseTaskFactory;
import myl.panda.timers.TimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * panda的spring配置类
 * create by maoyule on 2019/1/13
 */
public class PandaConfiguration{
    private static final Logger logger = LoggerFactory.getLogger(PandaConfiguration.class);

    public void init(){
        if(BaseTaskFactory.getFactory() == null) {
            new BaseTaskFactory();
        }
        if(TimerService.getService() == null) {
            new TimerService();
        }
    }

    public void init(int mainThreadCount, int slowThreadCount){
        if(BaseTaskFactory.getFactory() == null) {
            new BaseTaskFactory(mainThreadCount, slowThreadCount);
        }
        if(TimerService.getService() == null) {
            new TimerService();
        }
    }

    public void destroy() {
        if (BaseTaskFactory.getFactory() != null) {
            BaseTaskFactory.getFactory().dispose();
        }
        if (TimerService.getService() != null) {
            TimerService.getService().dispose();
        }
    }
}
