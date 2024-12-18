package util;

import java.util.Comparator;

public class Arrays_MarinaKosareva {
    private Arrays_MarinaKosareva() {
    }

    public static void quickSort(Object[] arr, int low, int high, Comparator comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);

            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }

    private static int partition(Object[] arr, int low, int high, Comparator comparator) {
        // Выбор среднего элемента в качестве опорного
        int middle = low + (high - low) / 2;
        Object pivot = arr[middle];

        // Обмен опорного элемента с последним
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

    private static void swap(Object[] arr, int i, int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
