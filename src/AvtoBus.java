import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AvtoBus {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            long w = Long.valueOf(in.readLine());
            if(w < 4 || w % 2 != 0){
                System.out.println("-1");
                continue;
            }
            long max = -1, min = -1;
            max = w / 4;
            if(w < 6){
                min = max;
            } else {
                long mod6 = w % 6;
                min = w / 6;
                if(mod6 != 0){
                    min++;
                }
            }
            System.out.println(min + " " + max);
        }
    }
}