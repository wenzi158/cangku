package broadcast;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;


/**
 * Created by fanshen on 16-8-21.
 */
public class BroadCastClient {
    static long aLong;
    public void broadCast(int port) throws Exception {
        EventLoopGroup group=new NioEventLoopGroup();
        try {
            Bootstrap b=new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UdpClientHandler());

            Channel ch = b.bind(0).sync().channel();

            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("a", CharsetUtil.UTF_8),
                    new InetSocketAddress("255.255.255.255",port))).sync();
            aLong=System.currentTimeMillis();

            if (!ch.closeFuture().await(20000)){
                System.out.println("链接超时");
            }
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new BroadCastClient().broadCast(8888);
    }
}
