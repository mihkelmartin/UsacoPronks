import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Valimised {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int d = Integer.parseInt(sisend[0]);
        int v = Integer.parseInt(sisend[1]);
        int n = Integer.parseInt(sisend[2]);

        HashMap<String, Integer> paar = new HashMap<>();
        while (n-- >0){
            String haaletus[] = in.readLine().split(" ");
            String poolt = haaletus[0];
            String vastu = haaletus[1];
            paar.put(poolt+vastu, paar.computeIfAbsent(poolt+vastu, i -> new Integer(0)).intValue() + 1);
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
            String esim_pool = parim_paar.substring(0,2);
            String teine_pool = parim_paar.substring(2,4);
            int vahe_parimaid = 0;
            for (Map.Entry<String, Integer> entry : paar.entrySet()){
                if(esim_pool.equals(entry.getKey().substring(0,2)) || teine_pool.equals(entry.getKey().substring(2,4))){
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
