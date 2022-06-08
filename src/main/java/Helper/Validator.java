package Helper;

public class Validator {

    public static boolean isValidCVVNumber(String CvvNum) {
        for (Character ch : CvvNum.toCharArray()) {
            if(!Character.isDigit(ch)) return false;
        }
        return  isValidLength(CvvNum, 3) ||
                isValidLength(CvvNum, 4) &&
                !isEmpty(CvvNum);
    }

    public static boolean isValidCardNumber(String cardNumber) {
        for (Character ch : cardNumber.toCharArray()) {
            if(!Character.isDigit(ch)) return false;
        }
        return  cardNumber.length() >= 13 &&
                cardNumber.length() <= 16 &&
                cardNumber.startsWith("4") ||
                cardNumber.startsWith("5") &&
                !isEmpty(cardNumber);
    }

    public static boolean isValidPIN(String PIN) {
        return PIN.length() == 4 && !isEmpty(PIN);
    }

    public static boolean isValidTip(String tip) {
        double t = Double.parseDouble(tip);
        return (t > 0);
    }

    private static boolean isValidLength(String value, int length) {
        return value.length() == length;
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
