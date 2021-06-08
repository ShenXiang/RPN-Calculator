import com.rpncalc.Calculator;
import com.rpncalc.Constant;
import com.rpncalc.ExecuteResult;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.OperationFactory;
import com.rpncalc.operator.math.AddOperation;
import com.rpncalc.operator.math.DivOperation;
import com.rpncalc.operator.math.MulOperation;
import com.rpncalc.operator.math.SubOperation;
import com.rpncalc.snapshot.Caretaker;
import com.rpncalc.stack.OperandStackImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {

    private OperationFactory operationFactory;

    public CalculatorTest() {
        operationFactory = new OperationFactory(new OperandStackImpl(), new Caretaker());
    }

    private static String randomSpaces() {
        Random r = new Random();
        int spaceCount = r.nextInt(100);
        StringBuilder builder = new StringBuilder(spaceCount);

        for (int i = 0; i < spaceCount; ++i) {
            builder.append(' ');
        }

        return builder.toString();
    }

    @Test
    void testPrecision() {
        Random r = new Random();

        BigDecimal[] params = {
            BigDecimal.valueOf(r.nextDouble()).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP),

            BigDecimal.valueOf(r.nextDouble()).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP)
        };

        BigDecimal result = operationFactory.create(AddOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = operationFactory.create(SubOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = operationFactory.create(MulOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = operationFactory.create(DivOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());
    }

    @Test
    void testInvalidToken() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("2 3 invalid invalid2");

        // Through "invalid" token is broken, it will affect those already parsed tokens.
        assertEquals("2 3", result.getResult());
        assertEquals(ErrorCodeEnum.INVALID_TOKEN, result.getError());
        assertEquals("token invalid (position: 5): invalid token", result.getErrorMessage());

        calculator.execute("clear");
        result = calculator.execute("2 3+");

        // Through "invalid" token is broken, it will affect those already parsed tokens.
        assertEquals("2", result.getResult());
        assertEquals(ErrorCodeEnum.INVALID_TOKEN, result.getError());
        assertEquals("token 3+ (position: 3): invalid token", result.getErrorMessage());
    }

    @Test
    void testInsufficientParameters() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("5 +");

        assertEquals(ErrorCodeEnum.INSUFFICIENT_PARAMETERS, result.getError());
        assertEquals("operator + (position: 3): insufficient parameters", result.getErrorMessage());
    }

    @Test
    void testClearEmptyStack() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute("clear");
        assertTrue(result.getResult().isEmpty());
    }

    @Test
    void testRpnWithMultiSpaces() {
        Calculator calculator = new Calculator();

        String spaces = randomSpaces();
        String input = "2" + spaces + "3" + spaces + "*";

        ExecuteResult result = calculator.execute(input);
        assertEquals("6", result.getResult());
    }

    @Test
    void testNullRpn() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute(null);
        assertTrue(result.isSuccess());
    }

    @Test
    void testBlankRpn() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("");
        assertTrue(result.getResult().isEmpty());

        result = calculator.execute(randomSpaces());
        assertTrue(result.getResult().isEmpty());
    }
}
