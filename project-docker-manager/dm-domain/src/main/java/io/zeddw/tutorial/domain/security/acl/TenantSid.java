package io.zeddw.tutorial.domain.security.acl;

import io.zeddw.tutorial.domain.security.tenant.OwnedByTenant;
import org.springframework.security.acls.model.Sid;

/**
 * 扩展 ACL Sid 增加租户支持
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public interface TenantSid extends Sid, OwnedByTenant {
}
