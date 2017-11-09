/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Created: Nov 7th 2017
 */

package app.tweeting.helpers;

import java.util.regex.Pattern;

/**
 * ValidateHelper is used in the signup / login classes
 * It is used to validate user email
 * It is used to validate the user name (only letters to be used)
 * It is used to validate that the input is valid and not empty
 */

// https://stackoverflow.com/questions/22505336/email-and-phone-number-validation-in-android
// https://stackoverflow.com/questions/35933361/android-studio-regular-expression

public class ValidateHelper {


    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isValidName(String name) {
        return Pattern.compile("[A-Za-z]+").matcher(name).matches();
    }


    public static boolean isValidInput(String string) {
        return !string.isEmpty();
    }
}
