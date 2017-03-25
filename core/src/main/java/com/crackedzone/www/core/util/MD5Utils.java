package com.crackedzone.www.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Package com.smartchoiceads.sdk.business.util
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
public class MD5Utils {

    public static String encrypt(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(source.getBytes());
        byte[]        digest       = md.digest();
        StringBuilder stringBuffer = new StringBuilder();
        for (byte b : digest) {
            stringBuffer.append(String.format("%02x", b & 0xff));
        }
        return stringBuffer.toString();
    }
}
