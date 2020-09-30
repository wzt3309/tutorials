package io.zeddw.tutorial.domain.security.authority;

import io.zeddw.tutorial.domain.security.tenant.OwnedByTenant;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import lombok.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

/**
 * 值对象: 租户隔离的权限
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
@Value
public class TenantGrantedAuthority implements GrantedAuthority, OwnedByTenant, ConfigAttribute {
    Tenant tenant;
    String authority;

    @Override
    public String getAttribute() {
        return authority;
    }
}
