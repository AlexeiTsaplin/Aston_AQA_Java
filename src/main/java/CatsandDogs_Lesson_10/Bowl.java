package CatsandDogs_Lesson_10;

class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food < 0 ? 0 : food;
    }

    public int getFood() {
        return food;
    }

    public void decreaseFood(int amount) {
        if (food >= amount) {
            food -= amount;
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
        }
    }

    @Override
    public String toString() {
        return "В миске " + food + " грамм еды";
    }
}
