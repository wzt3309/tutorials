package io.zeddw.ssm.domain;

/**
 * 选品状态
 *
 * @author xingguan.wzt
 * @date 2020/09/25
 */
public enum SelectState {

    /**
     * 待选择
     */
    S_INIT,

    /**
     * 已选择
     */
    S_SELECT,

    /**
     * 已发布
     */
    S_PUBLISH,

    /**
     * 取消选择
     */
    S_CXL_SELECT
}
