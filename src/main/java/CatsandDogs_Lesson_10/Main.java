package CatsandDogs_Lesson_10;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Василий");
        Cat cat2 = new Cat("Степан");
        Cat cat3 = new Cat("Евпатий");
        Cat cat4 = new Cat("Лукьян");
        Cat cat5 = new Cat("Роберто");

        Cat[] cats = {cat1, cat2, cat3, cat4, cat5};

        Bowl bowl = new Bowl(50);

        for (Cat cat : cats) {
            cat.eat(bowl, 15);
        }

        for (Cat cat : cats) {
            System.out.println(cat.name + (cat.getIsFull() ? " наелся и спит" : " голоден и мяукает"));
        }

        System.out.println("Количество животных: " + Animal.getAnimalCount());
        System.out.println("Количество котов: " + Cat.getCatCount());
        System.out.println("Количество собак: " + Dog.getDogCount());
    }
}
