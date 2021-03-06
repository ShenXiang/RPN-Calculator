import com.rpncalc.RpnExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RpnExpressionTest {

    @Test
    void testSingleToken() {
        RpnExpression expression = new RpnExpression("single");
        assertEquals(1, expression.getTokens().length);
        assertEquals("single", expression.getTokens()[0]);
    }

    @Test
    void testMultiTokens() {
        RpnExpression expression = new RpnExpression("multiple tokens");
        String[] tokens = expression.getTokens();

        assertEquals(2, expression.getTokens().length);
        assertEquals("multiple", tokens[0]);
        assertEquals("tokens", tokens[1]);
    }

    @Test
    void testBlankTokens() {
        RpnExpression expression = new RpnExpression("multiple tokens");
        String[] tokens = expression.getTokens();

        assertEquals(2, expression.getTokens().length);
        assertEquals("multiple", tokens[0]);
        assertEquals("tokens", tokens[1]);
    }

    @Test
    void testNullExpression() {
        RpnExpression expression = new RpnExpression(null);
        assertEquals(0, expression.getTokens().length);
    }

    @Test
    void testBlankRpn() {
        RpnExpression expression = new RpnExpression("");
        assertEquals(0, expression.getTokens().length);

        expression = new RpnExpression("  ");
        assertEquals(0, expression.getTokens().length);
    }

    @Test
    void testRpnMoreWhitespaces() {
        RpnExpression expression = new RpnExpression(" multiple   tokens ");
        String[] tokens = expression.getTokens();

        assertEquals(2, expression.getTokens().length);
        assertEquals("multiple", tokens[0]);
        assertEquals("tokens", tokens[1]);
    }

    @Test
    void testOriginalPos() {
        RpnExpression expression = new RpnExpression("1 2 3 * 5 + * * 6 5");
        assertEquals(15, expression.getExpressionCharPos(7));
    }

    @Test
    void testOriginalPosWithMoreSpaces() {
        RpnExpression expression = new RpnExpression(" 1  invalid ");
        assertEquals(5, expression.getExpressionCharPos(1));
    }

    @Test
    void testOriginalPosWith() {
        RpnExpression expression = new RpnExpression(" 1  invalid ");
        assertEquals(5, expression.getExpressionCharPos(1));
    }
}
