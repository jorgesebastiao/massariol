package br.com.massariol.application.users.utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomUtil {
    private static final int DEF_COUNT = 12;

    public static String getNewPassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }
}
