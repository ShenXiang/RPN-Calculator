import com.rpncalc.Calculator;
import com.rpncalc.Constant;
import com.rpncalc.ExecuteResult;
import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.math.AdditionOperator;
import com.rpncalc.operator.math.DivisionOperator;
import com.rpncalc.operator.math.MultiplicationOperator;
import com.rpncalc.operator.math.SqrtOperator;
import com.rpncalc.operator.math.SubtractionOperator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {

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
    public void testPrecision() throws BusinessException {
        Random r = new Random();

        BigDecimal left = BigDecimal.valueOf(r.nextDouble());
        BigDecimal right = BigDecimal.valueOf(r.nextDouble());

        BigDecimal result = new AdditionOperator().execute(left, right);
        assertEquals(result.scale(), Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = new SubtractionOperator().execute(left, right);
        assertEquals(result.scale(), Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = new MultiplicationOperator().execute(left, right);
        assertEquals(result.scale(), Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = new DivisionOperator().execute(left, right);
        assertEquals(result.scale(), Constant.DECIMAL_STORE_PRECISION.getPrecision());

        result = new SqrtOperator().execute(left);
        assertEquals(result.scale(), Constant.DECIMAL_STORE_PRECISION.getPrecision());
    }

    @Test
    public void testInvalidToken() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("2 3 invalid invalid2");

        // Through "invalid" token is broken, it will affect those already parsed tokens.
        assertEquals("2 3", result.getResult());
        assertEquals(ErrorCodeEnum.INVALID_TOKEN.getCode(), result.getErrorCode());
        assertEquals("token invalid (position: 3): invalid token", result.getErrorMsg());
    }

    @Test
    public void testInsufficientParameters() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("5 +");

        assertEquals(ErrorCodeEnum.INSUFFICIENT_PARAMETERS.getCode(), result.getErrorCode());
        assertEquals("operator + (position: 2): insufficient parameters", result.getErrorMsg());
    }

    @Test
    public void testClearEmpty() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute("clear");
        assertTrue(result.getResult().isEmpty());
    }

    @Test
    public void testMultipleSpaces() {
        Calculator calculator = new Calculator();

        String spaces = randomSpaces();
        String input = "2" + spaces + "3" + spaces + "*";

        ExecuteResult result = calculator.execute(input);
        assertEquals("6", result.getResult());
    }

    @Test
    public void testNoSpaces() throws BusinessException {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("2 3+");

        // Through "invalid" token is broken, it will affect those already parsed tokens.
        assertEquals("2", result.getResult());
        assertEquals(ErrorCodeEnum.INVALID_TOKEN.getCode(), result.getErrorCode());
        assertEquals("token 3+ (position: 2): invalid token", result.getErrorMsg());
    }

    @Test
    public void testNullInput() {
        Calculator calculator = new Calculator();
        ExecuteResult result = calculator.execute(null);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testEmptyInput() {
        Calculator calculator = new Calculator();

        ExecuteResult result = calculator.execute("");
        assertTrue(result.getResult().isEmpty());

        result = calculator.execute(randomSpaces());
        assertTrue(result.getResult().isEmpty());
    }

}
