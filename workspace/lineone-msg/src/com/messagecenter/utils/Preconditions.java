package com.messagecenter.utils;

public class Preconditions {

    public static boolean isNotNull (String str) {

        if (str == null || "".equals (str) || "null".equals (str)) {
            return false;
        }
        else {
            return true;
        }
    }
}
