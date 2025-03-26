package org.example;

public class Container {
    private int[] elements;
    private int size;
    private static final int default_size = 7;

    public Container() {
        this(default_size);
    }

    public Container(int InitialSize) {
        if (InitialSize < 0) {
            throw new IllegalArgumentException("Не может быть отрицательной");
        }
        this.elements = new int[InitialSize];
        this.size = 0;
    }

    private void increaseSize(int minSize) {
        if (minSize > elements.length) {
            int NewSize = elements.length * 2;
            if (NewSize < minSize) {
                NewSize = minSize;
            }
            int[] NewElem = new int[NewSize];
            System.arraycopy(elements, 0, NewElem, 0, size);
            elements = NewElem;
        }
    }

    public void add(int element) {
        increaseSize(size + 1);
        elements[size++] = element;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным: " + index);
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс превышает размер: " + index + " >= " + size);
        }
    }

    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}
