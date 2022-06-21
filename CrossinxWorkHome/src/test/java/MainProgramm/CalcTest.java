package MainProgramm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {




    @Test
    void calculation() {

      Calc calcTest = new Calc("src/main/expression.txt");

      assertNotNull("The string is null!", String.valueOf(calcTest.expression));
      double actual = calcTest.answer;
      double expected = 0.0;
      assertEquals(expected, actual);

    }
}