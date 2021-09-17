import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class StatistikaManipulatsioon {

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        ArrayList<Object> autod = new ArrayList<>();

        int autosid = Integer.parseInt(in.readLine());
        for (int i = 0; i < autosid; i++) {
            String sisend[] = in.readLine().split(" ");
            int hind = Integer.parseInt(sisend[0]);
            int onnetusi = Integer.parseInt(sisend[1]);
            int[] paar = new int[2];
            paar[0] = hind;
            paar[1] = onnetusi;
            autod.add(paar);
        }
        Collections.sort(autod, Comparator.comparingInt(a -> ((int[]) a)[0]));

        int[] pikkused = new int[autosid];
        // Üheelemendine jada on 1 pikkune
        for (int i = 0; i < autosid; i++) {
            pikkused[i] = 1;
        }

        int vastus = 1;
        for (int i = 0; i < autosid; i++) {
            vastus = Math.max(vastus, pikkused[i]);
            for (int j = i + 1; j < autosid; j++) {
                if(((int[])autod.get(j))[1] < ((int[])autod.get(i))[1]){
                    pikkused[j] = Math.max(pikkused[j], pikkused[i] + 1);
                }
            }
        }
        System.out.println(vastus);
    }

}

