package com.epam.helloworld;

import com.epam.utils.LocaleUtils;

import java.util.Locale;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        LocaleUtils localeUtils = LocaleUtils.getInstance("labels_dog_DOG", Locale.getDefault());
        System.out.println(LocaleUtils.getByKey("someKey"));
    }
}
