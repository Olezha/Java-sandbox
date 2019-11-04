package edu.olezha.sandbox.string;

/**
 * Created by oleh on 27.12.17.
 */
public class MainSBuilder {
    private static String string = "Some string";

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder(string);
        System.out.println(stringBuilder.reverse().toString());

        stringBuilder = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--)
            stringBuilder.append(string.charAt(i));
        String reversedString = stringBuilder.toString();

        stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++)
            stringBuilder.insert(0, string.charAt(i));
        String reversedStringV2 = stringBuilder.toString();

        char[] stringArr = string.toCharArray();
        for (int i = 0; i < stringArr.length / 2; i++) {
            char c = stringArr[i];
            stringArr[i] = stringArr[stringArr.length - 1 - i];
            stringArr[stringArr.length - 1 - i] = c;
        }
        System.out.println(String.valueOf(stringArr));

        System.out.println(reversedString.equals(reversedStringV2));
        System.out.println(reversedString == reversedStringV2);

        reversedString = reversedString.intern();
        reversedStringV2 = reversedStringV2.intern();
        System.out.println(reversedString == reversedStringV2);
    }
}
