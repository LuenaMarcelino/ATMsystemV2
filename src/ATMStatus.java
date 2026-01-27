import com.google.gson.Gson;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ATMStatus {

    private double cashAvailable;
    private int paperAvailable;
    private String softwareVersion;

    private static final String FILE_NAME = "atm_status.json";
    private Gson gson = new Gson();

    public ATMStatus() {
        load();
    }

    private void load() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            // Read as Map to avoid reflection issues
            Map<String, Object> data = gson.fromJson(reader, Map.class);
            this.cashAvailable = ((Number) data.get("cashAvailable")).doubleValue();
            this.paperAvailable = ((Number) data.get("paperAvailable")).intValue();
            this.softwareVersion = (String) data.get("softwareVersion");
        } catch (Exception e) {
            // Default values
            cashAvailable = 20000;
            paperAvailable = 100;
            softwareVersion = "1.0";
            save();
        }
    }

    private void save() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Map<String, Object> data = new HashMap<>();
            data.put("cashAvailable", cashAvailable);
            data.put("paperAvailable", paperAvailable);
            data.put("softwareVersion", softwareVersion);
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStatus() {
        System.out.println("\n--- ATM STATUS ---");
        System.out.println("Cash available: " + cashAvailable);
        System.out.println("Paper available: " + paperAvailable);
        System.out.println("Software version: " + softwareVersion);
    }

    public void addCash(double amount) {
        cashAvailable += amount;
        save();
        System.out.println("Cash replenished successfully.");
    }

    public void addPaper(int amount) {
        paperAvailable += amount;
        save();
        System.out.println("Paper replenished successfully.");
    }

    public void upgradeSoftware() {
        softwareVersion = softwareVersion + ".1";
        save();
        System.out.println("Software upgraded to version " + softwareVersion);
    }
}