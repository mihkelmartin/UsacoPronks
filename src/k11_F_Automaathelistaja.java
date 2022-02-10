import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class k11_F_Automaathelistaja {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int numbreid = Integer.valueOf(in.readLine());
        // TreeSet hoiab KOHE sorteeritult kasvavas järjekorras
        // Teine variant oleks ArrayListi panna ja siis lõpus sorteerida
        TreeSet<String>  numbrid = new TreeSet<>();
        for (int i = 0; i < numbreid; i++) {
            numbrid.add(in.readLine());
        }

        String eelmine = "X";
        for (String praegune : numbrid) {
            if(praegune.substring(0, Math.min(praegune.length(), eelmine.length())).equals(eelmine)) {
                System.out.println("EI");
                System.exit(0);
            }
            eelmine = praegune;
        }
        System.out.println("JAH");
    }
}
