package fastbackup.SendLog;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by kuan on 8/21/16.
 */
public class ClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String ip= IPAdress.Serverip;
        int port= IPAdress.Serverport;
        URI url=new URI("http://"+ip+":"+port);
        String in=Client.ip+","+Client.message;
        System.out.println(in);
//        InputStream message=System.in;
//        byte[] inByte = new byte[1024];
//        int x=0;
//        String in="";
//        System.out.println(0);
//        while ((x = message.read(inByte)) > 0) {}
//            in = new String(inByte, 0, x);
            FullHttpRequest fullHttpRequest=new DefaultFullHttpRequest
                    (HttpVersion.HTTP_1_1, HttpMethod.GET,url.toASCIIString(), Unpooled.copiedBuffer(in, CharsetUtil.UTF_8));
            fullHttpRequest.headers().set(HttpHeaderNames.HOST,port+"");
            fullHttpRequest.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            fullHttpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH,fullHttpRequest.content().readableBytes()+"");
            ctx.writeAndFlush(fullHttpRequest);
            ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse fullHttpResponse=(FullHttpResponse)msg;
        ByteBuf byteBuf=fullHttpResponse.content();
        byte[] bytes=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String st=new String(bytes,"utf-8");
        System.out.println(st);
    }
}
