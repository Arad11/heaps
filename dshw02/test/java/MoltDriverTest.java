import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MoltDriverTest {
    List<String> expectedMethodNames = List.of("incrementTotalOrdersDelivered", "getNextAvailableTimeForDelivery", "setNextAvailableTimeForDelivery", "toString", "compareTo");

    @Test
    void allMethodsPresent() {
        List<String> actualMethodNames = Arrays.stream(MoltDriver.class.getMethods())
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
        MoltDriver driver = new MoltDriver(1, "Alice", 10);
        assertEquals("Alice", driver.getName());
        assertEquals(10, driver.getNextAvailableTimeForDelivery());
        assertEquals(0, driver.getTotalOrdersDelivered());
    }

    @Test
    void testIncrementTotalOrdersDelivered() {
        MoltDriver driver = new MoltDriver(1, "Alice", 10);
        driver.incrementTotalOrdersDelivered();
        assertEquals(1, driver.getTotalOrdersDelivered());
    }

    @Test
    void testSetNextAvailableTimeForDelivery() {
        MoltDriver driver = new MoltDriver(1, "Alice", 10);
        driver.setNextAvailableTimeForDelivery(20);
        assertEquals(20, driver.getNextAvailableTimeForDelivery());
    }

    @Test
    void testSetNextAvailableTimeForDeliveryException() {
        MoltDriver driver = new MoltDriver(1, "Alice", 10);
        assertThrows(IllegalArgumentException.class, () -> driver.setNextAvailableTimeForDelivery(-5));
    }

    @Test
    void testCompareTo() {
        MoltDriver driver1 = new MoltDriver(1, "Alice", 10);
        MoltDriver driver2 = new MoltDriver(2, "Bob", 20);
        assertTrue(driver1.compareTo(driver2) < 0);
        assertTrue(driver2.compareTo(driver1) > 0);
        assertEquals(0, driver1.compareTo(driver1));
    }

    @Test
    void testToString() {
        MoltDriver driver = new MoltDriver(1, "Alice", 10);
        String expected = "MoltDriver{id=1, name='Alice', nextAvailableTimeForDelivery=10, totalOrdersDelivered=0}";
        assertEquals(expected, driver.toString());
    }
}