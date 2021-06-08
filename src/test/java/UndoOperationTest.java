import com.rpncalc.Calculator;
import com.rpncalc.ExecuteResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndoOperationTest {
    @Test
    void testEmptyUndo() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute("undo undo");
        assertEquals("", result.getResult());
    }

    @Test
    void testContinuesUndo() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute("5 4 3 2 * undo undo undo");
        assertEquals("5 4", result.getResult());
    }

    @Test
    void testUndoAfterClear() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("5 4 3 2 1 clear undo");
        assertEquals("5 4 3 2 1", result.getResult());
    }

    @Test
    void testUndoAfterContinuesClear() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("5 4 3 2 1 clear clear undo");
        assertEquals("", result.getResult());

        result = calculator.execute("undo");
        assertEquals("5 4 3 2 1", result.getResult());
    }

    @Test
    void testUndoAfterAdd() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("4 2 +");
        assertEquals("6", result.getResult());

        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
    }

    @Test
    void testUndoAfterSub() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("4 2 -");
        assertEquals("2", result.getResult());

        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
    }

    @Test
    void testUndoAfterMul() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("4 2 *");
        assertEquals("8", result.getResult());

        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
    }

    @Test
    void testUndoAfterDiv() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("4 2 /");
        assertEquals("2", result.getResult());
        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
    }
}
