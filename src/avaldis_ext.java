
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class avaldis_ext {

    static class Tehe {
        private int argument1;
        private int argument2;
        String tehe;
        private int cumulativeAND = -1;
        private int cumulativeOR = -1;
    }
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        String[] arvud = in.readLine().split(" ");
        int tehteid = Integer.parseInt(arvud[0]);
        int muutusi = Integer.parseInt(arvud[1]);


        ArrayList<Tehe> tehted = new ArrayList<>();
        int eelmine = 0;
        for(int i = 0; i < tehteid; i++){
            String[] teheStr = in.readLine().split(" ");
            Tehe tehe = new Tehe();
            tehe.argument1 = eelmine;
            tehe.argument2 = Integer.parseInt(teheStr[1]);
            tehe.tehe = teheStr[0];
            if(tehe.tehe.equals("AND")){
                eelmine = tehe.argument1 & tehe.argument2;
            }
            if(tehe.tehe.equals("XOR")){
                eelmine = tehe.argument1 ^  tehe.argument2;
            }
            if(tehe.tehe.equals("OR")){
                eelmine = tehe.argument1 | tehe.argument2;
            }
            tehted.add(tehe);
        }
        for (int i = tehteid - 1; i >= 0 ; i--) {
            Tehe tehe = tehted.get(i);
            if(tehe.tehe.equals("OR")){
                if(tehe.cumulativeOR == -1){
                    tehe.cumulativeOR = tehe.argument2;
                } else {
                    tehe.cumulativeOR |= tehe.argument2;
                }
                tehe.cumulativeAND = tehted.get(Math.min(i+1, tehted.size()-1)).cumulativeAND;
            }
            if(tehe.tehe.equals("AND")){
                if(tehe.cumulativeAND == -1){
                    tehe.cumulativeAND = tehe.argument2;
                } else {
                    tehe.cumulativeAND &= tehe.argument2;
                    tehe.cumulativeOR |= (tehe.cumulativeAND | tehe.cumulativeOR);
                }
            }

        }


        for(int i = 0; i < muutusi; i++){
            String[] muutusStr = in.readLine().split(" ");
            int pos = Integer.parseInt(muutusStr[0]);
            tehted.get(pos-1).tehe = muutusStr[1];
            tehted.get(pos-1).argument2 = Integer.parseInt(muutusStr[2]);
            int eelmineuus = tehted.get(pos-1).argument1;
            for(int j = pos-1; j < tehted.size(); j++){
                Tehe teheuus = tehted.get(j);
                teheuus.argument1 = eelmineuus;
                if(teheuus.tehe.equals("AND")){
                    eelmineuus = teheuus.argument1 & teheuus.argument2;
                }
                if(teheuus.tehe.equals("XOR")){
                    eelmineuus = teheuus.argument1 ^  teheuus.argument2;
                }
                if(teheuus.tehe.equals("OR")){
                    eelmineuus = teheuus.argument1 | teheuus.argument2;
                }
            }
            System.out.println(eelmineuus);
        }
    }
}
