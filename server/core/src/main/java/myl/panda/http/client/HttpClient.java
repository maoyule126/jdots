package myl.panda.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import myl.panda.dispose.Disposer;
import myl.panda.http.pools.HttpPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient extends Disposer {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private static HttpClient instance;

    private static EventLoopGroup group;
    private Bootstrap b;
    private HttpPool httpPool;
    private String host;
    private int port;

    public static final HttpClient getInstance() {
        return instance;
    }

    @Override
    protected void doDispose() {
        if (this.httpPool != null) {
            this.httpPool.dispose();
        }
    }

    public HttpClient(String host, int port, int threadCount, int poolLimit) {
        instance = this;
        this.host = host;
        this.port = port;
        this.httpPool = new HttpPool(poolLimit);
        try {
            if (group == null) {
                group = new NioEventLoopGroup(threadCount);
            }
            b = new Bootstrap();
            b.group(group);
            b.option(ChannelOption.TCP_NODELAY, true);
            b.channel(NioSocketChannel.class);

            b.handler(new HttpClientInitializer());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Channel getChannel()
            throws InterruptedException {
        Channel channel = httpPool.pop();
        if (channel == null) {
            channel = b.connect(host, port).sync().channel();
        }
        return channel;
    }

    public void recycleChannel(Channel channel) {
        httpPool.add(channel);
    }

    public void sendGet(String path) {
        HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET, path);
        request.headers().set(HttpHeaderNames.HOST, host);
        request.headers().set(HttpHeaderNames.CONNECTION,
                HttpHeaderValues.KEEP_ALIVE);
        request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);
        try {
            Channel channel = getChannel();
            channel.writeAndFlush(request);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

}
