import collection.ArrayList_MarinaKosareva;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        ArrayList_MarinaKosareva<Integer> arr = new ArrayList_MarinaKosareva<>();
        for (int i=0; i<20; i++) {
            arr.add((int)(Math.random()*100));
        }
        System.out.println("Лист с элементами типа Integer:");
        print(arr);



        arr.quickSort(Comparator.comparingInt(o -> (int) o));
        System.out.println("Лист с элементами типа Integer после сортировки:");
        print(arr);
        arr.split(5);
        System.out.println("Лист с элементами типа Integer после split");
        print(arr);
        System.out.println("Лист с элементами типа Integer после удаления по индексу");
        arr.remove(1);
        print(arr);


        ArrayList_MarinaKosareva<Test> arr2 = new ArrayList_MarinaKosareva<>();
        for (int i=0; i<10; i++) {
            arr2.add(new Test(i));
        }
        System.out.println("Лист с элементами типа Test:");
        print(arr2);
        System.out.println("Лист не отсортирован, т.к. Test не имплементирует Comparable:");
        System.out.println(arr2.isSorted());

        ArrayList_MarinaKosareva<Test> arr3 = new ArrayList_MarinaKosareva<>();
        for (int i=0; i<10; i++) {
            arr3.add(new Test2(i));
        }
        System.out.println("Лист с элементами типа Test2:");
        print(arr3);
        System.out.println("Лист отсортирован, т.к. Test2 имплементирует Comparable:");
        System.out.println(arr3.isSorted());
        System.out.println(arr3.isSorted());


        ArrayList_MarinaKosareva<Test3> arr4 = new ArrayList_MarinaKosareva<>();
        for (int i=0; i<10; i++) {
            arr4.add(new Test3((int)(Math.random()*100)));
        }
        System.out.println("Лист с элементами типа Test3:");
        print(arr4);

        //за счет wildcard используем компараторы для родительских классов
        //arr4.quickSort(Test2::compareTo);
        arr4.quickSort(new Comparator<Test>() {
            @Override
            public int compare(Test o1, Test o2) {
                return o1.getVal()-o2.getVal();
            }
        });
        System.out.println("Лист с элементами типа Test3 после сортировки:");
        print(arr4);
    }

    private static void print(ArrayList_MarinaKosareva arr) {
        for (int i=0; i<arr.size(); i++)
            System.out.println(arr.get(i));
    }
}

class Test {
    int val;

    public Test(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" +
                "val=" + val +
                '}';
    }

    public int getVal() {
        return val;
    }
}

class Test2 extends Test implements Comparable{

    public Test2(int val) {
        super(val);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Test2 && o!=null)
            return getVal() - ((Test2)o).getVal();
        else return -1;
    }
}

class Test3 extends Test2 {

    public Test3(int val) {
        super(val);
    }
}