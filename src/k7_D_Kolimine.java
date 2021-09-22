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
        int peidus = 0;
        for (int i = t - 1; i >= 0; i--) {
            int kast = kaste[i];
            if(kast == Integer.MAX_VALUE)
                continue;
            int mahub = kast;
            for (int j = i - 1; j >=0 ; j--) {
                if(mahub > kaste[j]){
                    mahub = kaste[j];
                    kaste[j] = Integer.MAX_VALUE; // Kasutatud
                    peidus++;
                }
            }
        }
        System.out.println(t - peidus);
    }
}
