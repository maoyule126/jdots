package myl.panda.http.pools;

import io.netty.channel.Channel;
import myl.panda.dispose.Disposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * create by maoyule on 2019/5/2
 */
public class HttpPool extends Disposer {
    private static final Logger logger = LoggerFactory.getLogger(HttpPool.class);

    private int limit;
    private Queue<Channel> channelList;

    @Override
    protected void doDispose() {
        while(true) {
            Channel channel = channelList.poll();
            if(channel == null){
                break;
            }
            try {
                channel.close();
            }catch (Exception e){
                logger.error("http pool dispose error", e);
            }
        }

    }

    public HttpPool(int limit){
        this.limit = limit;
        channelList = new LinkedBlockingQueue<>();
    }

    public void add(Channel channel){
        if(channelList.size() >= limit){
            channel.close();
            return;
        }
        channelList.add(channel);
    }

    public Channel pop(){
        while(true) {
            Channel channel = channelList.poll();
            if(channel == null){
                return null;
            }
            if(channel.isActive()){
                return channel;
            }else{
                try {
                    channel.close();
                }catch (Exception e){
                    logger.error("close http error", e);
                }
            }
        }
    }
}
