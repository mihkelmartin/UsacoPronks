import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sulg {

    private static int sonePikkus;
    private static int[] puu;
    private static int puuSuurus = 1;

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        sonePikkus = Integer.parseInt(sisend[0]);
        int qc = Integer.parseInt(sisend[1]);
        String sone = in.readLine();

        while (puuSuurus < sonePikkus){
            puuSuurus *= 2;
        }
        puu = new int[puuSuurus * 2];
        for (int i = 0; i < puuSuurus * 2; i++) {
            puu[i] = Integer.MAX_VALUE;
        }

        int[] balance  = new int[sonePikkus + 1];
        for (int i = 1; i <= sonePikkus; i++) {
            if (sone.charAt(i - 1) == '(') {
                balance[i] = 1 + balance[i - 1];
            } else {
                balance[i] = -1 + balance[i - 1];
            }
        }
        for (int i = 0; i <= sonePikkus; i++) {
            genereeriPuu(i, balance[i]);
        }

        for (int i = 0; i < qc; i++) {
            sisend = in.readLine().split(" ");
            if(solve(sone, Integer.parseInt(sisend[0])-1, Integer.parseInt(sisend[1])))
                System.out.println("JAH");
            else
                System.out.println("EI");
        }
    }

    public static void genereeriPuu(int positsioon, int vaartus){
        // Keskele - 1 esimene kord
        puu[puuSuurus + positsioon] = vaartus;
        for (int u = (puuSuurus + positsioon) / 2; u != 0; u /= 2) {
            puu[u] = Math.min(puu[2 * u], puu[2 * u + 1]);
        }
    }

    private static boolean solve (String s, int l, int r) {
        int lptr = l, rptr = r - 1;
        int bal = 0, rbal = 0;
        boolean seen0 = false;
        while (lptr <= rptr) {
            if (s.charAt(lptr) == '(') bal++;
            else bal--;
            if (bal < 0) return false;
            if (bal == 0 && lptr != r - 1) seen0 = true;

            if (lptr == rptr) break;

            if (s.charAt(rptr) == '(') rbal++;
            else rbal--;
            if (rbal > 0) return false;
            if (rbal == 0) seen0 = true;

            lptr++;
            rptr--;
        }
        return seen0 && bal + rbal == 0;
    }
}

