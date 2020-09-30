package io.zeddw.ch02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 处理 Echo 的核心业务逻辑
 *
 * 增加{@link Sharable}, 说明该handler可被不同的channel共用
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 1. 获取输入
        ByteBuf in = (ByteBuf)msg;

        // 2. 本地输出
        System.out.println("receive: " + in.toString(CharsetUtil.UTF_8));

        // 3. 回写 异步操作, 当 channelRead返回时 write还在继续
        // ChannelInboundHandlerAdapter 方法返回时 ByteBuf内存不会被释放
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 1. 冲刷消息
        // 2. 操作完成后通过后关闭连接 (listener执行 - 回调)
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
            .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
