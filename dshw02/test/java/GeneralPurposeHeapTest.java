import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class GeneralPurposeHeapTest {


    List<String> expectedMethodNames = List.of("insert", "findMin", "getSize", "deleteMin", "mergeHeap");

    @Test
    void allMethodsPresent() {
        List<String> actualMethodNames = Arrays.stream(GeneralPurposeHeap.class.getMethods())
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
    void testConstructor() {
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>();
        assertEquals(0, heap.getSize());
    }

    @Test
    void testInsertAndFindMin() {
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>();
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        assertEquals(3, heap.getSize());
        assertEquals(5, heap.findMin());
    }

    @Test
    void testDeleteMin() {
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>();
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        assertEquals(5, heap.deleteMin());
        assertEquals(2, heap.getSize());
        assertEquals(10, heap.findMin());
    }

    @Test
    void testDeleteMinException() {
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>();
        assertThrows(NoSuchElementException.class, heap::deleteMin);
    }

    @Test
    void testFindMinException() {
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>();
        assertThrows(NoSuchElementException.class, heap::findMin);
    }

    @Test
    void testMergeHeap() {
        GeneralPurposeHeap<Integer> heap1 = new GeneralPurposeHeap<>();
        heap1.insert(10);
        heap1.insert(5);

        GeneralPurposeHeap<Integer> heap2 = new GeneralPurposeHeap<>();
        heap2.insert(15);
        heap2.insert(3);

        heap1.mergeHeap(heap2);

        assertEquals(4, heap1.getSize());
        assertEquals(3, heap1.findMin());
    }


    @Test
    public void secTestInsertAndFindMin() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);

        assertEquals(driver2, heap.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void secTestDeleteMin() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);

        assertEquals(driver2, heap.deleteMin(), "The minimum element should be driver2");
        assertEquals(driver1, heap.findMin(), "The new minimum element should be driver1");
    }

    @Test
    public void testGetSize() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap.insert(driver1);
        heap.insert(driver2);

        assertEquals(2, heap.getSize(), "The size of the heap should be 2");
    }

    @Test
    public void secTestMergeHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();
        
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);
        MoltDriver driver4 = new MoltDriver(4, "Driver4", 2);

        heap1.insert(driver1);
        heap1.insert(driver2);
        heap2.insert(driver3);
        heap2.insert(driver4);

        heap1.mergeHeap(heap2);

        assertEquals(4, heap1.getSize(), "The size of the merged heap should be 4");
        assertEquals(driver4, heap1.findMin(), "The minimum element of the merged heap should be driver4");
    }

    @Test
    public void testHeapEmptyException() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();

        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
        assertThrows(NoSuchElementException.class, heap::deleteMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void testHeapWithInitialData() {
        MoltDriver[] drivers = {
            new MoltDriver(1, "Driver1", 5),
            new MoltDriver(2, "Driver2", 3),
            new MoltDriver(3, "Driver3", 8),
            new MoltDriver(4, "Driver4", 2)
        };
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(drivers);

        assertEquals(4, heap.getSize(), "The size of the heap should be 4");
        assertEquals(drivers[3], heap.findMin(), "The minimum element should be driver4");
    }


    @Test
    public void atestInsertAndFindMin() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);

        assertEquals(driver2, heap.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void atestDeleteMin() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);

        assertEquals(driver2, heap.deleteMin(), "The minimum element should be driver2");
        assertEquals(driver1, heap.findMin(), "The new minimum element should be driver1");
    }

    @Test
    public void atestGetSize() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap.insert(driver1);
        heap.insert(driver2);

        assertEquals(2, heap.getSize(), "The size of the heap should be 2");
    }

    @Test
    public void atestMergeHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();
        
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);
        MoltDriver driver4 = new MoltDriver(4, "Driver4", 2);

        heap1.insert(driver1);
        heap1.insert(driver2);
        heap2.insert(driver3);
        heap2.insert(driver4);

        heap1.mergeHeap(heap2);

        assertEquals(4, heap1.getSize(), "The size of the merged heap should be 4");
        assertEquals(driver4, heap1.findMin(), "The minimum element of the merged heap should be driver4");
    }

    @Test
    public void btestHeapEmptyException() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();

        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
        assertThrows(NoSuchElementException.class, heap::deleteMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void atestHeapWithInitialData() {
        MoltDriver[] drivers = {
            new MoltDriver(1, "Driver1", 5),
            new MoltDriver(2, "Driver2", 3),
            new MoltDriver(3, "Driver3", 8),
            new MoltDriver(4, "Driver4", 2)
        };
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(drivers);

        assertEquals(4, heap.getSize(), "The size of the heap should be 4");
        assertEquals(drivers[3], heap.findMin(), "The minimum element should be driver4");
    }

    @Test
    public void testInsertBeyondInitialCapacity() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(2);
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);  // This should trigger a resize

        assertEquals(3, heap.getSize(), "The size of the heap should be 3");
        assertEquals(driver2, heap.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void testDeleteAllElements() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap.insert(driver1);
        heap.insert(driver2);

        heap.deleteMin();
        heap.deleteMin();

        assertEquals(0, heap.getSize(), "The size of the heap should be 0");
        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void testMergeEmptyHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();

        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap1.insert(driver1);
        heap1.insert(driver2);

        heap1.mergeHeap(heap2);  // Merging with an empty heap

        assertEquals(2, heap1.getSize(), "The size of the heap should remain 2");
        assertEquals(driver2, heap1.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void testMergeIntoEmptyHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();

        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap2.insert(driver1);
        heap2.insert(driver2);

        heap1.mergeHeap(heap2);  // Merging into an empty heap

        assertEquals(2, heap1.getSize(), "The size of the heap should be 2");
        assertEquals(driver2, heap1.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void testBuildHeapFromEmptyArray() {
        MoltDriver[] emptyDrivers = {};
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(emptyDrivers);

        assertEquals(0, heap.getSize(), "The size of the heap should be 0");
        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void testFindMinAfterMultipleInserts() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 7);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 5);
        MoltDriver driver4 = new MoltDriver(4, "Driver4", 1);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);
        heap.insert(driver4);

        assertEquals(driver4, heap.findMin(), "The minimum element should be driver4");
    }

    @Test
    public void vtestInsertAndFindMin() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);

        assertEquals(driver2, heap.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void qtestDeleteMin() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);

        assertEquals(driver2, heap.deleteMin(), "The minimum element should be driver2");
        assertEquals(driver1, heap.findMin(), "The new minimum element should be driver1");
    }

    @Test
    public void qtestGetSize() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap.insert(driver1);
        heap.insert(driver2);

        assertEquals(2, heap.getSize(), "The size of the heap should be 2");
    }

    @Test
    public void qtestMergeHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();
        
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);
        MoltDriver driver4 = new MoltDriver(4, "Driver4", 2);

        heap1.insert(driver1);
        heap1.insert(driver2);
        heap2.insert(driver3);
        heap2.insert(driver4);

        heap1.mergeHeap(heap2);

        assertEquals(4, heap1.getSize(), "The size of the merged heap should be 4");
        assertEquals(driver4, heap1.findMin(), "The minimum element of the merged heap should be driver4");
    }

    @Test
    public void etestHeapEmptyException() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();

        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
        assertThrows(NoSuchElementException.class, heap::deleteMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void etestHeapWithInitialData() {
        MoltDriver[] drivers = {
            new MoltDriver(1, "Driver1", 5),
            new MoltDriver(2, "Driver2", 3),
            new MoltDriver(3, "Driver3", 8),
            new MoltDriver(4, "Driver4", 2)
        };
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(drivers);

        assertEquals(4, heap.getSize(), "The size of the heap should be 4");
        assertEquals(drivers[3], heap.findMin(), "The minimum element should be driver4");
    }

    @Test
    public void etestInsertBeyondInitialCapacity() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(2);
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 8);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);  // This should trigger a resize

        assertEquals(3, heap.getSize(), "The size of the heap should be 3");
        assertEquals(driver2, heap.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void etestDeleteAllElements() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap.insert(driver1);
        heap.insert(driver2);

        heap.deleteMin();
        heap.deleteMin();

        assertEquals(0, heap.getSize(), "The size of the heap should be 0");
        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void etestMergeEmptyHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();

        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap1.insert(driver1);
        heap1.insert(driver2);

        heap1.mergeHeap(heap2);  // Merging with an empty heap

        assertEquals(2, heap1.getSize(), "The size of the heap should remain 2");
        assertEquals(driver2, heap1.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void etestMergeIntoEmptyHeap() {
        GeneralPurposeHeap<MoltDriver> heap1 = new GeneralPurposeHeap<>();
        GeneralPurposeHeap<MoltDriver> heap2 = new GeneralPurposeHeap<>();

        MoltDriver driver1 = new MoltDriver(1, "Driver1", 5);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);

        heap2.insert(driver1);
        heap2.insert(driver2);

        heap1.mergeHeap(heap2);  // Merging into an empty heap

        assertEquals(2, heap1.getSize(), "The size of the heap should be 2");
        assertEquals(driver2, heap1.findMin(), "The minimum element should be driver2");
    }

    @Test
    public void etestBuildHeapFromEmptyArray() {
        MoltDriver[] emptyDrivers = {};
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(emptyDrivers);

        assertEquals(0, heap.getSize(), "The size of the heap should be 0");
        assertThrows(NoSuchElementException.class, heap::findMin, "Should throw NoSuchElementException when heap is empty");
    }

    @Test
    public void extendstestFindMinAfterMultipleInserts() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>();
        MoltDriver driver1 = new MoltDriver(1, "Driver1", 7);
        MoltDriver driver2 = new MoltDriver(2, "Driver2", 3);
        MoltDriver driver3 = new MoltDriver(3, "Driver3", 5);
        MoltDriver driver4 = new MoltDriver(4, "Driver4", 1);

        heap.insert(driver1);
        heap.insert(driver2);
        heap.insert(driver3);
        heap.insert(driver4);

        assertEquals(driver4, heap.findMin(), "The minimum element should be driver4");
    }

    @Test
    public void testHeapWithLargeNumberOfElements() {
        GeneralPurposeHeap<MoltDriver> heap = new GeneralPurposeHeap<>(1000);
        MoltDriver[] drivers = new MoltDriver[1000];

        for (int i = 0; i < 1000; i++) {
            drivers[i] = new MoltDriver(i, "Driver" + i, 1000 - i);
            heap.insert(drivers[i]);
        }

        assertEquals(1000, heap.getSize(), "The size of the heap should be 1000");
        assertEquals(drivers[999], heap.findMin(), "The minimum element should be driver999");
    }

}