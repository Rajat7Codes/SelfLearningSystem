package co.in.nextgencoder.springblueprint.util;

import java.util.regex.Pattern;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class Validator {

    private final static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validateEmail(String email) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(email)
                .matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        try {
            Long.parseLong(phoneNumber);

            if (phoneNumber.length() != 10) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
