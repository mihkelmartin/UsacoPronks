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

        // Ahne algoritm, ei pruugi alati töötada. Ehk siis sorteeritud suuruse järjekorras ning
        // seetõttu kõige suurem mis mahub läheb alati esimesena
        for (int i = t - 1; i >= 0; i--) {
            int kast = kaste[i]; // Siia kasti hakkame proovima järgmisi
            if(kast == Integer.MAX_VALUE) // See kast on kasutatud
                continue;
            for (int j = i - 1; j >=0 ; j--) {
                if(kast > kaste[j]){ // Kui järgmine väiksem
                    kast = kaste[j]; // Uus jooksev suuruss
                    kaste[j] = Integer.MAX_VALUE; // Kasutatud
                    peidus++;
                }
            }
        }
        System.out.println(t - peidus);
    }
}
