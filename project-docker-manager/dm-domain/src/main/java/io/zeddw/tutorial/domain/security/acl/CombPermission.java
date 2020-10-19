package io.zeddw.tutorial.domain.security.acl;

import lombok.Value;
import org.springframework.security.acls.domain.AclFormattingUtils;
import org.springframework.security.acls.model.Permission;

/**
 * 由多个单独的 Permission 组合成的 Permission
 *
 * 不可变类, 新权限需要通过 Builder 重新创建
 *
 * e.g. rwx 权限由 r(read) w(write) x(execute) 组合
 * @author xingguan.wzt
 * @date 2020/10/14
 */
@Value
public class CombPermission implements Permission {
    /**
     * 权限的32位(int mask)掩码的字符形式, 使用默认占位符 '*'
     */
    String pattern;
    /**
     * 权限的bit掩码
     */
    int mask;

    /**
     * 权限的{@link Action#c}表示 易读
     * e.g. RW
     */
    String expression;

    /**
     * 从比如 'RW' 构建 CombPermission
     *
     * @param expression expression
     * @return CombPermission
     */
    public static CombPermission from(String expression) {
        int mask = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            Action action = Action.of(c);
            mask |= action.mask;
        }
        return new CombPermission(null, mask);
    }

    public static CombPermission from(Permission perm) {
        if (perm == null || perm instanceof CombPermission) {
            return (CombPermission) perm;
        }
        return new CombPermission(perm.getPattern(), perm.getMask());
    }

    public static Builder builder() {
        return new Builder();
    }

    public CombPermission(String pattern, int mask) {
        this.mask = mask;
        this.pattern = pattern != null ? pattern : AclFormattingUtils.printBinary(mask);

        StringBuilder sbd = new StringBuilder();
        for (Action action : Action.values()) {
            if ((action.mask & mask) != 0) {
                sbd.append(action.c);
            }
        }
        this.expression = sbd.toString();
    }

    public static class Builder {
        private String pattern = THIRTY_TWO_RESERVED_OFF;
        private int mask;

        public Builder add(Permission perm) {
            mask |= perm.getMask();
            pattern = AclFormattingUtils.mergePatterns(pattern, perm.getPattern());
            return this;
        }

        public Builder add(Permission... perms) {
            for (Permission perm : perms) {
                add(perm);
            }
            return this;
        }

        public Builder remove(Permission perm) {
            mask &= ~perm.getMask();
            pattern = AclFormattingUtils.demergePatterns(pattern, perm.getPattern());
            return this;
        }

        public Builder clear() {
            mask = 0;
            pattern = THIRTY_TWO_RESERVED_OFF;
            return this;
        }

        public CombPermission build() {
            return new CombPermission(pattern, mask);
        }
    }
}
