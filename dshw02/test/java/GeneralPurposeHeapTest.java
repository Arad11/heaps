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
    void testPercDownDescendingOrder() {
        Integer[] initialData = {null, 9, 8, 7, 6, 5, 4, 3, 2, 1}; // 1-based index, null at 0
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>(initialData);
        heap.precDown(1, initialData[1], initialData.length - 1, initialData);
        assertArrayEquals(new Integer[]{null, 1, 2, 3, 4, 5, 9, 6, 7, 8}, initialData);
    }

    @Test
    void testPercDownPartialHeap() {
        Integer[] initialData = {null, 5, 3, 4, 1}; // only first 4 elements are considered
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>(initialData);
        heap.precDown(1, initialData[1], 4, initialData);
        assertArrayEquals(new Integer[]{null, 3, 1, 4, 5}, initialData);
    }

    @Test
    void testPercDownWithDuplicates() {
        Integer[] initialData = {null, 3, 3, 3, 2, 2, 2, 1}; // duplicates in heap
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>(initialData);
        heap.precDown(1, initialData[1], 7, initialData);
        assertArrayEquals(new Integer[]{null, 1, 2, 2, 3, 2, 3, 3}, initialData);
    }

    @Test
    void testPercDownSmallHeap() {
        Integer[] initialData = {null, 2, 1}; // small heap with 2 elements
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>(initialData);
        heap.precDown(1, initialData[1], 2, initialData);
        assertArrayEquals(new Integer[]{null, 1, 2}, initialData);
    }

    @Test
    void testPercDownLargeHeap() {
        Integer[] initialData = {null, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; // larger heap
        GeneralPurposeHeap<Integer> heap = new GeneralPurposeHeap<>(initialData);
        heap.precDown(1, initialData[1], 10, initialData);
        assertArrayEquals(new Integer[]{null, 1, 2, 3, 7, 6, 5, 4, 10, 9, 8}, initialData);
    }
}