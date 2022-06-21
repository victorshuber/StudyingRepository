package test;

import MainProgramm.Calc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {


    protected String filename = "src/main/expression.txt";
    Calc calc = new Calc(filename);

    @Test
    void calculation() {

        assertNotNull("Please, enter the filename", filename);
        assertNotNull("The string is null!", String.valueOf(calc.expression));
        double actual = calc.answer;
        double expected = 0.0;
        assertEquals(expected, actual);
        System.out.println("CalcTest passed with succes!");
    }
}