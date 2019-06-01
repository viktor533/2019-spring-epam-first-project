package com.epam.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class LocaleUtils {
    private static final String BUNDLE_NAME = "labels";
    private static final LocaleUtils instance = new LocaleUtils();
    private static ResourceBundle messages = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());

    private LocaleUtils() {}

    public static String getByKey(String key) {
        return messages.getString(key);
    }

    public static LocaleUtils getInstance() {
        return instance;
    }

    public static void setLocale(Locale locale) {
        messages = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }
}
