package io.zeddw.tutorial.domain.security.tenant;

import io.zeddw.tutorial.common.domain.Identifier;
import lombok.Value;

/**
 * 值对象: 租户
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
@Value
public class Tenant implements Identifier {

    /**
     * 租户名称
     */
    String name;
}
