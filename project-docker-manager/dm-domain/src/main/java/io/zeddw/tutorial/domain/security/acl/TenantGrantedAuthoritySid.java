package io.zeddw.tutorial.domain.security.acl;

import io.zeddw.tutorial.common.domain.Identifier;
import io.zeddw.tutorial.domain.security.authority.TenantGrantedAuthority;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import io.zeddw.tutorial.domain.security.util.Tenants;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.core.GrantedAuthority;

/**
 * 值对象: 租户隔离的权限({@link TenantGrantedAuthority})Sid
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantGrantedAuthoritySid extends GrantedAuthoritySid implements TenantSid, Identifier {
    Tenant tenant;

    public TenantGrantedAuthoritySid(String grantedAuthority, Tenant tenant) {
        super(grantedAuthority);
        this.tenant = tenant;
    }

    public TenantGrantedAuthoritySid(GrantedAuthority grantedAuthority) {
        super(grantedAuthority);
        this.tenant = Tenants.getTenant(grantedAuthority);
    }
}
