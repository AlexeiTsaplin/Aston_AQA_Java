package Exception_Lesson_12;

public class ExceptionMethods {
    public static int sum = 0;

    public static void checkArraySize(String[][] array) throws MyArraySizeException {
        if (array.length != 4) {
            throw new MyArraySizeException("Неверный размер массива! Ожидается массив 4x4.");
        }

        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException("Неверный размер массива! Ожидается массив 4x4.");
            }
        }
    }

    public static int sumArray(String[][] array) throws MyArrayDataException {
        for (String[] strings : array) {
            for (String string : strings) {
                try {
                    sum += Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Суммирование невозможно. Некорректный символ: " + string);
                }
            }
        }
        return sum;
    }

    public static void validateArray(String[][] array) {
        try {
            checkArraySize(array);
            sum = sumArray(array);
            System.out.println("Размер массива корректный. Сумма элементов массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}