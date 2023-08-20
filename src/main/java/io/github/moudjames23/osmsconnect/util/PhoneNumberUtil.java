package io.github.moudjames23.osmsconnect.util;

import java.util.regex.Pattern;

public class PhoneNumberUtil {

    private PhoneNumberUtil() {
    }

    /**
     * this method allows you to check if phone number is correct
     * @param phoneNumber phonenumber to check
     * @return true ou false
     */
    public static boolean isValid(String phoneNumber)
    {
        String regex = "\\+?\\d{12,13}";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(phoneNumber).matches();
    }

    /**
     * This method checks if a phone number starts with +
     * otherwise it automatically adds it
     * @param phone phone number to check
     * @return correct phone number
     */
    public static String normalize(String phone) {
        return phone.startsWith("+") ? phone : "+" + phone;
    }
}
