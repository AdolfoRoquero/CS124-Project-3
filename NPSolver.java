import java.util.Arrays;
import java.util.Random;

class NPSolver{
    private Random r = new Random();
    private long[] _arr;
    private int _n;
    private Solution _S;
    private long _res;
    private int _MAX_ITER;

    public NPSolver(long[] arr, int MAX_ITER){
        _arr = arr;
        _n = arr.length;
        _MAX_ITER = MAX_ITER;
    }

    public long repeatedRandom(boolean pp){
        _S = (pp) ? new Prepartition(_n): new Standard(_n);
        _res = _S.residue(_arr);
        for (int i = 0; i < _MAX_ITER; i++){
            Solution S = (pp) ? new Prepartition(_n): new Standard(_n);
            long res = S.residue(_arr);
            if (res < _res){
                _S = S;
                _res = res;
            }
        }
        return _res;
    }

    public long hillClimbing(boolean pp){
        _S = (pp) ? new Prepartition(_n): new Standard(_n);
        _res = _S.residue(_arr);
        for (int i = 0; i < _MAX_ITER; i++){
            Solution S = _S.randomNeighbor();
            long res = S.residue(_arr);
            if (res < _res){
                _S = S;
                _res = res;
            }
        }
        return _res;
    }

    public long simulatedAnnealing(boolean pp){
        _S = (pp) ? new Prepartition(_n): new Standard(_n);
        _res = _S.residue(_arr);
        Solution S = _S.copy(); //S will move, _S will always be best solution found so far.
        long res = _res;
        for (int i = 0; i < _MAX_ITER; i++){
            Solution S2 = S.randomNeighbor();
            long res2 = S2.residue(_arr);
            if (res2 < res){
                S = S2;
                res = res2;
            }
            else{
                double prob = Math.exp(-(res2-res)/T(i)); // Check for roundings
                if (r.nextDouble() < prob){
                    S = S2;
                    res= res2;
                }
            }
            if (res < _res){
                _S = S;
                _res = res;
            }
        }
        return _res;
    }

    public long compute(String algorithm_type){
        boolean pp = (algorithm_type.length() == 2);
        switch (algorithm_type){
            case "0":
                return KK(_arr);
            case "1":
            case "11":
                return repeatedRandom(pp);
            case "2":
            case "12":
                return hillClimbing(pp);
            case "3":
            case "13":
                return simulatedAnnealing(pp);
            default:
                System.out.println("Invalid algorithm type");
                return 0;
        }
    }

    private double T(int iter){
        return Math.pow(10,10) * Math.pow(0.8,iter/300);
    }

    public static long KK(long[] a){
        long[] arr = a.clone();
        int len = arr.length;
        for (int i = 1; i < len; i++){
            Arrays.sort(arr);
            // System.out.println("Pre");
            // System.out.println(Arrays.toString(array));
            arr[len - 1] = Math.abs(arr[len - 1] - arr[len - 2]);
            arr[len - 2] = 0;
            // System.out.println("Post");
            // System.out.println(Arrays.toString(array));
            // System.out.println();
        }
        return arr[len-1];
    }
}
