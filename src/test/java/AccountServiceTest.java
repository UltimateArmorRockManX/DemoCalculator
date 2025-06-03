import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ThaiTan.AccountService;
import java.util.logging.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private final AccountService accountService = new AccountService();

    private static final Logger logger = Logger.getLogger(AccountServiceTest.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("UnitTest.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean result = accountService.registerAccount(username, password, email);
        assertEquals(expected, result);
        logger.info(String.format("Test result for %s - Expected: %b, Got: %b", username, expected, result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testIsValidPasswordFromCsv(String username, String password, String email) {
        boolean result = accountService.isValidPassword(password);
        boolean expected = password.length() >= 8;
        assertEquals(expected, result);
        logger.info(String.format("Password check for '%s' - Expected: %b, Got: %b", password, expected, result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testIsValidEmailFromCsv(String username, String password, String email) {
        boolean result = accountService.isValidEmail(email);
        boolean expected = email.contains("@") && email.contains(".");
        assertEquals(expected, result);
        logger.info(String.format("Email check for '%s' - Expected: %b, Got: %b", email, expected, result));
    }

    /*@ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testIsValidUsernameFromCsv(String username, String password, String email) {
        boolean result = accountService.isValidUsername(username);
        boolean expected = username != null && !username.isEmpty();
        assertEquals(expected, result);
        logger.info(String.format("Username check for '%s' - Expected: %b, Got: %b", username, expected, result));
    }*/
}
