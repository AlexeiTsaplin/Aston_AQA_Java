package org.exemple;

import java.util.Arrays;

public class Main {
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = -5;
        int b = 5;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 45;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 100) {
            System.out.println("Зеленый");
        } else {
            System.out.println("Желтый");
        }
    }

    public static void compareNumbers() {
        int a = -5;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean sumOfTwo(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    public static void numberSign(int num) {
        if (num >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean altNumberSign(int number) {
        return number >= 0;
    }

    public static void printString(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    public static boolean leapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }


    public static void zerOneTOneZero() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void oneToHundred() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void underSixOnTwo() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void squareMassive() {
        int square = 9;
        int[][] arr = new int[9][9];
        for (int i = 0; i < square; i++) {
            arr[i][i] = 7;
            arr[i][8 - i] = 7;
        }
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
    }

    public static void massiveWithInitialValue() {
        int len = 4;
        int initialValue = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String args[]) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(sumOfTwo(5, 5));
        numberSign(-15);
        System.out.println(altNumberSign(22));
        printString("How do you do fellow kids?", 3);
        System.out.println(leapYear(500));
        zerOneTOneZero();
        oneToHundred();
        underSixOnTwo();
        squareMassive();
        massiveWithInitialValue();
    }
}


