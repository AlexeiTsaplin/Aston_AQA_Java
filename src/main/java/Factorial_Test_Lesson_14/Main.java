package Factorial_Test_Lesson_14;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factorial factorial = new Factorial();

        System.out.print("Введите целое число: ");

        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            try {
                long result = factorial.calculateFactorial(number);
                System.out.println("Факториал числа " + number + " равен " + result);
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        } else {
            System.err.println("Программа работает только с положительными целыми числами.");
        }

        scanner.close();
    }
}
