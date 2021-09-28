import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class k8_I_Valimised {
    private static class Riik{
        private String nimi;
        private int hind = 0;
        private String vanem;

        public Riik(String nimi, int hind, String vanem) {
            this.nimi = nimi;
            this.hind = hind;
            this.vanem = vanem;
        }
    }
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int n = Integer.parseInt(sisend[0]);
        int m = Integer.parseInt(sisend[1]);
        ArrayList<Riik> riigid = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            sisend = in.readLine().split(" ");
            riigid.add(new Riik(sisend[0], Integer.parseInt(sisend[1]), sisend[2].equals("0") ? null : sisend[3]));
        }
        Collections.sort(riigid, Comparator.comparingInt(riik -> riik.hind));
        System.out.println("OK");
    }
}
