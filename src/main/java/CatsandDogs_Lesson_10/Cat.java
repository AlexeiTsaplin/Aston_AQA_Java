package CatsandDogs_Lesson_10;

class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name, 200, 0);
        catCount++;
        this.isFull = false;
    }

    public void eat(Bowl bowl, int foodAmount) {
        if (bowl.getFood() >= foodAmount) {
            bowl.decreaseFood(foodAmount);
            this.isFull = true;
            System.out.println(name + " покушал " + foodAmount + " грамм еды");
        } else {
            System.out.println(name + " не хватает еды в миске");
        }
    }

    public boolean getIsFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}
