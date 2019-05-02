package myl.jdots;

import myl.panda.PandaConfiguration;
import myl.panda.http.client.HttpClient;
import myl.panda.timers.LoopTask;
import myl.panda.timers.TimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by maoyule on 2019/4/15
 */
public class JDotsPerformer {
    private static final Logger logger = LoggerFactory.getLogger(JDotsPerformer.class);

    private static PandaConfiguration pandaConfiguration;


    public static void main(String[] args){
        new PandaConfiguration().init();
        logger.info("performer start");

        new HttpClient("127.0.0.1", 8080, 5, 1000);
        HttpClient.getInstance().sendGet("/api/school/getSchool?schoolId=1001");
        TimerService.getService().addDelay(new LoopTask(null, 5000) {
            @Override
            public void execute() {
                System.exit(0);
            }
        });
    }

    public static class Person{
        public int getAge(){
            return 1;
        }
    }
}
