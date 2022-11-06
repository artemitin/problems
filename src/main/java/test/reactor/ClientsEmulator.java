package test.reactor;

import java.util.Collection;
import java.util.LinkedHashMap;

public class ClientsEmulator {

    public static void main(String[] args) {
        int a = 5;
        int b = 9;
        a = a^b; //a^b = x; x^b = a
        b = a^b;
        a = a^b;
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
        lhm.put(1, "1");
        lhm.put(2, "2");
        lhm.put(3, "3");
        lhm.get(3);
//        ExecutorService es = Executors.newFixedThreadPool(100);
//        es.shutdown();

        Collection<String> values = lhm.values();

        System.out.println(String.join(", ", "" + a, "" + b));
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n/2; i++){
            for(int j = i; j < n - i - 1; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i -1] = tmp;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                if (j != i) {
                    matrix[i][j] = matrix[i][j]^matrix[j][i];
                    matrix[j][i] = matrix[i][j]^matrix[j][i];
                    matrix[i][j] = matrix[i][j]^matrix[j][i];
                }
            }
        }

        int low;
        int high;
        for (int i = 0; i < matrix.length; i++) {
            low = 0;
            high = matrix.length - 1;
            while (low < high) {
                matrix[i][low] = matrix[i][low]^matrix[i][high];
                matrix[i][high] = matrix[i][low]^matrix[i][high];
                matrix[i][low] = matrix[i][low]^matrix[i][high];
                low++;
                high--;
            }
        }

    }
}
