package io.zeddw.gist;

/**
 * Pipleline中处理单元
 */
public interface Processor<IN, OUT> {

    boolean isEnable();

    /**
     * 处理输入/输出
     * 
     * @param context 上下文
     * @return 结果
     */
    OUT process(IN context) throws Exception;
}
