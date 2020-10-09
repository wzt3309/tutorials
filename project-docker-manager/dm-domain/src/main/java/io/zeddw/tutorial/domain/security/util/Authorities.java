package io.zeddw.tutorial.domain.security.util;

import io.zeddw.tutorial.domain.security.authority.TenantGrantedAuthority;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * 权限相关常量和静态方法
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@UtilityClass
public class Authorities {
    public final String ROLE_PREFIX = "ROLE_";

    public final String ROLE_ADMIN = "ROLE_ADMIN";
    public final String ROLE_USER = "ROLE_USER";

    public final TenantGrantedAuthority AUTHORITY_ADMIN = from(ROLE_ADMIN);
    public final TenantGrantedAuthority AUTHORITY_USER = from(ROLE_USER);

    public TenantGrantedAuthority from(String role) {
        notNull(role, "Role is null");
        return new TenantGrantedAuthority(Tenants.NO_TENANT, format(role));
    }

    public TenantGrantedAuthority from(Tenant tenant, String role) {
        notNull(tenant, "Tenant is null");
        notNull(role, "Role is null");
        return new TenantGrantedAuthority(tenant, format(role));
    }

    private String format(String role) {
        String upperCase = StringUtils.upperCase(role);
        if (StringUtils.startsWith(upperCase, ROLE_PREFIX)) {
            return role;
        }
        return ROLE_PREFIX + upperCase;
    }
}
