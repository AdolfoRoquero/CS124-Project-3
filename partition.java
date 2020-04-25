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
        NumberPartition pre = new NumberPartition(true);
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

    
    // private static long KK(long[] a){
    //     long[] array = a.clone();  
    //     int len = array.length;
    //     for (int i = 1; i < len; i++){
    //         Arrays.sort(array);
    //         // System.out.println("Pre");
    //         // System.out.println(Arrays.toString(array));
    //         array[len - 1] = Math.abs(array[len - 2] - array[len - 1]);
    //         array[len - 2] = 0;
    //         // System.out.println("Post");
    //         // System.out.println(Arrays.toString(array));
    //         // System.out.println();
    //     }
    //     return array[len-1];
    // }

    // private static long repeatedRandomSTD(long[] a){
    //     boolean[] randS1 = randSTDsolution();
    //     for (int i = 0; i < max_iter; i++){
    //         boolean[] randS2 = randSTDsolution();
    //         if (residueSTD(a, randS2) < residueSTD(a, randS1)){
    //             randS1 = randS2;
    //         }
    //     }
    //     return residueSTD(a, randS1);
    // }

    // private static long hillClimbingSTD(long[] a){
    //     boolean[] sol = randSTDsolution();
    //     for (int i = 0; i < max_iter; i++){
    //         boolean[] neighbor = randomNeighborSTD(sol);
    //         if (residueSTD(a, neighbor) < residueSTD(a, sol)){
    //             sol = neighbor;
    //         }
    //     }
    //     return residueSTD(a, sol);
    // }

    // private static long simulatedAnnealingSTD(long[] a){
    //     boolean[] sol = randSTDsolution();
    //     boolean[] sol2 = sol.clone();
    //     for (int i = 0; i < max_iter; i++){
    //         boolean[] neighbor = randomNeighborSTD(sol);
    //         long residueSol = residueSTD(a, sol);
    //         long residueNeigh = residueSTD(a, neighbor);
    //         if (residueNeigh < residueSol){
    //             sol = neighbor;
    //             residueSol = residueNeigh;
    //         }
    //         else{
    //             double tIter = Math.pow(10,10) * Math.pow(0.8,i/300);
    //             double prob = Math.exp(-(residueNeigh-residueSol)/tIter); // Check for roundings
    //             if (r.nextDouble() < prob){
    //                 sol = neighbor;
    //                 residueSol = residueNeigh;
    //             }
    //         }
    //         if (residueSol < residueSTD(a, sol2)){
    //             sol2 = sol;
    //         }
    //     }
    //     return residueSTD(a, sol2);
    // }

    // // Generate random array of size size(100) with values in range
    // private static long[] generateArray(){
    //     long[] array = new long[size];
    //     for (int i = 0; i < size; i++){
    //         array[i] = randNum();
    //     }
    //     return array;
    // }

    // // Generate a random integer from 1 to range (10^12)
    // private static long randNum(){
    //     return 1 + (long) (r.nextDouble() * range);
    // }

    // // Generates a random standard solution which is a list of 1's and -1's
    // // So that we don't have to convert we use booleans where True == 1 and False == -1
    // private static boolean[] randSTDsolution(){
    //     boolean[] array = new boolean[size];
    //     for (int i = 0; i < size; i++){
    //         array[i] = r.nextBoolean();
    //     }
    //     return array;
    // }

    // // Computes the residue for a given solution
    // private static long residueSTD(long[] array, boolean[] solution){
    //     assert (array.length == solution.length) : "solution and array have different lengths";
    //     // System.out.println("array length " + array.length);
    //     // System.out.println("solution length " + solution.length);
    //     long residue = 0;
    //     for (int i = 0; i < array.length; i++) {
    //         if (solution[i]){
    //             residue += array[i];
    //         }
    //         else{
    //             residue -= array[i];
    //         }
    //     }
    //     return Math.abs(residue);
    // }

    // private static boolean[] randomNeighborSTD(boolean[] solution){
    //     int i = r.nextInt(size);
    //     int j = r.nextInt(size);
    //     while (j == i){                   // ensure i and j are different
    //         j = r.nextInt(size);
    //     }
    //     solution[i] = !solution[i];
    //     if (r.nextDouble() > 0.5){      // flip coin for swapping
    //         solution[j] = !solution[j];
    //     }
    //     return solution;
    // }
    
    // private static long residuePRE(long[] array, int[] prepart){
    //     assert (array.length == prepart.length) : "prepart and array have different lengths";
    //     long[] constrained = new long[array.length];
    //     for (int i = 0; i < array.length; i++){
    //         constrained[prepart[i]] += array[i];
    //     }
    //     return KK(constrained);
    // }

    // private static int[] randPREsolution(){
    //     int[] array = new int[size];
    //     for (int i = 0; i < size; i++){
    //         array[i] = 1 + r.nextInt(size);
    //     }
    //     return array;
    // }

    


    




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