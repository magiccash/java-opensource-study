package com.magic.lineone.util;

public class HexUtil {

    public static String getHex (int number, int length) {

        String result = new String ();
        int i = 0;
        while (number > 0) {
            result = getHex (number % 16) + result;
            number /= 16;
            i++;
            if (i == length)
                break;
        }
        if (i < length) {
            StringBuffer zero = new StringBuffer ();
            for (int j = i; j < length; j++)
                zero.append ('0');
            result = zero.toString () + result;
        }
        return result;
    }

    public static String getHexStringRandomly (int length) {

        StringBuffer ret = new StringBuffer ();
        for (int i = 0; i < length; i++) {
            ret.append (getHex ((int) (Math.random () * 16)));
        }
        return ret.toString ();
    }

    private static char getHex (int number) {

        if (number < 10)
            return (char) (number + 48);
        return (char) (number + 55);
    }

    /**
     * Generate the validate code (ten digital or alphabet char)
     * @return
     */
    public static String generateValidateCode (int n) {

        String ret = new String ();
        for (int i = 0; i < n; i++) {
            ret += generateChar ();
        }
        return ret;
    }

    private static char generateChar () {

        int n = (int) (Math.random () * 62);
        final char c;
        if (n < 10)
            c = (char) (n + 48);
        else if (n >= 10 && n < 36)
            c = (char) (n + 55);
        else
            c = (char) (n + 61);
        return c;
    }

}
