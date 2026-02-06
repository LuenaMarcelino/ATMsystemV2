import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.ByteArrayInputStream;

public class LoginManagerTest {

    @Test
    public void testCustomerLogin() {
        // Simulate typing: user1 then 1234
        String input = "user1\n1234\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ATMStatus atmStatus = new ATMStatus();
        Bank bank = new Bank(atmStatus);

        Role role = LoginManager.login(scanner, bank);

        assertEquals("Should return CUSTOMER role", Role.CUSTOMER, role);
    }

    @Test
    public void testTechnicianLogin() {
        // Simulate typing: admin then admin123
        String input = "admin\nadmin123\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ATMStatus atmStatus = new ATMStatus();
        Bank bank = new Bank(atmStatus);

        Role role = LoginManager.login(scanner, bank);

        assertEquals("Should return TECHNICIAN role", Role.TECHNICIAN, role);
    }

    @Test
    public void testInvalidLogin() {
        String input = "wrong\nwrong\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ATMStatus atmStatus = new ATMStatus();
        Bank bank = new Bank(atmStatus);

        Role role = LoginManager.login(scanner, bank);

        assertEquals("Should return NONE role", Role.NONE, role);
    }
}
