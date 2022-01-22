import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class k9_C_Kylaskaigud {

    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int maju = Integer.parseInt(sisend[0]);
        int paringuid = Integer.parseInt(sisend[1]);

        int[] maja_korgusgrupp = new int[maju + 1];
        TreeMap<Integer, Object> korgus_grupid_maps = new TreeMap<>();

        int korgus_grupp = 1;
        int korgus_grupi_algus = 1;

        // Loeme esimese maha enne tsüklit, nii on parem korguse muutust jälgida
        // ei pea eraldi if-i tegema esimese muutuse jaoks
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int maja_korgus = Integer.valueOf(st.nextToken()), maja_korgus_eelmine = maja_korgus;
        maja_korgusgrupp[1] = korgus_grupp ; // 1 maja kõrgusgrupp on 1

        for (int i = 2; i <=maju ; i++) {
            maja_korgus = Integer.valueOf(st.nextToken());
            if(maja_korgus != maja_korgus_eelmine){
                maja_korgus_eelmine = maja_korgus;
                loo_korgusgrupp(korgus_grupi_algus, i, korgus_grupp, korgus_grupid_maps);
                korgus_grupp++;
                korgus_grupi_algus = i; // järgmise korgus_grupi algus
            }
            // pane majale kõrgusgrupp
            maja_korgusgrupp[i] = korgus_grupp;
        }
        // Lisa viimane, sest siin muutust ei toimunud
        loo_korgusgrupp(korgus_grupi_algus, maju, korgus_grupp, korgus_grupid_maps);

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
        while (paringuid-- > 0){
            String maja1_maja2[] = in.readLine().split(" ");
            int maja1 = Integer.valueOf(maja1_maja2[0]);
            int maja2 = Integer.valueOf(maja1_maja2[1]);

            int maja_grupp1 = maja_korgusgrupp[maja1];
            int maja_grupp2 = maja_korgusgrupp[maja2];
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

    private static void loo_korgusgrupp(int korgus_grupi_algus, int i, int korgus_grupp, TreeMap<Integer, Object> korgus_grupid_maps) {
        int[] korgus_grupi_andmed = new int[3];
        korgus_grupi_andmed[0] = korgus_grupi_algus;
        korgus_grupi_andmed[1] = i - 1; // lopp, esimene kord ei tule seega -1 ok
        korgus_grupi_andmed[2] = i - korgus_grupi_algus;
        korgus_grupid_maps.put(korgus_grupp, korgus_grupi_andmed);
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
