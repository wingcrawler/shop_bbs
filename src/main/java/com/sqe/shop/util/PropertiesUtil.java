package com.sqe.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {
	
	
	public static Properties props = new Properties();
	
    static {
        InputStream in = PropertiesUtil.class.getResourceAsStream("/application.properties");
        try {
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static String get(String key) {
		try {
			if (StringUtils.isBlank(key)) {
				return "";
			} else {
				return (String) props.get(key);
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String get(String key, String defaultValue) {
		try {
			if (StringUtils.isBlank(key)) {
				return defaultValue;
			} else {
				return (String) props.get(key);
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Integer getInt(String key) {
		try {
			if (StringUtils.isBlank(key)) {
				return 0;
			} else {
				return (Integer) props.get(key);
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Integer getInt(String key, int defaultValue) {
		try {
			if (StringUtils.isBlank(key)) {
				return defaultValue;
			} else {
				return (Integer) props.get(key);
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Long getLong(String key) {
		try {
			if (StringUtils.isBlank(key)) {
				return 0L;
			} else {
				return (Long) props.get(key);
			}
		} catch (Exception e) {
			return 0L;
		}
	}
	
	public static Long getLong(String key, Long defaultValue) {
		try {
			if (StringUtils.isBlank(key)) {
				return defaultValue;
			} else {
				return (Long) props.get(key);
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	

}
