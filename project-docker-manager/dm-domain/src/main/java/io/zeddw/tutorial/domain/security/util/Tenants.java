package io.zeddw.tutorial.domain.security.util;

import io.zeddw.tutorial.domain.security.tenant.OwnedByTenant;
import io.zeddw.tutorial.domain.security.tenant.Tenant;
import lombok.experimental.UtilityClass;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * 租户相关的常量和静态方法
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@UtilityClass
public class Tenants {

    public final Tenant NO_TENANT = new Tenant("");
    public final Tenant ANONYMOUS_TENANT = new Tenant("anonymous_tenant");
    public final Tenant ROOT_TENANT = new Tenant("root_tenant");


    public static Tenant getTenant(Object val) {
        notNull(val, "Tenant object is null");

        if (val instanceof OwnedByTenant) {
            return ((OwnedByTenant)val).getTenant();
        }

        return NO_TENANT;
    }
}
