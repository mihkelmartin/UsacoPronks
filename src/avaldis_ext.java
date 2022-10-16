
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class avaldis_ext {

    static class Tehe {
        private int argument1;
        private int argument2;
        String tehe;
        private int cumulativeOR = 0;
        private int cumulativeAND = ~cumulativeOR;

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
        Tehe teheviiman = new Tehe();
        teheviiman.cumulativeOR = 0;
        teheviiman.cumulativeAND = ~teheviiman.cumulativeOR;
        tehted.add(teheviiman);
        for (int i = tehteid - 1; i >= 0 ; i--) {
            Tehe tehe = tehted.get(i);
            if(tehe.tehe.equals("OR")){
                tehe.cumulativeAND = tehted.get(i+1).cumulativeAND;
                tehe.cumulativeOR = tehted.get(i+1).cumulativeOR | tehe.argument2;
            }
            if(tehe.tehe.equals("AND")){
                tehe.cumulativeAND = tehted.get(i+1).cumulativeAND & tehe.argument2;
                tehe.cumulativeOR |= (tehe.cumulativeAND | tehted.get(i+1).cumulativeOR);
            }
        }


        for(int i = 0; i < muutusi; i++){
            String[] muutusStr = in.readLine().split(" ");
            int pos = Integer.parseInt(muutusStr[0]);
            tehted.get(pos-1).tehe = muutusStr[1];
            tehted.get(pos-1).argument2 = Integer.parseInt(muutusStr[2]);
            int eelmineuus = tehted.get(pos-1).argument1;
            Tehe teheuus = tehted.get(pos - 1);
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
            System.out.println(eelmineuus);
        }
    }
}
