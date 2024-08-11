package CatsandDogs_Lesson_10;

class Animal {
    protected String name;
    protected int runDistance;
    protected int swimDistance;
    protected static int animalCount = 0;

    public Animal(String name, int runDistance, int swimDistance) {
        this.name = name;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        animalCount++;
    }

    public void run(int distance) {
        if (distance <= runDistance) {
            System.out.println(name + " пробежал " + distance + " м");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м");
        }
    }

    public void swim(int distance) {
        if (distance <= swimDistance) {
            System.out.println(name + " проплыл " + distance + " м");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м");
        }
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}
