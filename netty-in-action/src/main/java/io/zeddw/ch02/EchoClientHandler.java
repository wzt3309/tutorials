package io.zeddw.ch02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Echo 客户端 逻辑处理
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final String longText;

    {
        int len = 100;
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sbd.append("hello echo server ").append(i);
        }
        longText = sbd.toString();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(longText, CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // 通过设置 longText channelRead0会被多次调用
        // channelRead调用, 方法返回后会释放 msg的内存
        System.out.println("client receive: " + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }
}
