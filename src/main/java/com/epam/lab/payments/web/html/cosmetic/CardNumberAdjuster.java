package com.epam.lab.payments.web.html.cosmetic;

/**
 * Formatter for card numbers to represent them more human friendly.
 */
public class CardNumberAdjuster {

    private CardNumberAdjuster() {}

    /**
     * Transforms '1234567890' into '1234 5678 9000 0000'
     * @param value - account id from database
     * @return a human friendly representation of value
     */
    public static String valueOf16Digits(String value) {

        if (value.length() < 10) {
            return value;
        }

        return new StringBuilder()
                .append(value.substring(0, 4))
                .append(' ')
                .append(value.substring(4, 8))
                .append(' ')
                .append(value.substring(8))
                .append("00 0000")
                .toString();
    }
}
