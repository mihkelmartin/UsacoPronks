import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Torn {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int kaste = Integer.parseInt(in.readLine());

        TreeMap<Integer, Integer> kastid = new TreeMap<>();
        while (kaste-- > 0) {
            String sisend[] = in.readLine().split(" ");
            int kaal = Integer.parseInt(sisend[0]);
            int kandevoime = Integer.parseInt(sisend[1]);

            if(kastid.get(kandevoime) != null){
               int kaal_olemas = kastid.get(kandevoime);
               if(kaal_olemas > kaal){
                   kastid.put(kandevoime, kaal);
               }
            } else {
                kastid.put(kandevoime, kaal);
            }
        }
        int max_kaste = 0;
        int kandevoime = Integer.MAX_VALUE; // Pane max algusesse
        while (true){
            int vahepeale_parim_kandevoime = Integer.MIN_VALUE;
            int vahepealne_parim_kast = 0;
            for (Map.Entry<Integer, Integer> entry : kastid.entrySet()){
                if(entry.getValue() <= kandevoime) {
                    int uus_kandevoime = Math.min(entry.getKey(), kandevoime - entry.getValue());
                    if(uus_kandevoime > vahepeale_parim_kandevoime){
                        vahepeale_parim_kandevoime = uus_kandevoime;
                        vahepealne_parim_kast = entry.getValue();
                    }
                }
            }
            //System.out.println(vahepealne_parim_kast + " " + vahepeale_parim_kandevoime);

            kandevoime = vahepeale_parim_kandevoime;
            if(kandevoime >= 0){
                max_kaste++;
            } else {
                break;
            }
        }
        System.out.println(max_kaste);
    }
}
