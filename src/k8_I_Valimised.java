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
        ArrayList<Riik> soltuvad_riigid = new ArrayList<>();

        public Riik(String nimi, int hind, String vanem) {
            this.nimi = nimi;
            this.hind = hind;
            this.vanem = vanem;
        }
        private void leiaSoltuvatRiigid(ArrayList<Riik> riigid){
            if(haalte_arv == -1) {
                haalte_arv = 1; // Vaikimisi 1, käesolev riik ise
                for (Riik riik : riigid) {
                    // On sõltuv riik
                    if (nimi.equals(riik.vanem)) {
                        riik.leiaSoltuvatRiigid(riigid);
                        this.haalte_arv += riik.haalte_arv;
                        soltuvad_riigid.add(riik);
                        soltuvad_riigid.addAll(riik.soltuvad_riigid);
                    }
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
        // Ei pea tegelikult sorteerima, aga õpi
        Collections.sort(riigid, Comparator.comparingInt(riik -> riik.hind));
        // Nii on kõige lihtsam meetodit välja kutsuda
        riigid.forEach(riik -> riik.leiaSoltuvatRiigid(riigid));

        // Indeks 0 on jällegi kui hääli on vaja 0
        int[] maksta_haali = new int[m + 1];
        // Mingil häälte arvul juba kasutatud riigid
        HashMap<Integer, ArrayList<Riik>> kasutatud_riigid = new HashMap<>();
        // Lisa kohe 0 haalte puhul kasutatud riigid, mis on tühi hulk
        kasutatud_riigid.put(0, new ArrayList<>());
        int vastus = 0;

        // Hakkame leidma dünaamiliselt parimat hinda alates 1
        for (int i = 1; i <= m; i++) {
            maksta_haali[i] = Integer.MAX_VALUE;
            ArrayList<Riik> voetud_kasutusele_kohal_i =  new ArrayList<>();
            kasutatud_riigid.put(i, voetud_kasutusele_kohal_i);

            for (Riik riik : riigid) {
                ArrayList<Riik> teiste_poolt_kasutusel_olevad =  new ArrayList<>();
                // Kui kaugel on ettevõetud riik alguspunktist
                // Arvtua maha need soltuvad mis juba kasutusel
                int alles_haalte_arv = riik.haalte_arv;
                kasutatud_riigid.get(Math.max(i - alles_haalte_arv, 0)).forEach(kasutatud_riik -> {
                    if(kasutatud_riik!=riik)
                        teiste_poolt_kasutusel_olevad.addAll(kasutatud_riik.soltuvad_riigid);
                        }
                );

                // Kui riik pole veel kasutusel siis ta võidakse võtta kasutusele kuid tema häälte arvu
                // tuleb vähendada selle võrra kui palju on temast sõltuvaid, ja sõltuvate sõltuvaid juba kasutusel

                // Siin probleem !!!! Kui sees on ka siis tuleb maha võtta, võib teiste kaudu olla
                for (Riik riik_soltuv : riik.soltuvad_riigid) {
                    // Eelmises peaks kõik kasutusel oleva sees olema
                    if (teiste_poolt_kasutusel_olevad.contains(riik_soltuv) ||
                            kasutatud_riigid.get(Math.max(i - alles_haalte_arv, 0)).contains(riik_soltuv))
                        alles_haalte_arv--;
                }

                int pos = Math.max(i - alles_haalte_arv, 0);
                if(!kasutatud_riigid.get(pos).contains(riik)){
                    // Mis kui on võrdne ?
                    if(maksta_haali[pos] + riik.hind < maksta_haali[i]){
                        maksta_haali[i] = maksta_haali[pos] + riik.hind;

                        // Uuendame riike mis on positsioonil kasutatud
                        voetud_kasutusele_kohal_i.clear();
                        voetud_kasutusele_kohal_i.addAll(kasutatud_riigid.get(pos));
                        voetud_kasutusele_kohal_i.add(riik);
                        vastus = maksta_haali[i];
                    }
                }
            }
            // Ka kõik eelenvalt kasutatud tuleb listi panna
            // voetud_kasutusele_kohal_i.addAll(kasutatud_riigid.get(i-1));
        }
        System.out.println(vastus);
    }
}
