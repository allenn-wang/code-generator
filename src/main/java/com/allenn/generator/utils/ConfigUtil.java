package com.allenn.generator.utils;

import com.allenn.generator.entity.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.URL;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class ConfigUtil {
    private static Configuration configuration;

    static {
        URL url = ConfigUtil.class.getClassLoader().getResource("generator.yaml");
        if (url == null || url.getPath() == null || "".equals(url.getPath())) {
            System.err.println("Can not find file named 'generator.yaml' at resources path, " +
                    "please make sure that you have defined that file.");
            System.exit(0);
        } else {
            InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("generator.yaml");
            configuration = new Yaml().loadAs(inputStream, Configuration.class);
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
