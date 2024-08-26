package Collections_Lesson_13;

import java.util.*;

public class Task_1 {
    private List<String> words;

    public Task_1() {
        words = new ArrayList<>();
    }

    public void addWord(String word) {
        words.add(word);
    }

    public Set<String> getUniqueWords() {
        return new HashSet<>(words);
    }

    public Map<String, Integer> countWords() {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }

    public static void main(String[] args) {
        Task_1 wordCounter = new Task_1();

        wordCounter.addWord("Собака");
        wordCounter.addWord("Кошка");
        wordCounter.addWord("Слон");
        wordCounter.addWord("Корова");
        wordCounter.addWord("Кошка");
        wordCounter.addWord("Коза");
        wordCounter.addWord("Собака");
        wordCounter.addWord("Обезьяна");
        wordCounter.addWord("Слон");
        wordCounter.addWord("Кошка");
        wordCounter.addWord("Свинья");
        wordCounter.addWord("Свинья");
        wordCounter.addWord("Обезьяна");
        wordCounter.addWord("Коза");

        Set<String> uniqueWords = wordCounter.getUniqueWords();
        System.out.println("Уникальные слова: " + uniqueWords);

        Map<String, Integer> wordCounts = wordCounter.countWords();
        System.out.println("Количество слов:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
