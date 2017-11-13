package com.epam.lab.payments.web.html.cosmetic;

/**
 * formatter for card numbers to represent them more human friendly
 */
public class CardNumberAdjuster {

    public static String valueOf16Digits(String value) {

        if (value.length() < 10) {
            return value;
        }

        StringBuilder builder = new StringBuilder();

        builder.append(value.substring(0, 4));
        builder.append(' ');
        builder.append(value.substring(4, 8));
        builder.append(' ');
        builder.append(value.substring(8));
        builder.append("00 0000");

        return builder.toString();
    }
}
