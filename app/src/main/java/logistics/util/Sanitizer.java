package logistics.util;

import java.util.regex.Pattern;

public class Sanitizer {

    private static Pattern patternString;
    private static Pattern patternInteger;

    public Sanitizer() {
    }

    public static boolean verify(String _input) {

        String text = _input;

        patternString = Pattern.compile("^[A-Za-z, ]++$");  // para validar apenas strings
        patternInteger = Pattern.compile("\\d+");           // para validar apenas inteiros

        if (!patternString.matcher(text.trim()).matches() || text.isEmpty()) {
            return false;
        }

        if (!patternInteger.matcher(text).matches() || text.isEmpty()) {
            return false;
        }

        int number = Integer.parseInt(text);
        if (number > 100 || number < 0) {
            return false;
        }

        return true;
    }

}