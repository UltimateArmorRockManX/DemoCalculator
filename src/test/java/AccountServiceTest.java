import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ThaiTan.AccountService;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private final AccountService accountService = new AccountService();

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
    }

    @Test
    void testIsValidPassword() {
        assertTrue(accountService.isValidPassword("password123"));
        assertFalse(accountService.isValidPassword("short"));
    }

    @Test
    void testIsValidEmail() {
        assertTrue(accountService.isValidEmail("valid@example.com"));
        assertFalse(accountService.isValidEmail("invalid-email"));
    }

    @Test
    void testIsValidUsername() {
        assertTrue(accountService.registerAccount("validUser", "password123", "valid@example.com"));
        assertFalse(accountService.registerAccount("", "password123", "valid@example.com"));
    }
}
