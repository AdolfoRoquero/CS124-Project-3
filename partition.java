import java.util.Arrays;
import java.util.Random;
import java.lang.Math;


public class partition{
    private static int size = 100;
    private static long range = 1000000000000L;
    private static int max_iter = 100;
    private static Random r = new Random();
    public static void main(String args[]){
        long [] array = generateArray();
        System.out.println(KK(array));
        System.out.println(Arrays.toString(generateArray()));

    }
    
    private static long KK(long[] a){
        long[] array = a.clone();  
        int len = array.length;
        for (int i = 1; i < len; i++){
            Arrays.sort(array);
            // System.out.println("Pre");
            // System.out.println(Arrays.toString(array));
            array[len - 1]= Math.abs(array[len-2] - array[len-1]);
            array[len - 2] = 0;
            // System.out.println("Post");
            // System.out.println(Arrays.toString(array));
            // System.out.println();
        }
        return array[len-1];
    }

    private static boolean[] repeatedRandomSTD(long[] a){
        boolean[] randS1 = randSTDsolution();
        for (int i = 0; i < max_iter; i++){
            boolean[] randS2 = randSTDsolution();
            if (residueSTD(a, randS2) < residueSTD(a, randS1)){
                randS1 = randS2;
            }
        }
        return randS1;
    }

    private static boolean[] hillClimbingSTD(long[] a){
        boolean[] sol = randSTDsolution();
        for (int i = 0; i < max_iter; i++){
            boolean[] neighbor = randomNeighborSTD(sol);
            if (residueSTD(a, neighbor) < residueSTD(a, sol)){
                sol = neighbor;
            }
        }
        return sol;
    }

    private static boolean[] simulatedAnnealingSTD(long[] a){
        boolean[] sol = randSTDsolution();
        boolean[] sol2 = sol.clone();
        for (int i = 0; i < max_iter; i++){
            boolean[] neighbor = randomNeighborSTD(sol);
            long residueSol = residueSTD(a, sol);
            long residueNeigh = residueSTD(a, neighbor);
            if (residueNeigh < residueSol){
                sol = neighbor;
                residueSol = residueNeigh;
            }
            else{
                double prob = Math.exp(-(residueNeigh-residueSol)/(double)i); // I'm not sure what they mean by T(i)
                if (r.nextDouble() < prob){
                    sol = neighbor;
                    residueSol = residueNeigh;
                }
            }
            if (residueSol < residueSTD(a, sol2)){
                sol2 = sol;
            }
        }
        return sol2;
    }

    // Generate random array of size size(100) with values in range
    private static long[] generateArray(){
        long[] array = new long[size];
        for (int i = 0; i < size; i++){
            array[i] = randNum();
        }
        return array;
    }

    // Generate a random integer from 1 to range (10^12)
    private static long randNum(){
        return 1 + (long) (r.nextDouble() * range);
    }

    // Generates a random standard solution which is a list of 1's and -1's
    // So that we don't have to convert we use booleans where True == 1 and False == -1
    private static boolean[] randSTDsolution(){
        boolean[] array = new boolean[size];
        for (int i = 0; i < size; i++){
            array[i] = r.nextBoolean();
        }
        return array;
    }

    // Computes the residue for a given solution
    private static int residueSTD(long[] array, boolean[] solution){
        int residue = 0;
        for (int i = 0; i < size; i++) {
            if (solution[i]){
                residue += array[i];
            }
            else{
                residue -= array[i];
            }
        }
        return residue;
    }

    private static boolean[] randomNeighborSTD(boolean[] solution){
        int i = 1 + r.nextInt(size);
        int j = 1 + r.nextInt(size);
        while (j == i){                   // ensure i and j are different
            j = 1 + r.nextInt(size);
        }
        solution[i] = !solution[i];
        if (r.nextDouble() > 0.5){      // flip coin for swapping
            solution[j] = !solution[j];
        }
        return solution;
    }
    
    private static int residuePrepart(long[] array, boolean[] prepart){
        int residue = 0;
        return residue;
    }
}