package FactorialTest;

import Factorial_Test_Lesson_14.Factorial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    private final Factorial factorial = new Factorial();

    @Test
    @DisplayName("Программа верно высчитывает факториал числа")
    void testCalculateFactorialPositive() throws IllegalArgumentException {
        long result = factorial.calculateFactorial(3);
        assertEquals(6, result, "Факториал 3 равен 6");
    }

    @Test
    @DisplayName("Программа не выполняется с неверным результатом")
    void testCalculateFactorialNegative() {
        long result = factorial.calculateFactorial(3);
        assertFalse(result != 6);
    }

    @Test
    @DisplayName("Число не должно быть отрицательным")
    void testCalculateFactorialNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factorial.calculateFactorial(-1);
        });

        assertEquals("Число не должно быть отрицательным.", exception.getMessage());
    }
}
