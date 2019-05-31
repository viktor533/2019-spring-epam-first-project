package com.epam.helloworld;

import com.epam.utils.DBConnectionUtils;
import com.epam.utils.LocaleUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Locale;

@Slf4j
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        LocaleUtils localeUtils = LocaleUtils.getInstance("labels_cat_CAT", Locale.getDefault());
        System.out.println(LocaleUtils.getByKey("someKey"));

        log.trace("Trace log message");
        log.debug("Debug log message");
        log.info("Info log message");
        log.warn("Warn log message");
        log.error("Error log message");

        Connection connection = DBConnectionUtils.getConnection("jdbc:h2:~/test", "sa", "");
    }
}
