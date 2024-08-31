package FactorialTestNG;

import Factorial_Test_Lesson_14.Factorial;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    private final Factorial factorial = new Factorial();

    @Test
    public void testCalculateFactorial() throws IllegalArgumentException {
        long result = factorial.calculateFactorial(3);
        assertEquals(result, 6, "Факториал 3 равен 6");
    }

    @Test
    public void testCalculateFactorialNegative() throws IllegalArgumentException {
        long result = factorial.calculateFactorial(3);
        assertFalse(result != 6);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Число не должно быть отрицательным.")
    public void testCalculateFactorialNegativeNumber() {
        factorial.calculateFactorial(-1);
    }
}
