package org.example;

/**
 * Класс Container представляет собой контейнер для хранения чисел.
 */
public class Container {
    private int[] elements;
    private int size;
    private static final int default_size = 7;

    /**
     * Создает контейнер с размером по умолчанию.
     */
    public Container() {
        this(default_size);
    }

    /**
     * Создает контейнер с указанным начальным размером.
     *
     * @param InitialSize начальный размер контейнера
     * @throws IllegalArgumentException если начальный размер отрицателен.
     */
    public Container(int InitialSize) {
        if (InitialSize < 0) {
            throw new IllegalArgumentException("Не может быть отрицательной");
        }
        this.elements = new int[InitialSize];
        this.size = 0;
    }

    /**
     * Увеличивает размер внутреннего массива, если необходимо.
     *
     * @param minSize минимально требуемый размер.
     */
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

    /**
     * Добавляет элемент в контейнер.
     *
     * @param element добавляемый элемент
     */
    public void add(int element) {
        increaseSize(size + 1);
        elements[size++] = element;
    }

    /**
     * Проверяет, что индекс находится в допустимых границах.
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным: " + index);
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс превышает размер: " + index + " >= " + size);
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    /**
     * Проверяет, пуст ли контейнер.
     *
     * @return true, если контейнер пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает количество элементов в контейнере.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }
}