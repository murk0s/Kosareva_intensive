package collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ArrayList_MarinaKosareva <E> implements IntensiveList {
    /**
     * Дефолтная ёмкость массива
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Массив, в котором хранятся элементы листа ArrayList_MarinaKosareva
     */
    private Object[] elements;
    /**
     * Размер листа ArrayList_MarinaKosareva (количество элементов, которые содержит лист)
     */
    private int size;
    /**
     *Флаг сортировки листа
     */
    private boolean isSorted = false;
    /**
     * Конструтор пустого листа
     */
    public ArrayList_MarinaKosareva() {
        isSorted = true;
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Возвращает размер листа
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец листа
     * в процессе добавления проверяется ёмкость, при необходимости увеличивается
     * @param element
     */
    @Override
    public void add(Object element) {
        if (size == elements.length)
            elements = grow();
        elements[size] = element;
        size++;
        isSorted = false;
    }

    /**
     * Добавляет элементв специальную позицию листа
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException  – если индекс не в диапозоне листа (index < 0 || index > size())
     */
    @Override
    public void add(int index, Object element) {
        rangeCheckForAdd(index);
        if (size == elements.length)
            elements = grow();
        System.arraycopy(elements,index,elements,index+1,size-index);
        elements[index] = element;
        size++;
        isSorted = false;
    }

    /**
     * Возвращает элемент по индексу
     * @param index - индекс листа, по которому возвращается элемент
     * @return - элемент листа
     * @throws IndexOutOfBoundsException  – если индекс не в диапозоне листа (index < 0 || index >= size())
     */
    @Override
    public Object get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * Сохраняет значение в лист по индексу
     * @param index
     * @param element
     * @return
     */
    @Override
    public Object set(int index, Object element) {
        rangeCheck(index);
        elements[index] = element;
        return elements[index];
    }

    /**
     * Удаляет элемент по индексу, перемещает элементы после индекса влево
     * @param index – индекс элемента, который будет удален
     * @return элемент, который был удален
     * @throws IndexOutOfBoundsException – если индекс не в диапазоне листа (index < 0 || index >= size()
     */
    @Override
    public Object remove(int index) {
        Objects.checkIndex(index, size);
        Object oldValue = elements[index];

        size--;
        if (size > index)
            System.arraycopy(elements, index + 1, elements, index, size - index);

        return oldValue;
    }

    /**
     * Удаляет элементы из листа, устанавливает ёмкость массива по умолчанию
     */
    @Override
    public void clear() {
        size=0;
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Сортирует лист
     * @param comparator
     */
    @Override
    public void quickSort(Comparator comparator) {
        if (!isSorted) {
            quickSort(elements, 0, size - 1, comparator);
            isSorted = true;
        }
    }

    /**
     *
     * @return флаг сортировки листа
     */
    @Override
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Обрезает массив до заданного размера
     * элементы массива в диапазоне от size до size()-1 обнуляются
     * @param size - новый размер массива
     * @throws IndexOutOfBoundsException – если новый размер не в диапазоне листа (size < 0 || size > size())
     */
    @Override
    public void split(int size) {
        rangeCheckForAdd(size);
        for (int i=size; i<this.size; i++) elements[i] = null;
        this.size = size;
    }

    /**
     * Вспомогательный метод вызывается из grow()
     * изменяет ёмкость массива
     * @param minCapacity - минимально необходимый размер массива
     * @return
     */
    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > 0 ) {
            int newCapacity = (int) (minCapacity*1.5);
            return elements = Arrays.copyOf(elements, newCapacity);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    /**
     * Изменяет ёмкость массива
     * @return
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * Версия проверки индекса для add() и split()
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Проверка индекса
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
    }



    private void quickSort(Object[] arr, int low, int high, Comparator comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);

            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }

    private int partition(Object[] arr, int low, int high, Comparator comparator) {
        // Выбор среднего элемента в качестве опорного
        int middle = low + (high - low) / 2;
        Object pivot = arr[middle];
        swap(arr, middle, high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) > 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i + 1;
    }

    private void swap(Object[] arr, int i, int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
