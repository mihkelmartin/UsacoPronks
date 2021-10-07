import jdk.internal.org.objectweb.asm.tree.analysis.BasicValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class kalk {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String avaldis = in.readLine();
        while (avaldis.indexOf(")") >=0){
            avaldis = arvuta_sulud(avaldis);
        }
        //avaldis = arvuta_sulgudeta_avaldis(avaldis);
        System.out.println(avaldis);
    }

    private static String arvuta_sulud(String avaldis){
        String retVal = avaldis;
        int parem_sulg = avaldis.indexOf(")");
        int vasak_sulg = parem_sulg;
        do {
            vasak_sulg--;
        } while (avaldis.charAt(vasak_sulg) != '(');

        String sulgudeta_avaldis = avaldis.substring(vasak_sulg + 1, parem_sulg);
        sulgudeta_avaldis = arvuta_sulgudeta_avaldis(sulgudeta_avaldis);

        retVal = avaldis.substring(0, vasak_sulg) + sulgudeta_avaldis + avaldis.substring(parem_sulg +1, avaldis.length());
        return retVal;
    }
    private static String arvuta_sulgudeta_avaldis(String avaldis){
        String retVal = "proov";
        // Korrutamised / Jagamised
        for (int i = 0; i < avaldis.length(); i++) {
            if(avaldis.charAt(i) == '*' || avaldis.charAt(i) == '/'){
                int vasak_arv = leia_vasak_arv(avaldis, i);
                int parem_arv = leia_parem_arv(avaldis, i);
                String vasak_string = leia_vasak_string(avaldis, i);
                String parem_string = leia_parem_string(avaldis, i);
            }
        }
        return retVal;
    }


}
