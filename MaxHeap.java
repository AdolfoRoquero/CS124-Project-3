import java.util.Arrays;

class MaxHeap {

    private int d;
    private int size;
    private long[] Heap;


    public MaxHeap(int n) {
        d = 2;
        size = 0;
        Heap = new long[n];
        // Initialize arrays
        Arrays.fill(Heap, -1);
    }

    private int parent(int pos){
        return (pos-1)/d;
    }

    private int kthChild(int pos, int k){
        return d*pos+k;
    }

    private void swap(int fpos, int spos){
        long tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private int maxChild(int pos){
        int biggest = kthChild(pos, 1);
        if (biggest >= size){
            return pos;
        }
        else{
            int k = 2;
            int next = kthChild(pos, k);
            while ((k <= d) && (next < size)){
                if (Heap[biggest] < Heap[next]){
                    biggest = next;
                }
                next = kthChild(pos, ++k);
            }
            return biggest;
        }
    }

    private void maxHeapify(int pos){
        int child = maxChild(pos);
        if (Heap[child] > Heap[pos]){
            swap(child, pos);
            maxHeapify(child);
        }
    }

    private boolean checkHeap(){
        for (int p = 1; p < size; p++){
            if (Heap[parent(p)] < Heap[p]){
                return false;
            }
        }
        return true;
    }

    private boolean checkExtract(long max){
        for (int w = 0; w < Heap.length; w++){
            if (Heap[w] > max ){
                return false;
            }
        }
        return true;
    }

    public long extractMax(){
        long max = Heap[0];
        assert checkExtract(max) : "Extracted node not maximum";
        swap(0, --size);
        Heap[size] = -1;
        if (size != 0){
            maxHeapify(0);
        }
        assert checkHeap() : "Invariant false after minHeapify";
        return max;
    }

    public void insert(long v){
        Heap[size] = v;
        size++;
        int pos = size - 1;
        while ((pos > 0) && (Heap[parent(pos)] < Heap[pos])){
            swap(pos,parent(pos));
            pos = parent(pos);
        }
        assert checkHeap(): "Invariant false after insert  " + Arrays.toString(Heap);
    }

    public int getSize(){
        return size;
    }

}
