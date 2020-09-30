package io.zeddw.method;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * 重载方法是如何被选择的
 *
 * 编译时确定
 *
 * @author xingguan.wzt
 * @date 2020/09/30
 */
public class OverloadMethod {

    public static void main(String[] args) {
        Object arg = Arrays.asList("test1", "test2");
        String out = method(arg);
        System.out.println(out);
    }

    static String method(Object... args) {
        StringJoiner joiner = new StringJoiner(" ", "method1", "");
        if (args != null) {
            for (Object arg : args) {
                joiner.add(arg.toString());
            }
        }
        return joiner.toString();
    }

    static String method(Object arg) {
        return "method2 " + arg.toString();
    }

    static String method(List<String> arg) {
        return "method3 " + String.join(" ", arg);
    }
}
