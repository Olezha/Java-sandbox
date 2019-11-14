package edu.olezha.sandbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256Hash {

    private static final String KEY_PREFIX = "01e4";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Random random = new Random();
        byte[] data = new byte[256];

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        for (; ; ) {
            random.nextBytes(data);
            String hash = bytesToHex(messageDigest.digest(data));

            if (hash.startsWith(KEY_PREFIX)) {
                System.out.println(hash);
                break;
            }
        }

        try (FileOutputStream stream = new FileOutputStream(new File("data"))) {
            stream.write(data);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
