package io.zeddw.tutorial.domain.security.user;

import javax.validation.constraints.NotBlank;

import io.zeddw.tutorial.common.domain.Identifier;
import lombok.Value;

/**
 * 值对象: 用户信息票据
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
@Value
public class UserCompositePrincipal implements Identifier {

    /**
     * login username
     */
    @NotBlank
    String username;

    /**
     * login email
     */
    @NotBlank
    String email;
}
