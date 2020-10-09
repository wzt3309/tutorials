package io.zeddw.tutorial.domain.security.tenant;

/**
 * 标识 - 受租户管理的值对象
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public interface OwnedByTenant {

    /**
     * 获取租户
     *
     * @return Tenant
     */
    Tenant getTenant();
}
