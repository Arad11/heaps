import java.util.Arrays;
import java.util.NoSuchElementException;
public class GeneralPurposeHeap<T extends Comparable<T>> {
    private T[] heap;
    private int currentElementsAmount = 0;

    public GeneralPurposeHeap() {
        heap = (T[]) new Comparable[100];
    }

    public GeneralPurposeHeap(int capacity) {
        heap = (T[]) new Comparable[capacity];
    }

    public GeneralPurposeHeap(T[] initialData) {
        if (initialData.length == 0) {
            heap = (T[]) new Comparable[0];
        } else if ( initialData[0] == null ) {
            this.currentElementsAmount = initialData.length - 1;
        } else {
            this.currentElementsAmount = initialData.length;
        }
        heap = Arrays.copyOf(initialData, initialData.length );
        buildHeap(heap);
    }

    public void buildHeap(T[] array) {
        for (int i = array.length / 2; i > 0; i--) {
            precDown(i, array[i], array.length - 1, array);
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

        T lastValueToPercDown = heap[currentElementsAmount];
        heap[currentElementsAmount] = null;
        currentElementsAmount--;
        T min = heap[1];
        heap[1] = null;
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

    private void precDown(int elementIndex, T valueToInsert, int elementsInHeap, T[] array) {
        int j;
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
        } else if (2 * elementIndex > elementsInHeap) {
            array[elementIndex] = valueToInsert;
        }
    }

    private void resize(int longerBy) {
        this.heap = Arrays.copyOf(heap, heap.length + longerBy);
    }

    private void precUp(int indexToInsert, T newValue) {
        int parentIndex = indexToInsert / 2;

        if(indexToInsert >= this.heap.length) {
            resize(1);
        }
        
        if (indexToInsert == 1) {
            heap[1] = newValue;
        } else if (heap[parentIndex].compareTo(newValue) < 0) {
            heap[indexToInsert] = newValue;
        } else {
            heap[indexToInsert] = heap[parentIndex];
            precUp(parentIndex, newValue);
        }
    }

    private void percUp(T newValue) {
        precUp(currentElementsAmount + 1, newValue);
    }
}

