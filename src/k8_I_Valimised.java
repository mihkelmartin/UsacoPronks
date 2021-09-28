import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class k8_I_Valimised {
    private static class Riik{
        private String nimi;
        private int hind = 0;
        private String vanem;
        // -1 näitab, et ei ole veel kontrollitud
        private int haalte_arv = -1;

        public Riik(String nimi, int hind, String vanem) {
            this.nimi = nimi;
            this.hind = hind;
            this.vanem = vanem;
        }
        private void leiaSoltuvateRiikideArv(ArrayList<Riik> riigid){
            haalte_arv = 1; // Vaikimisi 1
            for (Riik riik : riigid){
                // On sõltuv riik
                if(nimi.equals(riik.vanem)){
                    // Leia tema alamate hulk
                    if(riik.haalte_arv == -1) {
                        riik.leiaSoltuvateRiikideArv(riigid);
                    } else {
                        int i = 0;
                    }
                    this.haalte_arv += riik.haalte_arv;
                    // Kuna riik ei saa olla mitmele vanem, siis võime katkestada
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

        // Indeks 0 on jällegi kui hääli on vaja 0
        int[] maksta_haali = new int[m + 1];
        HashMap<Integer, ArrayList<Riik>> kasutatud_riigid = new HashMap<>();
        for (int i = 1; i <= m; i++) {

        }

        System.out.println("OK");
    }
}
