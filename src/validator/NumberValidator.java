package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {
    static boolean validateNumber(String number) {
        Pattern pattern = Pattern.compile("\\+?(\\([0-9a-zA-Z]+\\)|[0-9a-zA-Z]+([ -][(][0-9a-zA-Z]{2,}[)])?)([ -][0-9a-zA-Z]{2,})*");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}