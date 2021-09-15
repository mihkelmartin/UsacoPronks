import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Kylaskaigud {
    public static void main(String[] args )throws Exception {

        final long startTime = System.currentTimeMillis();

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int maju = Integer.parseInt(sisend[0]);
        int lapsi = Integer.parseInt(sisend[1]);
        int[][] maja_grupp = new int[maju + 1][2]; // Maja koos samakõrgus grupi numbriga. Yks rohkem, et saaks alustada 1 indeksist

        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int maja_korgus_eelmine = -100001;
        int korgus_grupp = 1;
        int korgus_grupi_pikkus = 1;
        int korgus_grupi_algus = 1;
        TreeMap<Integer, Object> korgus_grupid_maps = new TreeMap<>();

        for (int i = 1; i <=maju ; i++) {

            int maja_korgus = Integer.valueOf(st.nextToken());
            maja_grupp[i][0] = maja_korgus; // i-nda maja kõrgus
            korgus_grupi_pikkus++;


            // Et esimene kord ei läheks järgmisse if-i
            if(i == 1){
                maja_korgus_eelmine = maja_korgus;
                korgus_grupi_pikkus = 0;
            }


            // Paneme tagantjärgi, kui grupp läbi
            if(maja_korgus != maja_korgus_eelmine){ // Ei ole esimene kord
                // Kui muutus kasvata korgus_gruppi
                maja_korgus_eelmine = maja_korgus;
                int[] korgus_grupi_andmed = new int[3];
                korgus_grupi_andmed[0] = korgus_grupi_algus;
                korgus_grupi_andmed[1] = i - 1; // lopp, esimene kord ei tule seega -1 ok
                korgus_grupi_andmed[2] = korgus_grupi_pikkus;
                korgus_grupid_maps.put(korgus_grupp, korgus_grupi_andmed);

                korgus_grupp++;
                korgus_grupi_algus = i; // järgmise korgus_grupi algus
                korgus_grupi_pikkus = 0;
            }
            // pane kõrgusgrupp
            maja_grupp[i][1] = korgus_grupp;
        }
        // Lisa viimane
        int[] korgus_grupi_andmed = new int[3];
        korgus_grupi_andmed[0] = korgus_grupi_algus;
        korgus_grupi_andmed[1] = maju; // lopp
        korgus_grupi_andmed[2] = ++korgus_grupi_pikkus;
        korgus_grupid_maps.put(korgus_grupp, korgus_grupi_andmed);

        HashMap<Integer, Integer> gruppide_pikkuste_puu = new HashMap<>();
        loo_kahendpuu(korgus_grupid_maps, gruppide_pikkuste_puu, 1, 1, korgus_grupid_maps.size());

        // Nüüd tuleb teha kahendpuu Kõrgusgruppidest
        // Sealt saame kiirelt teada mis on mingite gruppide pikimad lõigud - näiteks grupist 35 - 2300 pikim lõik 45
        // Kui antakse sisend majade indeksitega, siis massiivist maja_grupp leiame kuhu gruppi vastava maja kuulub
        // Kui on antud maja_algus ja maja_lopp, siis leiame nende grupid ning otsime maja_alguse grupist ühest suurema
        // ja maja_lopp grupist ühe väiksema vahemikust. Sest äärtes võib olla mittetäielik kattumine, neid peab eraldi vaatama
        // Selleks aga on meil korgus_grupid_maps TreeMap, kust saame vastavad andmed

        // Kuidas teha kahendpuu


        System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime));

    }

    private static void loo_kahendpuu(TreeMap<Integer, Object> korgus_grupid_maps, HashMap<Integer, Integer> gruppide_pikkuste_puu, int tipp, int algus, int lopp) {

        if(algus == lopp) { // Oleme jõudnud puu lehele, kus on algandmed otse
            gruppide_pikkuste_puu.put(tipp, ((int[])korgus_grupid_maps.get(algus))[3]);
            return;
        }

        int uus_tipp = tipp * 2;
        int pooleks = (lopp - algus) / 2;
        loo_kahendpuu(korgus_grupid_maps, gruppide_pikkuste_puu, uus_tipp, algus, pooleks);
        loo_kahendpuu(korgus_grupid_maps, gruppide_pikkuste_puu, uus_tipp  + 1, pooleks + 1, (lopp);

        gruppide_pikkuste_puu.put(tipp, Math.max(gruppide_pikkuste_puu.get(uus_tipp), gruppide_pikkuste_puu.get(uus_tipp+1)));

    }
}
