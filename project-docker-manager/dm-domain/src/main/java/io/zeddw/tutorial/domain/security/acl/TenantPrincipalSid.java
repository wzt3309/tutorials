package io.zeddw.tutorial.domain.security.acl;

import io.zeddw.tutorial.common.domain.Identifier;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import io.zeddw.tutorial.domain.security.util.Tenants;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * 值对象: 租户隔离的用户身份票据Sid
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantPrincipalSid extends PrincipalSid implements TenantSid, Identifier {
    Tenant tenant;

    public TenantPrincipalSid(String principal, Tenant tenant) {
        super(principal);

        notNull(tenant, "Tenant is null");
        this.tenant = tenant;
    }

    public TenantPrincipalSid(Authentication authentication) {
        super(authentication);

        Tenant tenant = Tenants.getTenant(authentication.getPrincipal());
        notNull(tenant, "Get tenant from principal is null");
        this.tenant = tenant;
    }

    public static TenantPrincipalSid from(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }
        return new TenantPrincipalSid(userDetails.getUsername(), Tenants.getTenant(userDetails));
    }

    public static TenantPrincipalSid from(PrincipalSid sid) {
        if (sid == null) {
            return null;
        }
        if (sid instanceof TenantPrincipalSid) {
            return (TenantPrincipalSid)sid;
        }
        return new TenantPrincipalSid(sid.getPrincipal(), Tenants.getTenant(sid));
    }
}
