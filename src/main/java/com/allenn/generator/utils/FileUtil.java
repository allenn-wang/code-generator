package com.allenn.generator.utils;

import org.apache.commons.io.Charsets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class FileUtil {
    public static String getProjectTargetJavaPath() {
        return getProjectTargetPath("java");
    }

    public static String getProjectTargetResourcePath() {
        return getProjectTargetPath("resources");
    }

    private static String getProjectTargetPath(String pathType) {
        String path = FileUtil.class.getClassLoader().getResource("").getFile();
        StringBuilder stb = new StringBuilder();
        stb.append(path.substring(0, path.indexOf("classes"))).append("src")
                .append(File.separator).append("main").append(File.separator)
                .append(pathType).append(File.separator);
        return stb.toString();
    }

    public static boolean openFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.err.println("File '" + file + "' exists but is a directory");
                return false;
            }
            if (file.canWrite() == false) {
                System.err.println("File '" + file + "' cannot be written to");
                return false;
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    System.err.println("Directory '" + parent + "' could not be created");
                    return false;
                }
            }
        }
        return true;
    }

    public static void generate2Java(File file, String fileContent) {
        try (OutputStream out = new FileOutputStream(file, false)) {
            out.write(fileContent.getBytes(Charsets.toCharset("UTF-8")));
        } catch (Exception e) {
            System.err.println("File '" + file.getPath() + " write failure");
            e.printStackTrace();
        }
    }
}
