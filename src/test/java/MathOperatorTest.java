import com.rpncalc.Constant;
import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.math.AdditionOperator;
import com.rpncalc.operator.math.DivisionOperator;
import com.rpncalc.operator.math.MultiplicationOperator;
import com.rpncalc.operator.math.SqrtOperator;
import com.rpncalc.operator.math.SubtractionOperator;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MathOperatorTest {

    @Test
    public void testUnaryOperator() throws BusinessException {
        Random r = new Random();
        BigDecimal input = BigDecimal.valueOf(r.nextDouble());

        double expectedDouble = Math.sqrt(input.doubleValue());
        BigDecimal expectedVal = new BigDecimal(expectedDouble).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP
        );

        assertEquals(expectedVal, new SqrtOperator().execute(input));
        assertEquals(BigDecimal.ZERO, new SqrtOperator().execute(BigDecimal.ZERO));
    }

    @Test
    public void testBinaryOperator() throws BusinessException {
        Random r = new Random();

        BigDecimal left = BigDecimal.valueOf(r.nextDouble());

        BigDecimal right = BigDecimal.valueOf(r.nextDouble());

        assertEquals(left.add(right).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP),
                new AdditionOperator().execute(left, right));

        assertEquals(left.subtract(right).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP),
                new SubtractionOperator().execute(left, right));

        assertEquals(left.multiply(right).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP),
                new MultiplicationOperator().execute(left, right));

        assertEquals(left.divide(right, Constant.DECIMAL_STORE_PRECISION).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP),
                new DivisionOperator().execute(left, right));
    }

    @Test
    public void testDivideByZero() {

        BigDecimal left = BigDecimal.ONE;
        BigDecimal right = BigDecimal.ZERO;
        try {
            new DivisionOperator().execute(left, right);
            fail("Expected an BusinessException to be thrown");
        } catch (BusinessException e) {
            assertEquals(e.getCode(), ErrorCodeEnum.DIVISION_BY_ZERO.getCode());
        }
    }

    @Test
    public void testSqrtRootNegative() {

        try {
            new SqrtOperator().execute(BigDecimal.ONE.negate());
            fail("Expected an BusinessException to be thrown");
        } catch (BusinessException e) {
            assertEquals(e.getCode(), ErrorCodeEnum.NEGATIVE_SQUARE_ROOT.getCode());
        }
    }

    @Test
    public void testNoExactRepresentableSqrt() throws BusinessException {

        BigDecimal left = BigDecimal.ONE;
        BigDecimal right = new BigDecimal("3");

        assertEquals(
                left.divide(right, Constant.DECIMAL_STORE_PRECISION),
                new DivisionOperator().execute(left, right));
    }
}
