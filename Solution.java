import java.util.Arrays;
import java.util.Random;

abstract class Solution{
    protected static Random r = new Random();
    protected int[] _arr;
    protected int _n;

    public Solution(int[] arr){
        _arr = arr;
        _n = arr.length;
    }

    public int[] getArray(){
        return _arr;
    }

    public void print(){
        System.out.println(Arrays.toString(_arr));
    }

    public abstract Solution copy();

    public abstract Solution randomNeighbor();

    public abstract long residue(long[] set);
}

class Standard extends Solution{

    public Standard(int n){
        this(randArray(n));
    }

    public Standard(int[] arr){
        super(arr);
    }

    @Override
    public Solution randomNeighbor(){
        int[] neighbor = _arr.clone();
        int i = r.nextInt(_n), j = r.nextInt(_n);
        while (j == i){                   // ensure i and j are different
            j = r.nextInt(_n);
        }
        neighbor[i] = 1 - neighbor[i];
        if (r.nextDouble() > 0.5){      // flip coin for swapping
            neighbor[j] = 1 - neighbor[j];
        }
        return new Standard(neighbor);
    }

    @Override
    public long residue(long[] set){
        long residue = 0;
        for (int i = 0; i < _n; i++) {
            if (_arr[i] == 1){
                residue += set[i];
            }
            else{
                residue -= set[i];
            }
        }
        return Math.abs(residue);
    }

    @Override
    public Solution copy(){
        return new Standard(_arr.clone());
    }

    private static int[] randArray(int n){
        int[] rand = new int[n];
        for (int i = 0; i < n; i++){
            rand[i] = r.nextInt(2); // 0 represent -1 and 1 represents 1
        }
        return rand;
    }
}

class Prepartition extends Solution{

    public Prepartition(int n){
        this(randArray(n));
    }

    public Prepartition(int[] arr){
        super(arr);
    }

    @Override
    public Solution randomNeighbor(){
        int[] neighbor = _arr.clone();
        int i = r.nextInt(_n), j = r.nextInt(_n);
        while (j == neighbor[i]){                   // ensure p_i and j are different
            j = r.nextInt(_n);
        }
        neighbor[i] = j;
        return new Prepartition(neighbor);
    }

    @Override
    public long residue(long[] set){
        long[] constrained = new long[set.length];
        for (int i = 0; i < set.length; i++){
            constrained[_arr[i]] += set[i];
        }
        return NPSolver.KK(constrained);
    }

    @Override
    public Solution copy(){
        return new Prepartition(_arr.clone());
    }

    private static int[] randArray(int n){
        int[] rand = new int[n];
        for (int i = 0; i < n; i++){
            rand[i] = r.nextInt(n); // shouldn't values be from [0, n-1] instead of [1, n] for indexing?
        }
        return rand;
    }
}
