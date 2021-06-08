import com.rpncalc.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearOperationTest {

    @Test
    void testClearNumbers() {
        Calculator calculator = new Calculator();
        assertEquals("1 2 3", calculator.execute("1 2 3").getResult());
        assertEquals("", calculator.execute("clear").getResult());
    }

    @Test
    void testClearEmptyStack() {
        Calculator calculator = new Calculator();
        assertEquals("", calculator.execute("clear").getResult());
    }

    @Test
    void testContinuesClear() {
        Calculator calculator = new Calculator();
        assertEquals("", calculator.execute("clear clear").getResult());
    }

    @Test
    void testClearAfterUndo() {
        Calculator calculator = new Calculator();
        assertEquals("", calculator.execute("undo clear").getResult());
    }
}
