import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CF_B {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int t = Integer.parseInt(in.readLine());

        int[] kaste = new int[t];
        String sisend[] = in.readLine().split(" ");
        for (int i = 0; i < t; i++) {
            kaste[i] = Integer.valueOf(sisend[i]);
        }
    }
}
