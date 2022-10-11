
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class tsirkus {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        String[] arvud = in.readLine().split(" ");
        int lauasuurus = Integer.parseInt(arvud[0]);
        int N = Integer.parseInt(arvud[1]);

        HashMap<Integer, Integer> redelid = new HashMap<>();
        for(int i = 0; i < N; i++){
            String[] redelStr = in.readLine().split(" ");
            redelid.put(Integer.parseInt(redelStr[0]),Integer.parseInt(redelStr[1]));
        }

        ArrayList<Integer> eelmisel_korral_kaidud = new ArrayList<>();
        eelmisel_korral_kaidud.add(1);
        int mitmes_kord = 0;
        boolean[] kas_kaidud = new boolean[lauasuurus+1];
        boolean kas_muutus = false;
        int uus = 0;
        while(true){
            HashSet<Integer> praegu_kaidud = new HashSet<>();
            kas_muutus = false;
            mitmes_kord++;
            uus= 0 ;
            for(Integer eelmine_kord : eelmisel_korral_kaidud){
                for(int kaik = 1; kaik <= 6; kaik++){
                    uus = eelmine_kord + kaik;
                    Integer redeli_kaik = redelid.get(uus);
                    if(redeli_kaik != null){
                        uus = redeli_kaik;
                    }
                    if(!kas_kaidud[Math.min(uus, lauasuurus)]){
                        kas_kaidud[uus] = true;
                        kas_muutus = true;
                        praegu_kaidud.add(uus);
                    }
                    if(uus >= lauasuurus)
                        break;
                }
            }
            if(!kas_muutus || uus >= lauasuurus)
                break;
            eelmisel_korral_kaidud.clear();
            eelmisel_korral_kaidud.addAll(praegu_kaidud);
        }
        if(mitmes_kord > lauasuurus || uus < lauasuurus){
            System.out.println("EI SAA");
        } else {
            System.out.println(mitmes_kord);
        }
    }
}
