package io.zeddw.tutorial.domain.security.user;

import io.zeddw.tutorial.common.domain.Identifier;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * 值对象: 用户认证token
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class UserCompositeAuthenticationToken extends AbstractAuthenticationToken implements Identifier {
    UserCompositePrincipal principal;
    Object credentials;
}
