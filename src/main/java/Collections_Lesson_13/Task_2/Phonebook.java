package Collections_Lesson_13.Task_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {
    private Map<String, List<String>> phonebook;

    public Phonebook() {
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
}