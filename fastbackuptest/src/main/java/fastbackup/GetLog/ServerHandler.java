package fastbackup.GetLog;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by kuan on 8/21/16.
 */
public class ServerHandler extends ChannelHandlerAdapter {
    HttpRequest httpRequest = null;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str = "";
        if (msg instanceof HttpRequest) {
            httpRequest = (HttpRequest) msg;
            String uri = httpRequest.uri();
            System.out.println(uri);
        }
        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            str = httpContent.content().toString(CharsetUtil.UTF_8);

            String result = "访问成功成功,准备发送...";
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse
                    (HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(result.getBytes("UTF-8")));

            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
            ctx.writeAndFlush(fullHttpResponse);

            String[] message=str.split(",");
            new MakeDirectory().makeDir(message[0],message[1]);

            System.out.println("日志文件写入成功");
        }

    }
}
