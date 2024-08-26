import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task_2 {

    private Map<String, List<String>> phonebook;

    public Task_2() {
        phonebook = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (!phonebook.containsKey(lastName)) {
            phonebook.put(lastName, new ArrayList<>());
        }
        phonebook.get(lastName).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return phonebook.getOrDefault(lastName, new ArrayList<>());
    }

    public static void main(String[] args) {
        Task_2 phonebook = new Task_2();

        phonebook.add("Ивановы", "8-900-678-32-12");
        phonebook.add("Петровы", "8-900-675-32-45");
        phonebook.add("Ивановы", "8-900-875-92-45");
        phonebook.add("Сидоровы", "8-900-785-92-25");

        System.out.println("Номера телефонов Ивановы: " + phonebook.get("Ивановы"));
        System.out.println("Номера телефонов Петровы: " + phonebook.get("Петровы"));
        System.out.println("Номера телефонов Сидоровы: " + phonebook.get("Сидоровы"));
    }
}