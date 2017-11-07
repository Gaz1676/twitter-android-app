/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Created: Nov 7th 2017
 */

package app.tweeting.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {

    // https://stackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        String emailRegex = "[A-Za-z]+";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidInput(String string) {
        return !string.isEmpty();
    }
}
