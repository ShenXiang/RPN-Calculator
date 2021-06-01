import com.rpncalc.Calculator;
import com.rpncalc.ExecuteResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FunctionOperatorTest {

    @Test
    public void testClear() {
        Calculator calculator = new Calculator();
        assertEquals("1 2 3", calculator.execute("1 2 3").getResult());
        assertEquals("", calculator.execute("clear").getResult());
    }

    @Test
    public void testEmptyClear() {
        Calculator calculator = new Calculator();
        assertEquals("", calculator.execute("clear").getResult());
    }

    @Test
    public void testUndoOperator() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("4 2 +");
        assertEquals("6", result.getResult());

        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
        calculator.execute("clear");

        result = calculator.execute("4 2 -");
        assertEquals("2", result.getResult());

        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
        calculator.execute("clear");

        result = calculator.execute("4 2 *");
        assertEquals("8", result.getResult());

        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());

        calculator.execute("clear");

        result = calculator.execute("4 2 /");
        assertEquals("2", result.getResult());
        result = calculator.execute("undo");
        assertEquals("4 2", result.getResult());
    }

    @Test
    public void testUndoValue() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute("5 4 3 2 undo undo undo");
        assertEquals("5", result.getResult());
    }

    @Test
    public void testContinuesUndo() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute("5 4 3 2 * undo undo undo");
        assertEquals("5 4", result.getResult());
    }

    @Test
    public void testUndoClear() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("5 4 3 2 1 clear undo");
        assertEquals("5 4 3 2 1", result.getResult());
        calculator.execute("clear");

        result = calculator.execute("5 4 3 2 1 clear clear undo");
        assertTrue(result.getResult().isEmpty());

        result = calculator.execute("undo");
        assertEquals("5 4 3 2 1", result.getResult());
    }
}
