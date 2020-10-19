package io.zeddw.tutorial.domain.security.acl;

import org.springframework.security.acls.domain.AclFormattingUtils;
import org.springframework.security.acls.model.Permission;

/**
 * 受权限控制的动作枚举
 *
 * @author xingguan.wzt
 * @date 2020/10/09
 */
public enum Action implements Permission {
    /**
     * CRUD/执行 权限
     */
    READ(0),
    CREATE(1),
    UPDATE(2),
    DELETE(3),
    EXECUTE(4),

    ;
    final int mask;
    final char c;

    Action(int pos) {
        mask = 1 << pos;
        c = name().charAt(0);
    }

    @Override
    public int getMask() {
        return mask;
    }

    @Override
    public String getPattern() {
        return AclFormattingUtils.printBinary(mask, c);
    }

    public static Action of(char c) {
        for (Action action : values()) {
            if (action.c == c) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown enum constant Action." + c);
    }

    public static Action ofNullable(char c) {
        for (Action action : values()) {
            if (action.c == c) {
                return action;
            }
        }
        return null;
    }
}
