
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
        avaldis = arvuta_sulgudeta_avaldis(avaldis);
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
        String retVal = avaldis;
        retVal = tee_tehted(retVal, '*', '/');
        retVal = tee_tehted(retVal, '+', '-');
        return retVal;
    }

    private static String tee_tehted(String avaldis, char tehe1, char tehe2){
        String retVal = avaldis;
        // Korrutamised / Jagamised
        boolean leiti = true;
        while(leiti) {
            leiti = false;
            for (int i = 0; i < retVal.length(); i++) {
                if (retVal.charAt(i) == tehe1 || retVal.charAt(i) == tehe2) {
                    int vasak_arv = leia_vasak_arv(retVal, i);
                    int parem_arv = leia_parem_arv(retVal, i);
                    String vasak_string = leia_vasak_string(retVal, i);
                    String parem_string = leia_parem_string(retVal, i);
                    int tulemus = retVal.charAt(i) == '*' ? vasak_arv * parem_arv :
                            retVal.charAt(i) == '/' ? vasak_arv / parem_arv :
                                    retVal.charAt(i) == '+' ? vasak_arv + parem_arv : vasak_arv - parem_arv;
                    retVal = vasak_string + tulemus + parem_string;
                    leiti = true;
                    break;
                }
            }
        }
        return retVal;
    }

    private static String leia_parem_string(String avaldis, int i) {
        int pos = i + 1;
        while (avaldis.length() > pos && Character.isDigit(avaldis.charAt(pos))){
            pos++;
        }
        return avaldis.substring(pos);
    }

    private static String leia_vasak_string(String avaldis, int i) {
        int pos = i - 1;
        while (pos >= 0 && Character.isDigit(avaldis.charAt(pos))){
            pos--;
        }
        return avaldis.substring(0, pos + 1);
    }

    private static int leia_parem_arv(String avaldis, int i) {
        int pos = i + 1;
        while (avaldis.length() > pos && Character.isDigit(avaldis.charAt(pos))){
            pos++;
        }
        return Integer.parseInt(avaldis.substring(i + 1, pos));
    }

    private static int leia_vasak_arv(String avaldis, int i) {
        int pos = i - 1;
        while (pos >= 0 && Character.isDigit(avaldis.charAt(pos))){
            pos--;
        }
        return Integer.parseInt(avaldis.substring(pos + 1, i));
    }


}
