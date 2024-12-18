import collection.ArrayList_MarinaKosareva;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList_MarinaKosareva<Integer> arr = new ArrayList_MarinaKosareva<>();
        for (int i=0; i<20; i++) {
            arr.add((int)(Math.random()*100));
        }
        print(arr);

        arr.quickSort(Comparator.naturalOrder());
        System.out.println("sorted arr:");
        print(arr);
        arr.split(5);
        System.out.println("After split");
        print(arr);
        System.out.println("After remove by index");
        arr.remove(1);
        print(arr);
    }

    private static void print(ArrayList_MarinaKosareva arr) {
        for (int i=0; i<arr.size(); i++)
            System.out.println(arr.get(i));
    }
}