import java.util.Arrays;
import java.util.NoSuchElementException;
public class GeneralPurposeHeap<T extends Comparable<T>> {
    private T[] heap;
    private int currentElementsAmount = 0;

    @SuppressWarnings("unchecked")
    public GeneralPurposeHeap() {
        heap = (T[]) new Comparable[100];
    }

    @SuppressWarnings("unchecked")
    public GeneralPurposeHeap(int capacity) {
        heap = (T[]) new Comparable[capacity];
    }

    public GeneralPurposeHeap(T[] initialData) {
        this.currentElementsAmount = initialData.length;
        heap = Arrays.copyOf(initialData, initialData.length );
        buildHeap(heap);
    }

    public void buildHeap(T[] array) {
        for (int i = array.length / 2; i > 0; i--) {
            precDown(i, array[i], array.length, array);
        }
    }

    public void insert(T element) {
        percUp(element);
        currentElementsAmount++;
    }

    public T findMin() {
        if (currentElementsAmount == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap[1];
    }

    public int getSize() {
        return currentElementsAmount;
    }

    public T deleteMin() {
        if (currentElementsAmount == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }

        T min = heap[1];
        T lastValueToPercDown = heap[currentElementsAmount];
        heap[1] = null;
        currentElementsAmount--;
        heap[currentElementsAmount] = null;
        precDown(1, lastValueToPercDown, currentElementsAmount, heap); 
        return min;
    }

    public void mergeHeap(GeneralPurposeHeap<T> otherHeap) {
        if (otherHeap.currentElementsAmount == 0) {
            return;
        }

        if (currentElementsAmount + otherHeap.currentElementsAmount >= heap.length) {
            resize(otherHeap.currentElementsAmount);
        }

        T[] newArray = otherHeap.heap.clone();

        for (int i = 1; i <= otherHeap.currentElementsAmount; i++) {
            insert(newArray[i]);
        }
    }

    public void precDown(int elementIndex, T valueToInsert, int elementsInHeap, T[] array) {
        int j;

        if(elementsInHeap == array.length - 1) {
            resize(1);
        } 
        
        if( 2 * elementIndex < elementsInHeap && 2 * elementIndex <= array.length) {
            if (array[2 * elementIndex].compareTo(array[(2 * elementIndex) + 1]) < 0) {
                j = 2 * elementIndex;
            } else {
                j = 2 * elementIndex + 1;
            }

            if (array[j].compareTo(valueToInsert) < 0) {
                array[elementIndex] = array[j];
                precDown(j, valueToInsert, elementsInHeap, array);
            } else {
                array[elementIndex] = valueToInsert;
            }
        } else if (2 * elementIndex == elementsInHeap && 2 * elementIndex <= array.length) {
            if(array[2 * elementIndex].compareTo(valueToInsert) < 0) {
                array[elementIndex] = array[2 * elementIndex];
                array[2 * elementIndex] = valueToInsert;
            } else {
                array[elementIndex] = valueToInsert;
            }
        } else if (2 * elementIndex > elementsInHeap && 2 * elementIndex <= array.length) {
            array[elementIndex] = valueToInsert;
        }
    }

    private void resize(int longerBy) {
        this.heap = Arrays.copyOf(heap, heap.length + longerBy);
    }

    private void precUp(int indexToInsert, T newValue, T[] array) {
        int parentIndex = indexToInsert / 2;

        if(currentElementsAmount + 1 > this.heap.length) {
            resize(1);
        }
        
        if (indexToInsert == 1) {
            array[1] = newValue;
        } else if (array[parentIndex].compareTo(newValue) < 0) {
            array[indexToInsert] = newValue;
        } else {
            array[indexToInsert] = array[parentIndex];
            precUp(parentIndex, newValue, array);
        }
    }

    private void percUp(T newValue) {
        precUp(currentElementsAmount + 1, newValue, heap);
    }
}

