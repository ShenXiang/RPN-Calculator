import com.rpncalc.Constant;
import com.rpncalc.operator.OperationFactory;
import com.rpncalc.operator.OperationFactoryImpl;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

class PrecisionTest {

    private OperationFactory operationFactory;

    public PrecisionTest() {
        operationFactory = new OperationFactoryImpl(new OperandStackImpl(), new Caretaker());
    }

    @Test
    void testAddPrecision() {
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
    }

    @Test
    void testSubPrecision() {

        Random r = new Random();

        BigDecimal[] params = {
                BigDecimal.valueOf(r.nextDouble()).setScale(
                        Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                        RoundingMode.HALF_UP),

                BigDecimal.valueOf(r.nextDouble()).setScale(
                        Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                        RoundingMode.HALF_UP)
        };

        BigDecimal result = operationFactory.create(SubOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());
    }

    @Test
    void testMulPrecision() {
        Random r = new Random();

        BigDecimal[] params = {
                BigDecimal.valueOf(r.nextDouble()).setScale(
                        Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                        RoundingMode.HALF_UP),

                BigDecimal.valueOf(r.nextDouble()).setScale(
                        Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                        RoundingMode.HALF_UP)
        };

        BigDecimal result = operationFactory.create(MulOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());
    }


    @Test
    void testDivPrecision() {
        Random r = new Random();

        BigDecimal[] params = {
                BigDecimal.valueOf(r.nextDouble()).setScale(
                        Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                        RoundingMode.HALF_UP),

                BigDecimal.valueOf(r.nextDouble()).setScale(
                        Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                        RoundingMode.HALF_UP)
        };
        BigDecimal result = operationFactory.create(DivOperation.TOKEN).calculate(params);
        assertTrue(result.scale() >= Constant.DECIMAL_STORE_PRECISION.getPrecision());
    }
}
