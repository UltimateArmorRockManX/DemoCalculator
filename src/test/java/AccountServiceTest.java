import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @CsvSource({
            "john123,pass123,john@example.com,true",
            " ,pass123,john@example.com,false",
            "alice,short,alice@mail.com,false",
            "bob123,password,bobmail.com,false",
            "carol,password,carol@domain.com,true"
    })
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean result = accountService.registerAccount(username, password, email);
        assertEquals(expected, result);
        logger.info(String.format("Test result for %s - Expected: %b, Got: %b", username, expected, result));
    }

    @Test
    void testIsValidPassword() {
        boolean validPassword = accountService.isValidPassword("password123");
        boolean invalidPassword = accountService.isValidPassword("short");
        assertTrue(validPassword);
        assertFalse(invalidPassword);
        logger.info("Password 'password123' valid: " + validPassword);
        logger.info("Password 'short' valid: " + invalidPassword);
    }

    @Test
    void testIsValidEmail() {
        boolean validEmail = accountService.isValidEmail("valid@example.com");
        boolean invalidEmail = accountService.isValidEmail("invalid-email");
        assertTrue(validEmail);
        assertFalse(invalidEmail);
        logger.info("Email 'valid@example.com' valid: " + validEmail);
        logger.info("Email 'invalid-email' valid: " + invalidEmail);
    }

    @Test
    void testIsValidUsername() {
        boolean validUsername = accountService.registerAccount("validUser", "password123", "valid@example.com");
        boolean invalidUsername = accountService.registerAccount("", "password123", "valid@example.com");
        assertTrue(validUsername);
        assertFalse(invalidUsername);
        logger.info("Username 'validUser' valid: " + validUsername);
        logger.info("Username '' (empty) valid: " + invalidUsername);
    }
}
