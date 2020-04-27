import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class partition{
    private static int ARRAY_SIZE = 100;

    public static void main(String[] args){
        // don't print anything extraneous when first input is 0
        try{
            long[] arr = fileToArray(args[2]);
            NPSolver NPS = new NPSolver(arr, (args[1].length() == 2)); // second arg -> pp
            switch (args[1]){
                case "0":
                    System.out.println(NPSolver.KK(arr));
                    break;
                case "1":
                case "11":
                    System.out.println(NPS.repeatedRandom());
                    break;
                case "2":
                case "12":
                    System.out.println(NPS.hillClimbing());
                    break;
                case "3":
                case "13":
                    System.out.println(NPS.simulatedAnnealing());
                    break;
                default:
                    System.out.println("Error in algorithm type");
                    break;
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
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
