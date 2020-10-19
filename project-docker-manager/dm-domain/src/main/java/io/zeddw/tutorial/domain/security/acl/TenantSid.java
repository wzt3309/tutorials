package io.zeddw.tutorial.domain.security.acl;

import io.zeddw.tutorial.domain.security.tenant.OwnedByTenant;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Sid;

/**
 * 扩展 ACL Sid 增加租户支持
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public interface TenantSid extends Sid, OwnedByTenant {

    /**
     * Sid -> TenantSid;
     *
     * @param sid sid
     * @return TenantSid
     */
    static TenantSid from(Sid sid) {
        if (sid == null) {
            return null;
        }
        if (sid instanceof TenantSid) {
            return (TenantSid)sid;
        }

        if (sid instanceof PrincipalSid) {
            return TenantPrincipalSid.from((PrincipalSid)sid);
        }

        if (sid instanceof GrantedAuthoritySid) {
            return TenantGrantedAuthoritySid.from((GrantedAuthoritySid) sid);
        }

        throw new IllegalArgumentException("Unsupported sid type: " + sid.getClass());
    }
}
