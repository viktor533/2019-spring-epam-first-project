package com.epam.helloworld;

import com.epam.utils.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        LocaleUtils localeUtils = LocaleUtils.getInstance("labels_dog_DOG", Locale.getDefault());
        System.out.println(LocaleUtils.getByKey("someKey"));

        logger.trace("Trace log message");
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.warn("Warn log message");
        logger.error("Error log message");
    }
}
