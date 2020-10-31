package io.zeddw.tutorial.domain.security.acl;

import java.util.List;

import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.acls.model.Sid;

/**
 * 扩展ACL授权策略
 *
 * @author xingguan.wzt
 * @date 2020/10/20
 */
public interface ExtPermissionGrantingStrategy extends PermissionGrantingStrategy {

    /**
     * 给定ACL和Sid返回已授权的策略
     *
     * @param acl ACL
     * @param sids Sid
     * @return 已授权的策略
     */
    CombPermission getPermission(Acl acl, List<Sid> sids);
}
