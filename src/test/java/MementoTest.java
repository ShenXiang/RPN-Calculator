import com.rpncalc.snapshot.Caretaker;
import com.rpncalc.snapshot.Memento;
import com.rpncalc.stack.OperandValue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MementoTest {

    @Test
    void testRestoreMultiMementos() {
        Caretaker caretaker = new Caretaker();

        Deque<OperandValue> queue = new ArrayDeque<>();

        Random r = new Random();
        int count = r.nextInt(100);

        for (int i = 0; i < count; ++i) {
            queue.addLast(new OperandValue(new BigDecimal("" + count)));
            caretaker.add(new Memento(queue));
        }

        for (int i = 0; i < count; ++i) {
            assertEquals(count - i, caretaker.getMemento().getStack().size());
            caretaker.undo();
        }
    }

    @Test
    void testRestoreEmptyMemento() {
        Caretaker caretaker = new Caretaker();
        assertTrue(caretaker.undo().getMemento().getStack().isEmpty());
    }
}
