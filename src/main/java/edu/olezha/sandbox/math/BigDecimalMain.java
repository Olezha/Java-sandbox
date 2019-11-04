package edu.olezha.sandbox.math;

import java.math.BigDecimal;

public class BigDecimalMain {

    public static void main(String[] args) {
        int priceKop = 9999;
        BigDecimal price = BigDecimal.valueOf(priceKop, 2);

        System.out.println(price);

        System.out.println(price.movePointRight(2).intValue());

        System.out.println(new BigDecimal(cleanNumbersString("-abc1,23..456.789")));
    }

    private static String cleanNumbersString(String s) {
        int lastDotPosition = s.lastIndexOf('.'), lastCommaPosition = s.lastIndexOf(',');
        int decimalSeparatorPosition = lastDotPosition > lastCommaPosition ? lastDotPosition : lastCommaPosition;
        StringBuilder cleanNumbersString = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++)
            if (i == decimalSeparatorPosition || Character.isDigit(s.charAt(i)) || (i == 0 && s.charAt(i) == '-'))
                cleanNumbersString.append(s.charAt(i));
        return cleanNumbersString.toString();
    }
}
