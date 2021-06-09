import com.rpncalc.Calculator;
import com.rpncalc.ExecuteResult;
import com.rpncalc.exception.ErrorCodeEnum;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {

    private static String randomSpaces() {
        Random r = new Random();
        int spaceCount = r.nextInt(100) + 1;
        StringBuilder builder = new StringBuilder(spaceCount);

        for (int i = 0; i < spaceCount; ++i) {
            builder.append(' ');
        }

        return builder.toString();
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
        assertEquals(ErrorCodeEnum.SUCCESS, result.getError());
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
