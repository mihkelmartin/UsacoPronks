import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class k8_I_Valimised {

    private static class Riik{
        private String nimi;
        private int hind;
        private String vanem;
        private int haalte_arv = 1;
        HashSet<String> soltuvad_riigid = new HashSet<>();

        public Riik(String nimi, int hind, String vanem) {
            this.nimi = nimi;
            this.hind = hind;
            this.vanem = vanem;
        }
        private void varskendaVanem(Riik[] riigid){
            if(vanem != null){
                for (Riik riik : riigid) {
                    if(riik != null && riik.nimi.equals(vanem)){
                        riik.haalte_arv += 1;
                        riik.soltuvad_riigid.add(this.nimi);
                        riik.soltuvad_riigid.addAll(this.soltuvad_riigid);
                        riik.varskendaVanem(riigid);
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
        Riik[] riigid = new Riik[n];
        for (int i = 0; i < n ; i++) {
            sisend = in.readLine().split(" ");
            Riik riik = new Riik(sisend[0], Integer.parseInt(sisend[1]), sisend[2].equals("0") ? null : sisend[3]);
            riigid[i] = riik;
            riik.varskendaVanem(riigid);
        }

        int[] riikidearv_maksumus = new int[n+1];
        HashSet<String>[] kasutuses_riigid = new HashSet[n+1];
        riikidearv_maksumus[0] = 0;
        kasutuses_riigid[0] = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            riikidearv_maksumus[i] = Integer.MAX_VALUE;
            kasutuses_riigid[i] = new HashSet<>();
            for (int j = 1; j <= n; j++) {
                Riik riik = riigid[j-1];
                // Math.min, et massiivis välja ei läheks
                // Leia haalte arv

                int muutus_alates = Math.max(i - riik.haalte_arv, 0);
                int hind_enne = riikidearv_maksumus[muutus_alates];
                int hind_koos_uuega = hind_enne + riik.hind;

                if(!kasutuses_riigid[muutus_alates].contains(riik.nimi) &&
                        eiOleSoltuvateSeas(kasutuses_riigid[muutus_alates], riik, riigid)){
                    if(hind_koos_uuega < riikidearv_maksumus[i]){
                        kasutuses_riigid[i].clear();
                        kasutuses_riigid[i].addAll(kasutuses_riigid[muutus_alates]);
                        kasutuses_riigid[i].add(riik.nimi);
                        riikidearv_maksumus[i] = hind_koos_uuega;
                    }
                }
            }
        }
        System.out.println(riikidearv_maksumus[m]);
    }

    static boolean eiOleSoltuvateSeas(HashSet<String> kasutusesRiigid, Riik riik, Riik[] riigid){
        boolean retVal = true;
        for (String riiginimi : kasutusesRiigid) {
            for (Riik riik1 : riigid) {
                if(riiginimi.equals(riik1.nimi)){
                    if(riik1.soltuvad_riigid.contains(riik.nimi)){
                        return false;
                    }
                    for (String s : riik.soltuvad_riigid) {
                        if(s.equals(riik1.nimi))
                            return false;
                    }

                }
            }
        }

        return retVal;
    }

}

/*
6 2
Lati 15 0
USA 20 0
Kanada 10 1 USA
Eesti 40 1 Kanada
Leedu 30 1 Eesti
Poola 100 1 USA

 */