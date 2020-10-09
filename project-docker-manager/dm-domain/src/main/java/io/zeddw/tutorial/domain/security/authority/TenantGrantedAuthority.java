package io.zeddw.tutorial.domain.security.authority;

import io.zeddw.tutorial.common.domain.Identifier;
import io.zeddw.tutorial.domain.security.tenant.OwnedByTenant;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import io.zeddw.tutorial.domain.security.util.Tenants;
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
public class TenantGrantedAuthority implements GrantedAuthority, OwnedByTenant, ConfigAttribute, Identifier {
    Tenant tenant;
    String authority;

    public static TenantGrantedAuthority from(GrantedAuthority authority) {
        if (authority == null) {
            return null;
        }
        if (authority instanceof TenantGrantedAuthority) {
            return (TenantGrantedAuthority)authority;
        }

        return new TenantGrantedAuthority(Tenants.getTenant(authority), authority.getAuthority());
    }

    @Override
    public String getAttribute() {
        return authority;
    }
}
