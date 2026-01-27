import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, UserData> users;
    private Gson gson = new Gson();
    private String dataFile = "users.json";

    public Bank() {
        loadUsers();
    }

    private void loadUsers() {
        try (Reader reader = new FileReader(dataFile)) {
            Type type = new TypeToken<Map<String, UserData>>() {}.getType();
            users = gson.fromJson(reader, type);
            if (users == null) users = new HashMap<>();
        } catch (IOException e) {
            users = new HashMap<>();
        }
    }

    private void saveUsers() {
        try (Writer writer = new FileWriter(dataFile)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).getPassword().equals(password);
        }
        return false;
    }

    public double checkBalance(String username) {
        return users.get(username).getBalance();
    }

    public boolean withdraw(String username, double amount) {
        UserData user = users.get(username);
        if (user.getBalance() >= amount && amount > 0) {
            user.setBalance(user.getBalance() - amount);
            saveUsers();
            return true;
        }
        return false;
    }

    public void deposit(String username, double amount) {
        UserData user = users.get(username);
        user.setBalance(user.getBalance() + amount);
        saveUsers();
    }

    public boolean transfer(String fromUser, String toUser, double amount) {
        if (!users.containsKey(toUser)) return false;
        if (withdraw(fromUser, amount)) {
            deposit(toUser, amount);
            return true;
        }
        return false;
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public double getTotalMoney() {
        return users.values().stream().mapToDouble(UserData::getBalance).sum();
    }
}