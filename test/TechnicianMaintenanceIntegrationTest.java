import org.junit.Test;
import static org.junit.Assert.*;

public class TechnicianMaintenanceIntegrationTest {

    @Test
    public void testRefillBanknotesIncreasesTotal() {
        ATMStatus atmStatus = new ATMStatus();
        double initialCash = atmStatus.getTotalCash();

        // Refill 10 x €50 notes
        atmStatus.refillBanknotes(50, 10);

        double expectedCash = initialCash + 500.0;
        assertEquals("Cash should increase by 500",
                expectedCash,
                atmStatus.getTotalCash(),
                0.01);
    }

    @Test
    public void testRefillInkEnablesReceipt() {
        ATMStatus atmStatus = new ATMStatus();

        // Drain all ink
        while (atmStatus.getInkLevel() > 0) {
            atmStatus.useInkAndPaper();
        }

        assertFalse("Should have no ink", atmStatus.hasInkAndPaper());

        // Refill ink
        atmStatus.refillInk(10);
        atmStatus.refillPaper(10);

        assertTrue("Should have ink and paper now", atmStatus.hasInkAndPaper());
    }
}