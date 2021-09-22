import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class k7_D_Kolimine {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int t = Integer.parseInt(in.readLine());

        int[] kaste = new int[t];
        String sisend[] = in.readLine().split(" ");
        for (int i = 0; i < t; i++) {
            kaste[i] = Integer.valueOf(sisend[i]);
        }
        Arrays.sort(kaste);
        int vastus = 1;
        for (int i = t - 1; i >= 0; i--) {
            int kast = kaste[i];
            int mahtus = 1;
            for (int j = i - 1; j >=0 ; j--) {
                if(kast >= kaste[j]){
                    kast -= kaste[j];
                    mahtus++;
                }
            }
            vastus = Math.max(vastus, mahtus);
        }
        System.out.println(vastus);
    }
}
