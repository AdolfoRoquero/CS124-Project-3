import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class NumberPartition {
    private boolean STD;
    private static Random r = new Random();
    private static int max_iter = 25000;

    public NumberPartition(boolean b){
        this.STD = b;
    }

    public long KK(long[] a){
        long[] array = a.clone();
        int len = array.length;
        for (int i = 1; i < len; i++){
            Arrays.sort(array);
            // System.out.println("Pre");
            // System.out.println(Arrays.toString(array));
            array[len - 1] = Math.abs(array[len - 2] - array[len - 1]);
            array[len - 2] = 0;
            // System.out.println("Post");
            // System.out.println(Arrays.toString(array));
            // System.out.println();
        }
        return array[len-1];
    }

    public long repeatedRandom(long[] a){
        int[] randS1 = randSolution(a.length);
        for (int i = 0; i < max_iter; i++){
            int[] randS2 = randSolution(a.length);
            if (residue(a, randS2) < residue(a, randS1)){
                randS1 = randS2;
            }
        }
        return residue(a, randS1);
    }

    public long hillClimbing(long[] a){
        int[] sol = randSolution(a.length);
        for (int i = 0; i < max_iter; i++){
            int[] neighbor = randomNeighbor(sol);
            if (residue(a, neighbor) < residue(a, sol)){
                sol = neighbor;
            }
        }
        return residue(a, sol);
    }

    public long simulatedAnnealing(long[] a){
        int[] sol = randSolution(a.length);
        int[] sol2 = sol.clone();
        for (int i = 0; i < max_iter; i++){
            int[] neighbor = randomNeighbor(sol);
            long residueSol = residue(a, sol);
            long residueNeigh = residue(a, neighbor);
            if (residueNeigh < residueSol){
                sol = neighbor;
                residueSol = residueNeigh;
            }
            else{
                double tIter = Math.pow(10,10) * Math.pow(0.8,i/300);
                double prob = Math.exp(-(residueNeigh-residueSol)/tIter); // Check for roundings
                if (r.nextDouble() < prob){
                    sol = neighbor;
                    residueSol = residueNeigh;
                }
            }
            if (residueSol < residue(a, sol2)){
                sol2 = sol;
            }
        }
        return residue(a, sol2);
    }


    // Generates a random solution which is a list of 1's and -1's
    // So that we don't have to convert we use booleans where True == 1 and False == -1
    private int[] randSolution(int size){
        int[] array = new int[size];
        if(this.STD){
            for (int i = 0; i < size; i++){
                array[i] = r.nextInt(2); // 0 represent -1 and 1 represents 1
            }
        }
        else{
            for (int i = 0; i < size; i++){
                array[i] = 1 + r.nextInt(size);
            }
        }
        return array;
    }

    public long residue(long[] array, int[] solution){
        assert (array.length == solution.length) : "solution and array have different lengths";
        // System.out.println("array length " + array.length);
        // System.out.println("solution length " + solution.length);
        if (this.STD){
            long residue = 0;
            for (int i = 0; i < array.length; i++) {
                if (solution[i] == 1){
                    residue += array[i];
                }
                else{
                    residue -= array[i];
                }
            }
            return Math.abs(residue);
        }
        else{
            long[] constrained = new long[array.length];
            for (int i = 0; i < array.length; i++){
                constrained[solution[i]] += array[i];
            }
            return KK(constrained);
        }
    }

    private int[] randomNeighbor(int[] solution){
        int size = solution.length;
        int i = r.nextInt(size);
        int j = r.nextInt(size);
        if (this.STD){
            while (j == i){                   // ensure i and j are different
                j = r.nextInt(size);
            }
            solution[i] = 1 - solution[i];;
            if (r.nextDouble() > 0.5){      // flip coin for swapping
                solution[j] = 1 - solution[j];
            }
        }
        else{
            while (j == solution[i]){                   // ensure p_i and j are different
                j = r.nextInt(size);
            }
            solution[i] = j;
        }
        return solution;
    }
}
