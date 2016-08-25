package fastbackup.SendLog;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;


/**
 * Created by Li on 2016/6/23.
 */
public class Client {
    public static String message="";
    public static String ip=IPAdress.Clientip;

    public Client(String message){
        Client.message=message;
    }

    public void con(String host, int port) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap serverBootstrap = new Bootstrap();
        serverBootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new HttpRequestEncoder());
                        socketChannel.pipeline().addLast(new HttpResponseDecoder());
                        socketChannel.pipeline().addLast(new HttpObjectAggregator(65536));
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        serverBootstrap.connect(host, port).sync().channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }


}
