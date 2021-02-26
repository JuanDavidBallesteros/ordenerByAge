import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<List<Double>> arrays;
    private static List<String> countList;

    public final static String ABSOLUTE_PATH_IN = "data/Input2.txt";
    public final static String ABSOLUTE_PATH_OUT = "data/Output2.txt";

    private static int countChanges;
    private static int countSteps;

    private static DecimalFormat df;

    public static void main(String[] args) throws Exception {
        arrays = new ArrayList<>();
        countList = new ArrayList<>();
        countChanges = 0;
        countSteps = 0;

        df = new DecimalFormat("#.##");

        importData();

        for (int i = 0; i < arrays.size(); i++) {
            List<Double> temp = bubbleSorting(arrays.get(i));
            arrays.set(i, temp);
        }

        exportData();

    }

    private static void importData() throws IOException {
        BufferedReader br;

        br = new BufferedReader(new FileReader(ABSOLUTE_PATH_IN));
        String line;

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
        System.out.println("Data readed from: " + ABSOLUTE_PATH_IN);
    }


    private static List<Double> bubbleSorting(List<Double> list) {

        resetCountChanges();
        resetCountSteps();

        String message = "";

        for (int i = list.size() - 1; i > 0; i--) {

            for (int j = 0; j < list.size() - 1; j++) {

                if (list.get(j) > list.get(j + 1)) {
                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set((j + 1), temp);

                    countOfChanges();
                }
            }
            countOfSteps();
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                message += list.get(i);
            } else {
                message += list.get(i) + " ";
            }
        }

        df.setRoundingMode(RoundingMode.DOWN);
        String temp = df.format(promCalculation(countChanges, countSteps)) + "";

        if (temp.indexOf(".") == -1) {
            temp += ".0";
        }
        countList.add(temp + "-" + message);

        return list;
    }

    private static void countOfChanges() {
        countChanges++;
    }

    private static void resetCountChanges() {
        countChanges = 0;
    }

    private static void countOfSteps() {
        countSteps++;
    }

    private static void resetCountSteps() {
        countSteps = 0;
    }

    private static double promCalculation(int changes, int steps) {
        double temp = (double) changes / steps;
        return temp;
    }

    private static void exportData() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ABSOLUTE_PATH_OUT));

        for (int i = 0; i < countList.size(); i++) {
            String line = countList.get(i);
                bw.write(line + "\n");
        }
        bw.close();

        System.out.println("Data written in: " + ABSOLUTE_PATH_OUT);
    }
}
