package ThaiTan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService {

    public boolean registerAccount(String username, String password, String email) {
        return isValidUsername(username) && isValidPassword(password) && isValidEmail(email);
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() > 6;
    }

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidUsername(String username) {
        return username != null && !username.isEmpty();
    }
}
