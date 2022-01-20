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
            soltuvad_riigid.add(nimi);
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
        HashSet<String>[] haalte_riigid = new HashSet[n+1];
        HashSet<String>[] valitud_riigid = new HashSet[n+1];
        riikidearv_maksumus[0] = 0;
        haalte_riigid[0] = new HashSet<>();
        valitud_riigid[0] = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if(riikidearv_maksumus[i] == 0) riikidearv_maksumus[i] = Integer.MAX_VALUE;
            haalte_riigid[i] = new HashSet<>();
            valitud_riigid[i] = new HashSet<>();
            for (int j = 1; j <= n; j++) {
                Riik riik = riigid[j-1];

                for (int k = 1; k <= riik.haalte_arv && i - k >= 0 ; k++) {
                    int muutus_alates = i - k;
                    // Haali üldse kokku
                    int haali = haaliKoosEelnevaga(haalte_riigid[muutus_alates], riik);
                    int haali_lisaks = haali - muutus_alates;

                    if(haali_lisaks >= k){
                        int hind_enne = riikidearv_maksumus[muutus_alates];
                        int hind_koos_uuega = hind_enne + riik.hind;

                        if (hind_koos_uuega < riikidearv_maksumus[i]) {
                            haalte_riigid[i].clear();
                            haalte_riigid[i].addAll(haalte_riigid[muutus_alates]);
                            haalte_riigid[i].addAll(riik.soltuvad_riigid);
                            valitud_riigid[i].clear();
                            valitud_riigid[i].addAll(valitud_riigid[muutus_alates]);
                            valitud_riigid[i].add(riik.nimi);
                            riikidearv_maksumus[i] = hind_koos_uuega;
                        }
                    }
                }
            }
        }
        System.out.println(riikidearv_maksumus[m]);
    }

    static int haaliKoosEelnevaga(HashSet<String> kasutusesRiigid, Riik riik){
        int retVal = 0;
        for (String riigiNimi : riik.soltuvad_riigid) {
            if(kasutusesRiigid.contains(riigiNimi))
                retVal++;
        }
        return kasutusesRiigid.size() + riik.soltuvad_riigid.size() - retVal;
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