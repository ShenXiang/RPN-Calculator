package com.rpncalc.stack;

import com.rpncalc.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Now OperandValue only contains value property.
 * However, this class is designed to extend the behaviors.
 *
 * You can implement your own behavior like customizing the colour of the value, etc
 *
 * @author Xiang Shen
 */
@AllArgsConstructor
@Getter
public class OperandValue {

    private BigDecimal value;

    @Override
    public String toString() {

        if (Objects.nonNull(value)) {
            BigDecimal displayValue = value.add(BigDecimal.ZERO).setScale(
                    Constant.DECIMAL_DISPLAY_PRECISION.getPrecision(), RoundingMode.DOWN);

            return displayValue.stripTrailingZeros().toPlainString();
        }

        return "";
    }
}
