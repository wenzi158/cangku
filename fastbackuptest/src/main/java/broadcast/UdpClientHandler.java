package broadcast;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;


/**
 * Created by fanshen on 16-8-21.
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String string=datagramPacket.content().toString(CharsetUtil.UTF_8);
        InetSocketAddress str=datagramPacket.recipient();
        System.out.println(string+":");
        long bLong=System.currentTimeMillis();
        bLong=bLong-BroadCastClient.aLong;
        System.out.println(str);
        System.out.println(bLong);
    }
}
