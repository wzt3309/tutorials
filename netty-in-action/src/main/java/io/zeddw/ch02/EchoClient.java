package io.zeddw.ch02;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Echo client
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        final int len = 2;
        if (args.length != len) {
            System.out.println("Usage: " + EchoClient.class.getSimpleName() + "<Host> <Port>");
        }

        new EchoClient(args[0], Integer.parseInt(args[1])).start();
    }

    public void start() throws Exception {
        final EchoClientHandler echoClientHandler = new EchoClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap app = new Bootstrap();
            app.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(echoClientHandler);
                    }
                });
            ChannelFuture future = app.connect().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
