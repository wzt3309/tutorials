package io.zeddw.tutorial.domain.security.acl;

import java.util.List;
import java.util.Optional;

import io.zeddw.tutorial.domain.security.tenant.Tenant;
import io.zeddw.tutorial.domain.security.user.TenantUser;
import io.zeddw.tutorial.domain.security.user.TenantUserDetailsService;
import io.zeddw.tutorial.domain.security.util.Tenants;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * 租户隔离的ACL授权策略
 *
 * @author xingguan.wzt
 * @date 2020/10/20
 */
public class TenantPermissionGrantingStrategy implements ExtPermissionGrantingStrategy {

    private TenantUserDetailsService userDetailsService;

    private AuditLogger auditLogger;

    /**
     * 只有都允许才授权, 如果是 'admin 模式' 则始终允许
     *
     * @param acl                ACL
     * @param permission         请求的权限
     * @param sids               Sid
     * @param administrativeMode 是否admin模式
     * @return boolean
     */
    @Override
    public boolean isGranted(Acl acl, List<Permission> permission, List<Sid> sids, boolean administrativeMode) {
        if (administrativeMode) {
            return true;
        }

        CombPermission grantedPerm = getPermission(acl, sids);
        if (grantedPerm == null) {
            return false;
        }

        for (Permission reqPerm : permission) {
            int rMask = reqPerm.getMask();
            int gMask = grantedPerm.getMask();
            if ((rMask & gMask) != rMask) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CombPermission getPermission(Acl acl, List<Sid> sids) {
        final Sid ownerSid = acl.getOwner();
        notNull(ownerSid, "Acl owner is null");

        final Tenant ownerTenant = getTenant(ownerSid);
        isTrue(ownerTenant != Tenants.NO_TENANT, "Acl owner's has no tenant");
        final Tenant curPrincipalTenant = getPrincipalTenant(sids);
        CombPermission.Builder cpb = CombPermission.builder();
        cpb.add(getDefaultPermission(ownerSid, ownerTenant, sids));
        return null;
    }

    /**
     * Allow Permission by admin or owner
     *
     * @param ownerSid ACL owner
     * @param ownerTenant ownerSid's tenant
     * @param sids sid
     * @return permission
     */
    CombPermission getDefaultPermission(Sid ownerSid, Tenant ownerTenant, List<Sid> sids) {

    }

    Tenant getPrincipalTenant(List<Sid> sids) {
        Optional<PrincipalSid> principalSid = sids.stream()
            .filter(sid -> sid instanceof PrincipalSid)
            .map(sid -> (PrincipalSid)sid)
            .findFirst();
        return principalSid.map(this::getTenant).orElseThrow(
            () -> new IllegalStateException("Not found principal sid"));
    }

    Tenant getTenant(Sid sid) {
        if (sid == null) {
            return Tenants.NO_TENANT;
        }

        if (sid instanceof TenantSid) {
            return ((TenantSid)sid).getTenant();
        }

        // Get tenant from principal
        if (sid instanceof PrincipalSid) {
            TenantUser tenantUser = userDetailsService.loadUserByUsername(((PrincipalSid)sid).getPrincipal());
            if (tenantUser != null) {
                return tenantUser.getId().getTenant();
            }
        }

        return Tenants.NO_TENANT;
    }
}
