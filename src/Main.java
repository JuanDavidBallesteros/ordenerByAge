import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static List<List<Double>> arrays;

    public final static String ABSOLUTE_PATH_IN = "data/Input2.txt";

    public static void main(String[] args) throws Exception {
        arrays = new ArrayList<>();

        importData();
        for(int i = 0; i < arrays.size() ; i++)
            System.out.println(Arrays.toString(arrays.get(i).toArray()));
    }

    private static void importData() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(ABSOLUTE_PATH_IN));
            String line;
            try {
                line = br.readLine();
                line = br.readLine();

                while (line != null) {
                    String[] parts = line.split(" ");
                    List<Double> temp = new ArrayList<>();

                    for (int i = 0; i < parts.length; i++) {
                        temp.add(Double.parseDouble(parts[i]));
                    }
                    arrays.add(temp);

                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }

    }
}
