import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Kylaskaigud {
    public static BufferedReader in;
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int maju = Integer.parseInt(sisend[0]);
        int lapsi = Integer.parseInt(sisend[1]);
        TreeMap<Integer, Integer> maja_jargmine_korgus = new TreeMap<>();
        int eelmine_korgus = -1000001; // See ei saa ülesande tingmuste järgi olla
        int eelmine_indeks = -1;

        String[] majad = in.readLine().split(" ");
        for (int i = 1; i <= maju; i++) {
             int maja = Integer.valueOf(majad[i-1]);
             if(maja != eelmine_korgus){
                 if(eelmine_indeks != -1) maja_jargmine_korgus.put(eelmine_indeks, i);
                 eelmine_korgus = maja;
                 eelmine_indeks = i;
             }
        }
        maja_jargmine_korgus.put(eelmine_indeks, maju + 1);

        for (int i = 0; i < lapsi; i++) {
            String liikumine[] = in.readLine().split(" ");
            int maja1 = Integer.parseInt(liikumine[0]);
            int maja2 = Integer.parseInt(liikumine[1]);
            int parim_pikkus = 1;
            for(Map.Entry<Integer, Integer> entry : maja_jargmine_korgus.entrySet()){
                if(entry.getValue() - 1 > maja2)
                    break;
                parim_pikkus = Math.max(parim_pikkus, entry.getValue() - Math.max(maja1, entry.getKey()));
            }
            System.out.println(parim_pikkus);
        }
    }
}
