package io.zeddw.tutorial.domain.security.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import io.zeddw.tutorial.common.domain.Entity;
import io.zeddw.tutorial.domain.security.authority.TenantGrantedAuthority;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import io.zeddw.tutorial.domain.security.util.Tenants;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 实体: 租户隔离的用户信息
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@Data
public class TenantUser implements UserDetails, Entity<UserId> {
    private final UserId id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(UserDetails userDetails) {
        return builder().userDetails(userDetails);
    }

    public static final class Builder {
        private final Set<GrantedAuthority> authorities = new HashSet<>();
        private UserId id;
        private String username;
        private String password;
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;
        private boolean enabled;

        public Builder userDetails(UserDetails userDetails) {
            if (userDetails == null) {
                return this;
            }

            if (userDetails instanceof TenantUser) {
                id(((TenantUser)userDetails).getId());
            } else {
                id(Tenants.getTenant(userDetails), null);
            }
            username(userDetails.getUsername());
            password(userDetails.getPassword());
            setAuthorities(userDetails.getAuthorities());
            accountNonExpired(userDetails.isAccountNonExpired());
            accountNonLocked(userDetails.isAccountNonLocked());
            credentialsNonExpired(userDetails.isCredentialsNonExpired());
            enabled(userDetails.isEnabled());
            return this;
        }

        public Builder id(UserId id) {
            this.id = id;
            return this;
        }

        public Builder id(Tenant tenant, String email) {
            this.id = new UserId(tenant, email);
            return this;
        }

        public Builder id(String tenant, String email) {
            this.id = new UserId(new Tenant(tenant), email);
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder addAuthority(GrantedAuthority authority) {
            if (authority != null) {
                this.authorities.add(TenantGrantedAuthority.from(authority));
            }
            return this;
        }

        public Builder addAuthorities(Collection<? extends GrantedAuthority> authorities) {
            setAuthorities(authorities);
            return this;
        }

        public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities.clear();
            if (authorities != null) {
                this.authorities.addAll(
                    authorities.stream().map(TenantGrantedAuthority::from).collect(Collectors.toList()));
            }
        }

        public Builder accountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder accountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public Builder credentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public TenantUser build() {
            TenantUser tenantUser = new TenantUser(id);
            tenantUser.setUsername(username);
            tenantUser.setPassword(password);
            tenantUser.setAuthorities(authorities);
            tenantUser.setAccountNonExpired(accountNonExpired);
            tenantUser.setAccountNonLocked(accountNonLocked);
            tenantUser.setCredentialsNonExpired(credentialsNonExpired);
            tenantUser.setEnabled(enabled);
            return tenantUser;
        }
    }
}
