package io.zeddw.tutorial.domain.security.user;

import io.zeddw.tutorial.common.domain.Identifier;
import io.zeddw.tutorial.domain.security.tenant.OwnedByTenant;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import lombok.Value;

/**
 * 值对象: 用户ID
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@Value
public class UserId implements OwnedByTenant, Identifier {
    Tenant tenant;
    String email;
}
