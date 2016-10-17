package broadcast;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


/**
 * Created by fanshen on 16-8-21.
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String req=datagramPacket.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if (req.equals("a")){
            channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("返回值",CharsetUtil.UTF_8),
                    datagramPacket.sender()));
            channelHandlerContext.close();
        }

    }
}
