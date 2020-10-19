package io.zeddw.tutorial.domain.security.acl;

import lombok.Value;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AuditableAccessControlEntry;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * ACE 实现
 *
 * @author xingguan.wzt
 * @date 2020/10/14
 */
@Value
public class AceImpl implements AuditableAccessControlEntry {
    String id;
    Acl acl;
    CombPermission permission;
    TenantSid sid;
    boolean granting;
    boolean auditFailure;
    boolean auditSuccess;

    public static AceImpl from(AccessControlEntry entry) {
        if (entry == null) {
            return null;
        }
        if (entry instanceof AceImpl) {
            return (AceImpl)entry;
        }

        Builder b = builder().id(entry.getId() == null ? null : String.valueOf(entry.getId()))
            .acl(entry.getAcl())
            .permission(entry.getPermission())
            .sid(entry.getSid())
            .granting(entry.isGranting());
        if (entry instanceof AuditableAccessControlEntry) {
            AuditableAccessControlEntry ae = (AuditableAccessControlEntry)entry;
            return b.auditFailure(ae.isAuditFailure())
                .auditSuccess(ae.isAuditSuccess())
                .build();
        }
        return b.build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(AccessControlEntry entry) {
        return new Builder(entry);
    }

    public static class Builder {
        private String id;
        private Acl acl;
        private CombPermission permission;
        private TenantSid sid;
        private boolean granting;
        private boolean auditFailure = false;
        private boolean auditSuccess = false;

        public Builder() {
        }

        public Builder(AccessControlEntry entry) {
            this.id = entry.getId() == null ? null : String.valueOf(entry.getId());
            this.sid = TenantSid.from(entry.getSid());
            this.acl = entry.getAcl();
            this.permission = CombPermission.from(entry.getPermission());
            this.granting = entry.isGranting();
            if (entry instanceof AuditableAccessControlEntry) {
                AuditableAccessControlEntry ae = (AuditableAccessControlEntry)entry;
                this.auditFailure = ae.isAuditFailure();
                this.auditSuccess = ae.isAuditSuccess();
            }
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder acl(Acl acl) {
            this.acl = acl;
            return this;
        }

        public Builder permission(Permission perm) {
            this.permission = CombPermission.from(perm);
            return this;
        }

        public Builder sid(Sid sid) {
            this.sid = TenantSid.from(sid);
            return this;
        }

        public Builder granting(boolean granting) {
            this.granting = granting;
            return this;
        }

        public Builder auditFailure(boolean auditFailure) {
            this.auditFailure = auditFailure;
            return this;
        }

        public Builder auditSuccess(boolean auditSuccess) {
            this.auditSuccess = auditSuccess;
            return this;
        }

        public AceImpl build() {
            notNull(sid, "Sid is null");
            notNull(permission, "Sid is null");
            return new AceImpl(id, acl, permission, sid, granting, auditFailure, auditSuccess);
        }
    }
}
