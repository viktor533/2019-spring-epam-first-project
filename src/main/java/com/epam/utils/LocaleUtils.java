package com.epam.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class LocaleUtils {
    private static LocaleUtils instance;
    private static ResourceBundle messages;

    private LocaleUtils(String property, Locale locale) {
        messages = ResourceBundle.getBundle(property, locale);
    }

    public static String getByKey(String key) {
        return messages.getString(key);
    }

    public static LocaleUtils getInstance(String property, Locale locale) {
        if (instance == null) {
            synchronized (LocaleUtils.class) {
                if (instance == null) {
                    instance = new LocaleUtils(property, locale);
                    log.info("Logger was instantiated.");
                }
            }
        }

        return instance;
    }
}
