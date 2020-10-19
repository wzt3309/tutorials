package io.zeddw.tutorial.domain.security.acl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.acls.model.UnloadedSidException;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * ACL 实现
 *
 * @author xingguan.wzt
 * @date 2020/10/19
 */
@Value
public class AclImpl implements Acl {
    List<AceImpl> entries;
    ObjectIdentity objectIdentity;
    TenantSid owner;
    AclImpl parentAcl;
    boolean entriesInheriting;
    transient PermissionGrantingStrategy permissionGrantingStrategy;

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean isGranted(List<Permission> permission, List<Sid> sids, boolean administrativeMode)
        throws NotFoundException, UnloadedSidException {
        notNull(permission, "Permission is null");
        notNull(sids, "Sid is null");

        if (!isSidLoaded(sids)) {
            throw new UnloadedSidException("ACL was not loaded for one or more sids");
        }

        return permissionGrantingStrategy.isGranted(this, permission, sids, administrativeMode);
    }

    @Override
    public boolean isSidLoaded(List<Sid> sids) {
        return true;
    }

    public static class Builder {
        private Map<String, AceImpl> entries;
        private ObjectIdentity objectIdentity;
        private TenantSid owner;
        private AclImpl parentAcl;
        private boolean entriesInheriting;
        private transient PermissionGrantingStrategy strategy;

        public Builder objectIdentity(ObjectIdentity objectIdentity) {
            this.objectIdentity = objectIdentity;
            return this;
        }

        public Builder owner(TenantSid owner) {
            this.owner = owner;
            return this;
        }

        public Builder parentAcl(AclImpl parentAcl) {
            this.parentAcl = parentAcl;
            return this;
        }

        public Builder entriesInheriting(boolean entriesInheriting) {
            this.entriesInheriting = entriesInheriting;
            return this;
        }

        public Builder permissionGrantingStrategy(PermissionGrantingStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public Builder addEntries(List<AceImpl> entries) {
            this.entries.clear();
            if (entries != null) {
                entries.forEach(this::addEntry);
            }
            return this;
        }

        public Builder addEntry(AceImpl entry) {
            if (entry == null) {
                return this;
            }

            computeIfAbsent(entry, key -> AceImpl.builder(entry).id(key).build());
            return this;
        }

        private void computeIfAbsent(AceImpl entry, Function<String, AceImpl> func) {
            if (entry.getId() != null) {
                entries.put(entry.getId(), entry);
                return;
            }

            while (true) {
                String unique = UUID.randomUUID().toString();
                if (!entries.containsKey(unique)) {
                    entries.computeIfAbsent(unique, func);
                    return;
                }
            }
        }

        public AclImpl build() {
            notNull(objectIdentity, "ObjectIdentity is null");
            notNull(owner, "Owner is null");
            notNull(owner.getTenant(), "Owner's tenant is null");
            notNull(strategy, "PermissionGrantingStrategy is null");

            List<AceImpl> entries = ImmutableList.copyOf(this.entries.values());
            return new AclImpl(entries, objectIdentity, owner, parentAcl, entriesInheriting, strategy);
        }
    }
}
