package com.allenn.generator.utils;

import com.allenn.generator.entity.Configuration;
import com.allenn.generator.entity.SysConfig;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.URL;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class ConfigUtil {
    private static Configuration configuration;

    static {
        URL url = ConfigUtil.class.getClassLoader().getResource("generator.yaml");
        if (url == null || StringUtils.isEmpty(url.getPath())) {
            System.err.println("Can not find file named 'generator.yaml' at resources path, " +
                    "please make sure that you have defined that file.");
            System.exit(0);
        } else {
            InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("generator.yaml");
            SysConfig sysConfig = new Yaml().loadAs(inputStream, SysConfig.class);
            configuration = sysConfig.getModules().get(sysConfig.getModuleName());
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
