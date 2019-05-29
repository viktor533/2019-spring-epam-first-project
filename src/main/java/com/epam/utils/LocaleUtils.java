package com.epam.utils;

import java.util.ResourceBundle;
import java.util.Locale;

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
                if(instance == null) {
                    instance = new LocaleUtils(property, locale);
                }
            }
        }

        return instance;
    }
}
