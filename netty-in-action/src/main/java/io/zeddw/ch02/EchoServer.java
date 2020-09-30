package io.zeddw.ch02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Echo 引导
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public class EchoServer {
    private final String host;
    private final int port;

    public EchoServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        // 构建引导
        try {
            ServerBootstrap app = new ServerBootstrap();
            app.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(host, port)
                // 当一个新的连接被建立时, 创建一个新的channel
                // ChannelInitializer会在channel init时将echoServerHandler添加到channel.pipeline中
                // echoServerHandler会收到入站的消息
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(echoServerHandler);
                    }
                });

            // 阻塞知道端口绑定完成
            ChannelFuture future = app.bind().sync();

            // 获取channel的closeFuture, 阻塞直到closeFuture完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭, 释放资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        EchoServer server = new EchoServer("localhost", 13302);
        server.start();
    }
}
