package Collections_Lesson_13.Task_1;

import java.util.*;

public class WordsMassive {

    private Map<String, Integer> wordCounts;

    public WordsMassive() {
        wordCounts = new HashMap<>();
    }

    public void addWords(String[] words) {
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
    }

    public void printUniqueWords() {
        Set<String> uniqueWords = new HashSet<>(wordCounts.keySet());
        System.out.println("Уникальные слова: " + uniqueWords);
    }

    public void printWordCounts() {
        System.out.println("Каждое слово встречается:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
