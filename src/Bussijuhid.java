import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Bussijuhid {

    public static BufferedReader in;
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int juhte = Integer.parseInt(sisend[0]);
        int max_marsruut = Integer.parseInt(sisend[1]);
        int lisa_tasu = Integer.parseInt(sisend[2]);
        String hommikused[] = in.readLine().split(" ");
        String ohtused[] = in.readLine().split(" ");
        ArrayList<Integer> hommikused_array = new ArrayList<>();
        ArrayList<Integer> ohtused_array = new ArrayList<>();

        for (int i = 0; i < juhte; i++) {
            hommikused_array.add(Integer.parseInt(hommikused[i]));
            ohtused_array.add(Integer.parseInt(ohtused[i]));
        }
        Collections.sort(hommikused_array);
        Collections.sort(ohtused_array);
        int ylejaak = 0;
        for (int i = 0; i < juhte; i++) {
            ylejaak += Math.max(hommikused_array.get(i) + ohtused_array.get(juhte - i - 1) - max_marsruut, 0);
        }
        System.out.println(ylejaak*lisa_tasu);


    }
}
