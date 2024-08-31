package Collections_Lesson_13.Task_2;

public class Main {
    public static void main(String[] args) {
        Phonebook phoneBook = new Phonebook();

        phoneBook.add("Ивановы", "123-456-789");
        phoneBook.add("Петровы", "987-654-321");
        phoneBook.add("Ивановы", "234-567-890");
        phoneBook.add("Сидоровы", "456-789-123");

        System.out.println("Ивановы: " + phoneBook.get("Ивановы"));
        System.out.println("Петровы: " + phoneBook.get("Петровы"));
        System.out.println("Сидоровы: " + phoneBook.get("Сидоровы"));
        System.out.println("Кузнецовы: " + phoneBook.get("Кузнецовы"));
    }
}
