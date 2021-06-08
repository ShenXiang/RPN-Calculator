import com.rpncalc.Constant;
import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.Operation;
import com.rpncalc.operator.OperationFactory;
import com.rpncalc.operator.math.AddOperation;
import com.rpncalc.operator.math.DivOperation;
import com.rpncalc.operator.math.MulOperation;
import com.rpncalc.operator.math.SqrtOperation;
import com.rpncalc.operator.math.SubOperation;
import com.rpncalc.snapshot.Caretaker;
import com.rpncalc.stack.OperandStackImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathOperationTest {

    private OperationFactory operationFactory;

    public MathOperationTest() {
        operationFactory = new OperationFactory(new OperandStackImpl(), new Caretaker());
    }

    @Test
    void testUnaryOperation() throws CalculatorException {
        Random r = new Random();
        BigDecimal[] params = {
                BigDecimal.valueOf(r.nextDouble())
        };

        Operation sqtOperation = operationFactory.create(SqrtOperation.TOKEN);
        assertEquals(params[0].sqrt(Constant.DECIMAL_STORE_PRECISION), sqtOperation.calculate(params));

        params[0] = BigDecimal.ZERO;
        assertEquals(BigDecimal.ZERO, sqtOperation.calculate(params));
    }

    @Test
    void testBinaryOperation() throws CalculatorException {
        Random r = new Random();

        BigDecimal[] params = {
                BigDecimal.valueOf(r.nextDouble()),
                BigDecimal.valueOf(r.nextDouble())
        };

        assertEquals(params[0].add(params[1]),
                operationFactory.create(AddOperation.TOKEN).calculate(params));

        assertEquals(params[0].subtract(params[1]),
                operationFactory.create(SubOperation.TOKEN).calculate(params));

        assertEquals(params[0].multiply(params[1]),
                operationFactory.create(MulOperation.TOKEN).calculate(params));

        assertEquals(params[0].divide(params[1], Constant.DECIMAL_STORE_PRECISION).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP),
                operationFactory.create(DivOperation.TOKEN).calculate(params));
    }

    @Test
    void testDivZero() {

        BigDecimal[] params = {
                BigDecimal.ONE, BigDecimal.ZERO
        };

        CalculatorException exception = assertThrows(
                CalculatorException.class,
                () -> operationFactory.create(DivOperation.TOKEN).calculate(params));
        assertEquals(exception.getCode(), ErrorCodeEnum.DIVISION_BY_ZERO.getCode());
    }

    @Test
    void testSqrtNegative() {

        BigDecimal[] params = {
                BigDecimal.ONE.negate()
        };

        CalculatorException exception = assertThrows(
                CalculatorException.class,
                () -> operationFactory.create(SqrtOperation.TOKEN).calculate(params));

        assertEquals(exception.getCode(), ErrorCodeEnum.NEGATIVE_SQUARE_ROOT.getCode());
    }

    @Test
    void testSqrtInfiniteDecimal() throws CalculatorException {

        BigDecimal[] params = {
                BigDecimal.ONE, new BigDecimal("3")
        };

        assertEquals(
                params[0].divide(params[1], Constant.DECIMAL_STORE_PRECISION),
                operationFactory.create(DivOperation.TOKEN).calculate(params));
    }
}
