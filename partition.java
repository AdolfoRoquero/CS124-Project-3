import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class partition{
    private static int ARRAY_SIZE = 100;
    private static int MAX_ITER = 25000;

    public static void main(String[] args){
        // don't print anything extraneous when first input is 0
        try{
            long[] arr = fileToArray(args[2]);
            NPSolver NPS = new NPSolver(arr, MAX_ITER); // second arg -> pp
            System.out.println(NPS.compute(args[1]));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static long[] fileToArray(String filename) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            long[] arr = new long[ARRAY_SIZE];
            String line = br.readLine();
            int i = 0;
            while (line != null){       //so we can test on smaller input files
                if (i < 100){
                    arr[i] = Long.parseLong(line);
                    i += 1;
                    line = br.readLine();
                }
                else{
                    break;
                }
            }
            return arr;
        }
    }
}
