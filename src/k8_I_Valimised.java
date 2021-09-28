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
        // -1 näitab, et ei ole veel kontrollitud
        private int soltuvate_riikide_arv = -1;

        public Riik(String nimi, int hind, String vanem) {
            this.nimi = nimi;
            this.hind = hind;
            this.vanem = vanem;
        }
        private void leiaSoltuvateRiikideArv(ArrayList<Riik> riigid){
            soltuvate_riikide_arv = 0;
            for (Riik riik : riigid){
                // On sõltuv riik
                if(nimi.equals(riik.vanem)){
                    soltuvate_riikide_arv++;
                    // Leia tema alamate hulk
                    if(riik.soltuvate_riikide_arv == -1) {
                        riik.leiaSoltuvateRiikideArv(riigid);
                    } else {
                        int i = 0;
                    }
                    this.soltuvate_riikide_arv += riik.soltuvate_riikide_arv;
                    // Kuna riik ei saa olla mitmele vanem, siis katkestame
                    break;
                }
            }
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
        riigid.forEach(riik -> riik.leiaSoltuvateRiikideArv(riigid));
        System.out.println("OK");
    }
}
