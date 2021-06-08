import com.rpncalc.ExecuteResult;
import com.rpncalc.RpnExpression;
import org.junit.jupiter.api.Test;

import static com.rpncalc.exception.ErrorCodeEnum.INSUFFICIENT_PARAMETERS;
import static com.rpncalc.exception.ErrorCodeEnum.INVALID_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExecuteResultTest {

    @Test
    void testNullExpression() {
        ExecuteResult executeResult = new ExecuteResult(null);
        assertEquals("", executeResult.getResult());
        assertEquals("", executeResult.getErrorMessage());
    }

    @Test
    void testInsufficientParametersExpression() {
        ExecuteResult executeResult = new ExecuteResult(new RpnExpression("1 2 3 * 5 + * * 6 5"));

        executeResult.setResult("11");
        executeResult.setError(INSUFFICIENT_PARAMETERS.getCode());
        executeResult.setToken("*", 7);
        assertEquals(
                "operator * (position: 15): insufficient parameters",
                executeResult.getErrorMessage());

        assertEquals("11", executeResult.getResult());
    }

    @Test
    void testInvalidTokenExpression() {
        ExecuteResult executeResult = new ExecuteResult(new RpnExpression(" 1 invalid"));

        executeResult.setResult("1");
        executeResult.setError(INVALID_TOKEN.getCode());
        executeResult.setToken("invalid", 1);
        assertEquals(
                "token invalid (position: 4): invalid token",
                executeResult.getErrorMessage());

        assertEquals("1", executeResult.getResult());
    }
}
