package Collections_Lesson_13.task_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private Map<String, String> phoneToLastName;
    private Map<String, Set<String>> lastNameToPhones;

    public Phonebook() {
        phoneToLastName = new HashMap<>();
        lastNameToPhones = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (phoneToLastName.containsKey(phoneNumber)) {
            String existingLastName = phoneToLastName.get(phoneNumber);
            if (!existingLastName.equals(lastName)) {
                return;
            }
        } else {
            phoneToLastName.put(phoneNumber, lastName);
        }

        lastNameToPhones.computeIfAbsent(lastName, k -> new HashSet<>()).add(phoneNumber);
    }

    public Set<String> get(String lastName) {
        return lastNameToPhones.getOrDefault(lastName, new HashSet<>());
    }
}