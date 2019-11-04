package edu.olezha.sandbox.core;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

public class Encoding {

    public static void main(String[] args) {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode("jcTJ0xeCHDZT9ruwuDHIt32TdBkKTHDR1lmM2TU-HvU".getBytes());
        System.out.println(new String(Base64.encodeBase64(decoded)));
    }
}
