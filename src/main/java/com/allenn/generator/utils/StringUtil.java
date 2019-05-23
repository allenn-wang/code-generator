package com.allenn.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-21
 */
public class StringUtil {
    public static String upperFirstChar(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str;
        }
    }

    public static String lowerFirstChar(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return str;
        }
    }

    public static String dbName2JavaName(String dbName) {
        String[] arrStr = dbName.split("_");
        String javaName = "";
        for (String s : arrStr) {
            javaName += StringUtil.upperFirstChar(s.toLowerCase());
        }
        return javaName;
    }

    public static String package2Path(String packageName) {
        if (StringUtils.isBlank(packageName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] packages = packageName.split("\\.");
        for (String str : packages) {
            sb.append(str).append(File.separator);
        }
        return sb.toString();
    }
}
