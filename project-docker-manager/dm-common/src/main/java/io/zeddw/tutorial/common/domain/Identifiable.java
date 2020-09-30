package io.zeddw.tutorial.common.domain;

/**
 * 领域模型: 表示该对象有唯一标识
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public interface Identifiable<ID extends Identifier> {

    /**
     * 获取唯一标识
     *
     * @return ID
     */
    ID getId();
}
