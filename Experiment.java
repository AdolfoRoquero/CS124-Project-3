import java.util.Random;

public class Experiment{
    private static int _ARRAY_SIZE;
    private static int _NUM_TRIALS;
    private static int _MAX_ITER;
    private static long _MAX_VAL = 1000000000000L;
    private static long _MIN_VAL = 1L;
    private static String[] algorithm_types = {"0", "1", "2", "3", "11", "12", "13"};

    public static void main(String[] args){
        _ARRAY_SIZE = Integer.parseInt(args[0]);
        _NUM_TRIALS = Integer.parseInt(args[1]);
        _MAX_ITER = Integer.parseInt(args[2]);
        for (int i = 0; i <= _NUM_TRIALS; i++){
            System.out.println("trial "+ i + "\n");
            long[] arr = randLongArray(_ARRAY_SIZE);
            NPSolver NPS = new NPSolver(arr, _MAX_ITER);
            for (String alg : algorithm_types){
                timedCompute(NPS, alg); //prints residue and algorithm time.
            }
            System.out.println();
        }
    }

    private static void timedCompute(NPSolver NPS, String alg){
        long startTime = System.nanoTime();
        long res = NPS.compute(alg);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("alg: " + alg + "\tres: " + res + "\ttime: " + duration);
    }

    private static long[] randLongArray(int n){
        Random r = new Random();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++){
            arr[i] = _MIN_VAL + (long) (r.nextDouble() * (_MAX_VAL - _MIN_VAL));
        }
        return arr;
    }
}
