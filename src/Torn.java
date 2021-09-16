import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Torn {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int kaste = Integer.parseInt(in.readLine());

        int[][] kastid_kandevoimed = new int[kaste][2];
        for (int i = 0; i < kaste; i++) {
            String sisend[] = in.readLine().split(" ");
            int kaal = Integer.parseInt(sisend[0]);
            int kandevoime = Integer.parseInt(sisend[1]);
            kastid_kandevoimed[i][0] = kaal;
            kastid_kandevoimed[i][1] = kandevoime;
        }

        int parim_kaste = 0;
        for (int i = 0; i < kaste; i++) {
            int valitud_kasti_indeks = i;
            int kandevoime = kastid_kandevoimed[i][1]; // Pane max algusesse
            int max_kaste = 1;
            while(true){
                int vahepeale_parim_kandevoime = Integer.MIN_VALUE;
                for (int j = valitud_kasti_indeks; j < kaste; j++) {
                    int uus_kandevoime = Math.min(kastid_kandevoimed[j][1], kandevoime - kastid_kandevoimed[j][0]);
                    if(uus_kandevoime > vahepeale_parim_kandevoime){
                        vahepeale_parim_kandevoime = uus_kandevoime;
                        valitud_kasti_indeks = j;
                    }
                }
                kandevoime = vahepeale_parim_kandevoime;
                if(kandevoime >= 0){
                    max_kaste++;
                } else {
                    break;
                }
            }
            if(max_kaste>parim_kaste)
                parim_kaste = max_kaste;

        }
        System.out.println(parim_kaste);
    }
}
