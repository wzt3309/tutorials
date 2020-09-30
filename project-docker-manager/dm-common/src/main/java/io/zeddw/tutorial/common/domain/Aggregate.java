package io.zeddw.tutorial.common.domain;

/**
 * 领域模型: 聚合根
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public interface Aggregate<ID extends Identifier> extends Entity<ID> {
}
