import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class partition{
    private static long range = 1000000000000L;
    private static int size = 100;
    public static void main(String args[]){
        long [] array = generateArray();
        // System.out.println(KK(array));
        // System.out.println("Reapeated Random STD " + repeatedRandomSTD(array));
        // System.out.println("hill Climbing STD " + hillClimbingSTD(array));
        // System.out.println("simulated Annealing STD " + simulatedAnnealingSTD(array));
        NumberPartition pre = new NumberPartition(false);
        NumberPartition std = new NumberPartition(true);
        tests();
    }

    // Generate random array of size size(100) with values in range
    private static long[] generateArray(){
        Random r = new Random();
        long[] array = new long[size];
        for (int i = 0; i < size; i++){
            array[i] = 1 + (long) (r.nextDouble() * range);;
        }
        return array;
    }

    private static void tests(){
        NumberPartition std = new NumberPartition(true);
        NumberPartition pre = new NumberPartition(false);

        long[] arr1 = new long[] {1,2,3,4,5};
        long[] arr2 = new long[] {10000000000000L,20000000000000L,10000000000000L,9,8,12};
        long[] arr3 = new long[] {70, 20, 19, 9, 6, 6, 5, 4, 1};
        long[] arr4 = new long[] {1, 20, 6, 6, 9, 19, 70, 4, 5};
        long[] arr5 = new long[] {80, 70, 20, 19, 9, 6, 6, 5, 4, 1};
        long[] arr6 = new long[] {80, 70, 22, 20, 19, 8, 6, 6, 5, 4, 3, 1};
        long[] arr7 = new long[] {80, 70, 7, 13, 39};
        long[] arr8 = new long[] {3,7,5}; // equivalent to arr1 with pre = {1,1,2,2,3}
        long[] arr9 = new long[] {2,3,10}; // equivalent to arr1 with pre = {3,1,2,3,3}
        long[] arr10 = new long[] {80, 92, 40, 8, 6, 6, 5, 7}; // equivalent to arr6 with pre = {1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 8, 3}



        int[] sol1_1 = new int[] {1, 0, 0, 1, 0};
        int[] sol1_2 = new int[] {0, 0, 0, 1, 1};
        int[] sol2_1 = new int[] {0, 1, 0, 1, 0, 0};
        int[] sol2_2 = new int[] {1, 1, 1, 1, 1, 1};
        int[] sol2_3 = new int[] {0, 0, 0, 0, 0, 0};

        // Testing Residue STD
        long res1_1 = 5;
        long res1_2 = 3;
        long res2_1 = 11;
        long res2_2 = 40000000000029L;

        // System.out.println(std.residue(arr1, sol1_1));
        // System.out.println(pre.residue(arr1, sol1_1));

        assert(std.residue(arr1, sol1_1) == res1_1);
        assert(std.residue(arr1, sol1_2) == res1_2);
        assert(std.residue(arr2, sol2_1) == res2_1);
        assert(std.residue(arr2, sol2_2) == res2_2);
        assert(std.residue(arr2, sol2_3) == res2_2);

        // Testing Karmakar Karp
        long KK1 = 1;
        long KK2 = 5;
        long KK3 = 0;
        long KK7 = 9;
        assert(std.KK(arr1) == KK1);
        assert(std.KK(arr2) == KK2);
        assert(std.KK(arr3) == KK3);
        assert(std.KK(arr4) == KK3);
        assert(std.KK(arr5) == KK3);
        assert(std.KK(arr6) == KK3);
        assert(std.KK(arr7) == KK7);

        // Testing Residue Pre residue
        int[] pre1_1 = new int[] {1,2,2,1,2};
        int[] pre1_2 = new int[] {3,3,3,1,1};
        int[] pre2_1 = new int[] {4,2,4,2,4,4};
        int[] pre2_2 = new int[] {0,0,0,0,0,0};
        int[] pre2_3 = new int[] {2,2,2,2,2,2};


        assert(pre.residue(arr1, pre1_1) == res1_1);  // using only two numbers such for a +1 group and a -1 group
        assert(pre.residue(arr1, pre1_2) == res1_2);  // equivalent to std.residue with 0 and 1
        assert(pre.residue(arr2, pre2_1) == res2_1);
        assert(pre.residue(arr2, pre2_2) == res2_2);
        assert(pre.residue(arr2, pre2_3) == res2_2);

        int[] pre1_3 = new int[] {1,1,2,2,3};
        int[] pre1_4 = new int[] {3,1,2,3,3};
        int[] pre6_1 = new int[] {1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 8, 3};
        assert(pre.residue(arr1, pre1_3) == pre.KK(arr8));
        assert(pre.residue(arr1, pre1_4) == pre.KK(arr9));
        assert(pre.residue(arr6, pre6_1) == pre.KK(arr10));





    }
}
