package fastbackup.GetLog;

import fastbackup.SendLog.IPAdress;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;


/**
 * Created by Li on 2016/6/23.
 */
public class Server {
    public void bound(int port) throws Exception {
        EventLoopGroup eventLoopGroup1 = new NioEventLoopGroup();
        EventLoopGroup eventLoopGroup2 = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup1, eventLoopGroup2)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new HttpRequestDecoder());
                        socketChannel.pipeline().addLast(new HttpResponseEncoder());
                        socketChannel.pipeline().addLast(new HttpObjectAggregator(65536));
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        eventLoopGroup1.shutdownGracefully();
        eventLoopGroup2.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        new Server().bound(IPAdress.Serverport);
    }
}
