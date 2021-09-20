import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class k5_Aktsiaturg {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int arve = Integer.parseInt(in.readLine());
        StringTokenizer sc = new StringTokenizer(in.readLine());
        int parim_summa = 0;
        int vahesumma = 0;
        for (int i = 0; i < arve; i++) {
            vahesumma += Integer.parseInt(sc.nextToken());
            if(vahesumma > parim_summa)
                parim_summa =vahesumma;
            if(vahesumma < 0)
                vahesumma = 0;
        }
        System.out.println(parim_summa);
    }
}
