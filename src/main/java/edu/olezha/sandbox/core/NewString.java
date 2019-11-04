package edu.olezha.sandbox.core;

public class NewString {

    public static void main(String[] args) {
        String firstString = "ABC";
        String secondString = "ABC";
        System.out.println("same str: " + (firstString == secondString));

        firstString = "BCD";
        secondString = new String("BCD");
        System.out.println("new: " + (firstString == secondString)
                + " (str1.hashCode: " + firstString.hashCode() + "; str2.hashCode: " + secondString.hashCode() + ")");

        String partOfSecondString1 = "BC";
        String partOfSecondString2 = "D";
        secondString = partOfSecondString1 + partOfSecondString2; // probably it uses StringBuilder javap -c -verbose NewString
        System.out.println("str from two part: " + (firstString == secondString));

        System.out.println("AB + C == ABC: " + ("AB" + "C" == "ABC"));

        //docs.oracle.com/javase/specs/jls/se9/html/jls-15.html#jls-15.18.1
        final String partOfSecondString3 = "BC";
        final String partOfSecondString4 = "D";
        System.out.println("final + final: " + (firstString == partOfSecondString3 + partOfSecondString4));

        firstString = "DEF";
        secondString = new String("DEF").intern();
        System.out.println("intern: " + (firstString == secondString));

        firstString = new String("EFG");
        secondString = firstString.intern();
        System.out.println("intern2: " + (firstString == secondString));

        StringBuilder sb = new StringBuilder(secondString);
        System.out.println("sb-str: " + (secondString == sb.toString()));

        StringBuilder sb2 = new StringBuilder(secondString);
        System.out.println("sb-sb: " + sb.equals(sb2));

        System.out.println("hashCode: " + ("FB".hashCode() == "Ea".hashCode()));
    }
}
