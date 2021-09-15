import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
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

        // Nüüd tuleb teha kahendpuu Kõrgusgruppidest
        // Sealt saame kiirelt teada mis on mingite gruppide pikimad lõigud - näiteks grupist 35 - 2300 pikim lõik 45
        // Kui antakse sisend majade indeksitega, siis massiivist maja_grupp leiame kuhu gruppi vastava maja kuulub
        // Kui on antud maja_algus ja maja_lopp, siis leiame nende grupid ning otsime maja_alguse grupist ühest suurema
        // ja maja_lopp grupist ühe väiksema vahemikust. Sest äärtes võib olla mittetäielik kattumine, neid peab eraldi vaatama
        // Selleks aga on meil korgus_grupid_maps TreeMap, kust saame vastavad andmed


        HashMap<Integer, Integer> gruppide_pikkuste_puu = new HashMap<>();
        // Siin on gruppide arve see Maksimum
        loo_kahendpuu(korgus_grupid_maps, gruppide_pikkuste_puu, 1, 1, korgus_grupid_maps.size());

        // Hakka otsima
        while (lapsi-- > 0){
            String maja1_maja2[] = in.readLine().split(" ");
            int maja1 = Integer.valueOf(maja1_maja2[0]);
            int maja2 = Integer.valueOf(maja1_maja2[1]);

            int maja_grupp1 = maja_grupp[maja1][1];
            int maja_grupp2 = maja_grupp[maja2][1];
            if(maja_grupp1 == maja_grupp2){
                System.out.println(maja2 - maja1 + 1);
                continue;
            }

            // Grupid võta +1 ja -1. Ostsi vaatame eraldi
            // Korgus --> lopp peab olema korgus grupid !!!!
            int max_kaugus = otsi_kahendpuust(gruppide_pikkuste_puu, 1, 1, korgus_grupid_maps.size(),
                    maja_grupp1 + 1, maja_grupp2 - 1);


            // Lisaks  tuleb vaadata kaugusi lõikude otspunktides kui alguse maha ei ole täpselt grupi alguses või
            // lõpu maja ei ole täpselt grupi lõpus. Selleks oli meil TreeMap (võiks olla ka HashMap)
            int[] maja1_grupi_andmed = (int[])korgus_grupid_maps.get(maja_grupp1);
            int[] maja2_grupi_andmed = (int[])korgus_grupid_maps.get(maja_grupp2);
            // Grupi Lõpust arvutades
            int maja1_oma_grupis_pikkus = maja1_grupi_andmed[1] - maja1 + 1;
            // Grupi algusests arvutades
            int maja2_oma_grupis_pikkus = maja2 - maja2_grupi_andmed[0] + 1;

            max_kaugus = Math.max(Math.max(maja2_oma_grupis_pikkus, max_kaugus), maja1_oma_grupis_pikkus);
            System.out.println(max_kaugus);
        }
//        System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime));
    }

    private static int otsi_kahendpuust(HashMap<Integer, Integer> gruppide_pikkuste_puu, int tipp, int algus, int lopp, int maja1, int maja2) {

        if(maja1 > maja2 || maja1 > lopp || maja2 < algus)
            return 0;

        if( algus >= maja1 && lopp <= maja2){

            // Vaata teooriat - lk 3, osadele indeksitele väärtust ei looda, sest kahenduspuus ei ole kõikväimalikke harusid
            // siis tuleb tagastada sobiv väärtus Käesoleval juhul 0 pikkus
            Integer pikkus = gruppide_pikkuste_puu.get(tipp);
            return pikkus == null ? 0 : pikkus;
        }


        // Otsi vasakul
        int uus_tipp = tipp * 2;
        int pooleks = (algus + lopp) / 2;
        int vasak_vaartus = otsi_kahendpuust(gruppide_pikkuste_puu, uus_tipp, algus, pooleks, maja1, maja2);
        // Otsi paremalt
        int parem_vaartus = otsi_kahendpuust(gruppide_pikkuste_puu, uus_tipp + 1, pooleks + 1, lopp, maja1, maja2);

        if(vasak_vaartus > parem_vaartus)
            return vasak_vaartus;
        return parem_vaartus;
    }

    private static void loo_kahendpuu(TreeMap<Integer, Object> korgus_grupid_maps, HashMap<Integer, Integer> gruppide_pikkuste_puu, int tipp, int algus, int lopp) {

        if(algus == lopp) { // Oleme jõudnud puu lehele, kus on algandmed otse
            gruppide_pikkuste_puu.put(tipp, ((int[])korgus_grupid_maps.get(algus))[2]);
            return;
        }

        int uus_tipp = tipp * 2;
        int pooleks = (lopp + algus) / 2;
        loo_kahendpuu(korgus_grupid_maps, gruppide_pikkuste_puu, uus_tipp, algus, pooleks);
        loo_kahendpuu(korgus_grupid_maps, gruppide_pikkuste_puu, uus_tipp  + 1, pooleks + 1, lopp);

        gruppide_pikkuste_puu.put(tipp, Math.max(gruppide_pikkuste_puu.get(uus_tipp), gruppide_pikkuste_puu.get(uus_tipp+1)));

    }
}
