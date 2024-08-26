package Collections_Lesson_13.Task_1;

public class Main {
    public static void main(String[] args) {
        WordsMassive wordsMassive = new WordsMassive();
        wordsMassive.addWords(new String[]{
                "Кошка", "Собака", "Кошка", "Курица",
                "Овца", "Кошка", "Коза", "Свинья",
                "Корова", "Корова", "Кошка", "Крыса",
                "Овца", "Собака"
        });

        wordsMassive.printUniqueWords();
        wordsMassive.printWordCounts();
    }
}
