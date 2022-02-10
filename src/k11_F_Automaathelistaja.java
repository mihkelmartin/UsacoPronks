import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

public class k11_F_Automaathelistaja {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int numbreid = Integer.valueOf(in.readLine());
        TreeSet<String>  numbrid = new TreeSet<>();
        for (int i = 0; i < numbreid; i++) {
            numbrid.add(in.readLine());
        }
        Iterator<String> iter = numbrid.iterator();
        String eelmine = iter.next();
        while (iter.hasNext()){
            String praegune = iter.next();
            if(praegune.substring(0, Math.min(praegune.length(), eelmine.length())).equals(eelmine)) {
                System.out.println("EI");
                System.exit(0);
            }
            eelmine = praegune;
        }
        System.out.println("JAH");
    }
}
