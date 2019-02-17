package com.type.unit;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.CharMatcher;

/**
 * @author qiguangjie
 * 共通处理
 *
 */
public class CommonUnit {
    /**
     * Trim full space
     * example:
     * "　abc123　"---abc123
     * "abc123　" ---abc123
     * "　abc123" ---abc123
     * "abc　123"---abc　123
     * @param str
     * @return
     */
    public static String getTrimFullWidthSpace(String str) {
        return CharMatcher.WHITESPACE.trimFrom(str).toString();
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc123　","abc123　","　abc123","abc　123");
        list.forEach(Str ->System.out.println(getTrimFullWidthSpace(Str)));
    }
}
