package myl.jdots;

import myl.jdots.players.AbstractPlayer;
import myl.jdots.players.Player;
import myl.panda.PandaConfiguration;
import myl.panda.concurrency.tasks.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by maoyule on 2019/4/15
 */
public class JDotsPerformer {
    private static final Logger logger = LoggerFactory.getLogger(JDotsPerformer.class);

    public static void main(String[] args){
        new PandaConfiguration().init();
        Player player = new AbstractPlayer();
        player.add(() -> {
            logger.info("test ok");
        });
        logger.info("performer start");
    }
}
