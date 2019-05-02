package myl.jdots;

import myl.panda.PandaConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by maoyule on 2019/4/15
 */
public class JDotsPerformer {
    private static final Logger logger = LoggerFactory.getLogger(JDotsPerformer.class);

    public static void main(String[] args){
        new PandaConfiguration().init();
        logger.info("start");
    }
}
