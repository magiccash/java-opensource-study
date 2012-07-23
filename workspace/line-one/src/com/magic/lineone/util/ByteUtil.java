package com.magic.lineone.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Tools to make conversions between byte sequence and java internal types All
 * byte sequences are in little-endian format, exception special case pointed in
 * annotation
 * 
 * @author loudly
 * 
 */
public class ByteUtil {

    private static Pattern md5Pattern = Pattern.compile ("[0-9a-f]{32}", Pattern.CASE_INSENSITIVE);

    public static byte [] integerToBytes (int n) {

        byte [] bytes = new byte [4];

        bytes [0] = (byte) (n & 0x000000FF);
        bytes [1] = (byte) ((n & 0x0000FF00) >> 8);
        bytes [2] = (byte) ((n & 0x00FF0000) >> 16);
        bytes [3] = (byte) ((n & 0xFF000000) >> 24);

        return bytes;
    }

    public static byte [] subbytes (byte [] bytes, int startindex, int endindex) {

        if (startindex < 0 || endindex > bytes.length || endindex < startindex)
            throw new ArrayIndexOutOfBoundsException ();
        byte [] b = new byte [endindex - startindex];
        for (int i = startindex; i < endindex; i++) {
            b [i - startindex] = bytes [i];
        }
        return b;
    }

    public static int bytesToInteger (byte [] bytes) {

        if (bytes.length != 4) {
            throw new IllegalArgumentException ("Input bytes should be 4-bytes long");
        }

        int n = 0;

        n |= ((bytes [0]) & 0x000000FF);
        n |= ((bytes [1]) & 0x000000FF) << 8;
        n |= ((bytes [2]) & 0x000000FF) << 16;
        n |= ((bytes [3]) & 0x000000FF) << 24;

        return n;
    }

    public static byte [] longIntegerToBytes (long n) {

        byte [] bytes = new byte [8];

        bytes [0] = (byte) (n & 0x00000000000000FFL);
        bytes [1] = (byte) ((n & 0x000000000000FF00L) >> 8);
        bytes [2] = (byte) ((n & 0x0000000000FF0000L) >> 16);
        bytes [3] = (byte) ((n & 0x00000000FF000000L) >> 24);
        bytes [4] = (byte) ((n & 0x000000FF00000000L) >> 32);
        bytes [5] = (byte) ((n & 0x0000FF0000000000L) >> 40);
        bytes [6] = (byte) ((n & 0x00FF000000000000L) >> 48);
        bytes [7] = (byte) ((n & 0xFF00000000000000L) >> 56);

        return bytes;
    }

    public static long bytesToLongInteger (byte [] bytes) {

        if (bytes.length != 8) {
            throw new IllegalArgumentException ("Input bytes should be 4-bytes long");
        }

        long n = 0;

        n |= ((bytes [0]) & 0x00000000000000FFL);
        n |= ((bytes [1]) & 0x00000000000000FFL) << 8;
        n |= ((bytes [2]) & 0x00000000000000FFL) << 16;
        n |= ((bytes [3]) & 0x00000000000000FFL) << 24;
        n |= ((bytes [4]) & 0x00000000000000FFL) << 32;
        n |= ((bytes [5]) & 0x00000000000000FFL) << 40;
        n |= ((bytes [6]) & 0x00000000000000FFL) << 48;
        n |= ((bytes [7]) & 0x00000000000000FFL) << 56;

        return n;
    }

    public static byte [] longArrayToByte (long [] longArray) {

        byte [] byteArray = new byte [longArray.length * 8];
        for (int i = 0; i < longArray.length; i++) {
            byteArray [0 + 8 * i] = (byte) (longArray [i] >> 56);
            byteArray [1 + 8 * i] = (byte) (longArray [i] >> 48);
            byteArray [2 + 8 * i] = (byte) (longArray [i] >> 40);
            byteArray [3 + 8 * i] = (byte) (longArray [i] >> 32);
            byteArray [4 + 8 * i] = (byte) (longArray [i] >> 24);
            byteArray [5 + 8 * i] = (byte) (longArray [i] >> 16);
            byteArray [6 + 8 * i] = (byte) (longArray [i] >> 8);
            byteArray [7 + 8 * i] = (byte) (longArray [i] >> 0);
        }
        return byteArray;
    }

    public static long [] byteToLongArray (byte [] byteArray) {

        if (byteArray == null)
            return null;
        if (byteArray.length % 8 != 0) {
            throw new IllegalArgumentException ("Input bytes should be times of 8 bytes long");
        }

        long [] longArray = new long [byteArray.length / 8];
        for (int i = 0; i < longArray.length; i++) {
            longArray [i] = (((long) byteArray [0 + 8 * i] & 0xff) << 56)
                    | (((long) byteArray [1 + 8 * i] & 0xff) << 48) | (((long) byteArray [2 + 8 * i] & 0xff) << 40)
                    | (((long) byteArray [3 + 8 * i] & 0xff) << 32) | (((long) byteArray [4 + 8 * i] & 0xff) << 24)
                    | (((long) byteArray [5 + 8 * i] & 0xff) << 16) | (((long) byteArray [6 + 8 * i] & 0xff) << 8)
                    | (((long) byteArray [7 + 8 * i] & 0xff) << 0);

        }
        return longArray;
    }

    public static byte [] floatToBytes (float n) {

        return integerToBytes (Float.floatToRawIntBits (n));
    }

    public static float bytesToFloat (byte [] bytes) {

        if (bytes.length != 4) {
            throw new IllegalArgumentException ("Input bytes should be 4-bytes long");
        }

        return Float.intBitsToFloat (bytesToInteger (bytes));
    }

    public static byte [] doubleToBytes (double n) {

        return longIntegerToBytes (Double.doubleToRawLongBits (n));
    }

    public static double bytesToDouble (byte [] bytes) {

        if (bytes.length != 8) {
            throw new IllegalArgumentException ("Input bytes should be 8-bytes long");
        }

        return Double.longBitsToDouble (bytesToLongInteger (bytes));
    }

    public static byte [] stringToBytes (String s, int len, String encoding) {

        byte [] bytes = new byte [len];
        int i = 0;
        try {
            for (byte b : s.getBytes (encoding)) {
                if (i >= len)
                    break;
                bytes [i] = b;
                i++;
            }
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
        return bytes;
    }

    public static String bytesToString (byte [] bytes, String encoding) {

        try {
            return new String (bytes, encoding);
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte [] blankBytes (int len) {

        return new byte [len];
    }

    /**
     * bytes is in big-endian order
     * 
     * @param bytes
     * @return
     */
    public static String bytesToMD5String (byte [] bytes) {

        BigInteger bigInt = new BigInteger (1, bytes);
        String hashText = bigInt.toString (16);

        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashText.length () < 32) {
            hashText = "0" + hashText;
        }

        return hashText;
    }

    /**
     * 
     * @param md5
     * @return bytes is in big-endian order
     */
    public static byte [] md5StringToBytes (String md5) {

        if (!md5Pattern.matcher (md5).matches ()) {
            throw new IllegalArgumentException ("Not a valid MD5 string");
        }

        BigInteger bigInt = new BigInteger (md5, 16);

        // The result may contain at least a SIG bit at front which should be
        // omitted,
        // The result may not be as long as 16 bytes if the md5 is small
        byte [] convertedBytes = bigInt.toByteArray ();
        byte [] bytes = new byte [16];
        int i;

        for (i = 0; i < 16; i++) {
            bytes [i] = 0;
        }

        i = convertedBytes.length;
        for (int j = 0; (j < 16) && (i - j > 0); j++) {
            bytes [15 - j] = convertedBytes [i - j - 1];
        }

        return bytes;
    }

    /**
     * 
     * @param obj
     * @return
     */

    public static byte [] objectToBytes (Serializable obj) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream ();
            ObjectOutputStream oos = new ObjectOutputStream (baos);
            oos.writeObject (obj);
            byte [] result = baos.toByteArray ();
            baos.close ();
            oos.close ();
            return result;
        }
        catch (IOException e) {

        }
        return null;
    }

    /**
     * 
     * @param bytes
     * @return
     */

    public static Object bytesToObject (byte [] bytes) {

        if (bytes == null || bytes.length == 0)
            return null;

        try {
            ObjectInputStream ois = new ObjectInputStream (new ByteArrayInputStream (bytes));
            Object obj = ois.readObject ();
            ois.close ();
            return obj;
        }
        catch (ClassNotFoundException e1) {

        }
        catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace ();
        }
        return null;
    }

    public static byte [] intArrayToBytes (int [] array) {

        if (array == null)
            return null;
        byte [] bytes = new byte [4 * array.length];

        for (int i = 0; i < array.length; i++) {
            final int n = array [i];
            bytes [0 + i * 4] = (byte) (n & 0x000000FF);
            bytes [1 + i * 4] = (byte) ((n & 0x0000FF00) >> 8);
            bytes [2 + i * 4] = (byte) ((n & 0x00FF0000) >> 16);
            bytes [3 + i * 4] = (byte) ((n & 0xFF000000) >> 24);
        }
        return bytes;
    }

    public static byte [] intArrayToBytes (Integer [] array) {

        byte [] bytes = new byte [4 * array.length];

        for (int i = 0; i < array.length; i++) {
            final int n = array [i];
            bytes [0 + i * 4] = (byte) (n & 0x000000FF);
            bytes [1 + i * 4] = (byte) ((n & 0x0000FF00) >> 8);
            bytes [2 + i * 4] = (byte) ((n & 0x00FF0000) >> 16);
            bytes [3 + i * 4] = (byte) ((n & 0xFF000000) >> 24);
        }
        return bytes;
    }

    public static int [] bytesToIntArray (byte [] bytes) {

        if (bytes == null)
            return null;
        if (bytes.length % 4 != 0) {
            throw new IllegalArgumentException ("The byte array is not valid. The length is " + bytes.length);
        }
        int [] ret = new int [bytes.length / 4];
        for (int i = 0; i < ret.length; i++) {
            ret [i] = 0;
            ret [i] |= ((bytes [0 + i * 4]) & 0x000000FF);
            ret [i] |= ((bytes [1 + i * 4]) & 0x000000FF) << 8;
            ret [i] |= ((bytes [2 + i * 4]) & 0x000000FF) << 16;
            ret [i] |= ((bytes [3 + i * 4]) & 0x000000FF) << 24;
        }
        return ret;
    }

    public static byte [] doubleArrayToBytes (double [] array) {

        byte [] bytes = new byte [8 * array.length];

        for (int i = 0; i < array.length; i++) {
            final long n = Double.doubleToRawLongBits (array [i]);
            bytes [0 + i * 8] = (byte) (n & 0x00000000000000FFL);
            bytes [1 + i * 8] = (byte) ((n & 0x000000000000FF00L) >> 8);
            bytes [2 + i * 8] = (byte) ((n & 0x0000000000FF0000L) >> 16);
            bytes [3 + i * 8] = (byte) ((n & 0x00000000FF000000L) >> 24);
            bytes [4 + i * 8] = (byte) ((n & 0x000000FF00000000L) >> 32);
            bytes [5 + i * 8] = (byte) ((n & 0x0000FF0000000000L) >> 40);
            bytes [6 + i * 8] = (byte) ((n & 0x00FF000000000000L) >> 48);
            bytes [7 + i * 8] = (byte) ((n & 0xFF00000000000000L) >> 56);
        }
        return bytes;
    }

    public static double [] bytesToDoubleArray (byte [] bytes) {

        if (bytes == null)
            return null;
        if (bytes.length % 8 != 0) {
            throw new IllegalArgumentException ("The byte array is not valid. The length is " + bytes.length);
        }
        double [] ret = new double [bytes.length / 8];
        for (int i = 0; i < ret.length; i++) {
            long n = 0;
            n |= ((bytes [0 + i * 8]) & 0x00000000000000FFL);
            n |= ((bytes [1 + i * 8]) & 0x00000000000000FFL) << 8;
            n |= ((bytes [2 + i * 8]) & 0x00000000000000FFL) << 16;
            n |= ((bytes [3 + i * 8]) & 0x00000000000000FFL) << 24;
            n |= ((bytes [4 + i * 8]) & 0x00000000000000FFL) << 32;
            n |= ((bytes [5 + i * 8]) & 0x00000000000000FFL) << 40;
            n |= ((bytes [6 + i * 8]) & 0x00000000000000FFL) << 48;
            n |= ((bytes [7 + i * 8]) & 0x00000000000000FFL) << 56;
            ret [i] = Double.longBitsToDouble (n);
        }
        return ret;
    }

    public static byte [] merge (byte [] b1, byte [] b2) {

        if (b1 == null || b2 == null)
            return null;
        byte [] ret = new byte [b1.length + b2.length];
        for (int i = 0; i < b1.length; i++)
            ret [i] = b1 [i];
        int j = b1.length;
        for (int i = 0; i < b2.length; i++, j++) {
            ret [j] = b2 [i];
        }
        return ret;
    }
}
