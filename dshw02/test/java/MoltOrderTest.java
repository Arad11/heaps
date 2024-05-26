import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MoltOrderTest {
    List<String> expectedMethodNames = List.of("toString", "getOrderReadyTime", "getTimeNeededToDeliver",
            "getName", "getOrderDescription", "compareTo");


    @Test
    void allMethodsPresent() {
        List<String> actualMethodNames = Arrays.stream(MoltOrder.class.getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .map(Method::getName)
                .collect(Collectors.toList());

        List<String> missingNames = expectedMethodNames.stream()
                .filter(name -> !actualMethodNames.contains(name))
                .collect(Collectors.toList());

        String message = "The following methods are missing: " + String.join(", ", missingNames);
        assertEquals(0, missingNames.size(), message);
    }


    @Test
    void testConstructorAndGetters() {
        MoltOrder order = new MoltOrder("Pizza", "Large Pizza", 30, 15, 1);
        assertEquals("Pizza", order.getName());
        assertEquals("Large Pizza", order.getOrderDescription());
        assertEquals(30, order.getOrderReadyTime());
        assertEquals(15, order.getTimeNeededToDeliver());
        assertEquals(1, order.getPriority());
    }

    @Test
    void testConstructorException() {
        assertThrows(IllegalArgumentException.class, () -> new MoltOrder("Pizza", "Large Pizza", -10, 15, 1));
        assertThrows(IllegalArgumentException.class, () -> new MoltOrder("Pizza", "Large Pizza", 30, -5, 1));
    }

    @Test
    void testCompareTo() {
        MoltOrder order1 = new MoltOrder("Pizza", "Large Pizza", 30, 15, 1);
        MoltOrder order2 = new MoltOrder("Burger", "Double Burger", 30, 15, 2);
        assertTrue(order1.compareTo(order2) < 0);
        assertTrue(order2.compareTo(order1) > 0);
        assertEquals(0, order1.compareTo(order1));
    }

    @Test
    void testToString() {
        MoltOrder order = new MoltOrder("Pizza", "Large Pizza", 30, 15, 1);
        String expected = "MoltOrder{name='Pizza', orderDescription='Large Pizza', orderReadyTime=30, timeNeededToDeliver=15, priority=1}";
        assertEquals(expected, order.toString());
    }
}