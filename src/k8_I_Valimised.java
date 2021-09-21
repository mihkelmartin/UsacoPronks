import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class k8_I_Valimised {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int d = Integer.parseInt(sisend[0]);
        int v = Integer.parseInt(sisend[1]);
        int n = Integer.parseInt(sisend[2]);
        HashSet<String> demokraadid = new HashSet<>();
        for (int i = 1; i <=d; i++) {
            demokraadid.add("D"+i);
        }
        HashSet<String> vabariiklased = new HashSet<>();
        for (int i = 1; i <=v; i++) {
            vabariiklased.add("V"+i);
        }

        HashMap<String, Integer> paar = new HashMap<>();
        while (n-- >0){
            String haaletus[] = in.readLine().split(" ");
            String poolt = haaletus[0];
            String vastu = haaletus[1];
            // Nii kokku pannes saama kavalalt vaadata unikaalseid
            paar.put(poolt+";"+vastu, paar.computeIfAbsent(poolt+";"+vastu, i -> new Integer(0)).intValue() + 1);
        }
        int max = 0;
        for (Map.Entry<String, Integer> entry : paar.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
            }
        }
        ArrayList<String> parimad = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : paar.entrySet()){
            if(entry.getValue() == max){
                parimad.add(entry.getKey());
            }
        }

        int max_rahuolevaid = 0;
        for (String parim_paar : parimad){
            int eraldaja = parim_paar.indexOf(";", 1);
            String esim_pool = parim_paar.substring(0,eraldaja);
            String teine_pool = parim_paar.substring(eraldaja +1 );
            int vahe_parimaid = 0;

            HashSet<String> puutumata_vabariiklased = new HashSet<>();
            puutumata_vabariiklased.addAll(vabariiklased);
            HashSet<String> puutumata_demokraadid = new HashSet<>();
            puutumata_demokraadid.addAll(demokraadid);
            puutumata_demokraadid.remove(teine_pool);
            puutumata_vabariiklased.remove(teine_pool);

            for (Map.Entry<String, Integer> entry : paar.entrySet()){
                int entry_eraldaja = entry.getKey().indexOf(";", 1);
                String entry_esim_pool = entry.getKey().substring(0,entry_eraldaja);
                String entry_teine_pool = entry.getKey().substring(entry_eraldaja + 1);

                if(esim_pool.equals(entry_esim_pool) || teine_pool.equals(entry_teine_pool)
                        || puutumata_demokraadid.contains(entry_esim_pool)
                        || puutumata_vabariiklased.contains(entry_esim_pool)){
                    vahe_parimaid += entry.getValue();
                }
            }
            if(vahe_parimaid > max_rahuolevaid){
                max_rahuolevaid = vahe_parimaid;
            }
        }
        System.out.println(max_rahuolevaid);

    }
}
