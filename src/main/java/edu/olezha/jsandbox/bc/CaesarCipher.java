package edu.olezha.jsandbox.bc;

public class CaesarCipher {

    private static final String alphabet = "абвгдежзийклмнопрстуфхцчшщьі-єюя";

    public static void main(String[] args) {
        final String cipher = "Яфр нхщькл мхту ср гнрмщзлпх";

        for (int i = -100; i < 100; i++) {
            StringBuilder sb = new StringBuilder(cipher.length());
            for (int j = 0; j < cipher.length(); j++) {
                char c = cipher.charAt(j);

                if (c == ' ') {
                    sb.append(c);
                    continue;
                }

                int index = alphabet.lastIndexOf(c) + i;
                while (index >= alphabet.length())
                    index -= alphabet.length();
                while (index < 0)
                    index += alphabet.length();

                char real = alphabet.charAt(index);
                sb.append(real);
            }

            String result = sb.toString();
            if (result.contains("по"))
                System.out.printf("%4d: %s" + System.lineSeparator(), i, result);
        }
    }
}
