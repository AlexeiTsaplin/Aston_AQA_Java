package InterfacesGeometric_Lesson_10;

public class Main {
    public static void main(String[] args) {
        Figure circle = new Circle(10, "Синий", "Белый");
        Figure rectangle = new Rectangle(10, 10, "Красный", "Черный");
        Figure triangle = new Triangle(5, 5, 5, "Зеленый", "Желтый");

        printFigureChars(circle);
        printFigureChars(rectangle);
        printFigureChars(triangle);
    }

    public static void printFigureChars(Figure shape) {
        System.out.println("Площадь: " + shape.calculateArea());
        System.out.println("Периметр: " + shape.calculatePerimeter());
        System.out.println("Цвет заливки: " + shape.getFillColor());
        System.out.println("Цвет границы: " + shape.getBorderColor());
        System.out.println();
    }
}
