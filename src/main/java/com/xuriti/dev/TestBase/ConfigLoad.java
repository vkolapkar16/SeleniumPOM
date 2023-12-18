package com.xuriti.dev.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ConfigLoad {
    public Properties prop;

    public ConfigLoad() throws IOException {
        prop = new Properties();
        FileInputStream ip = new FileInputStream("src/main/resources/Config.properties");
        prop.load(ip);
    }
    public String getProp(String key)
    {
        return prop.getProperty(key);
    }
}
