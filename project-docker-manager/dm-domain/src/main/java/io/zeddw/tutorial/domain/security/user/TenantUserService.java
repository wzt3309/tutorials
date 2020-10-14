package io.zeddw.tutorial.domain.security.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 领域服务: 根据用户标识或者用户名加载用户
 *
 * @author xingguan.wzt
 * @date 2020/10/10
 */
public interface TenantUserService extends UserDetailsService {

    /**
     * 根据用户名加载用户信息
     *
     * @param username username
     * @return TenantUser
     * @throws UsernameNotFoundException not found or the user has no GrantedAuthority
     */
    @Override
    TenantUser loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * 根据用户ID加载用户信息
     *
     * @param id id
     * @return TenantUser
     */
    TenantUser loadUserById(UserId id);
}
