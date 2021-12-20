import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sulg {

    private static int sonePikkus;
    private static int[] puu;
    private static int[] lfend;
    private static int[] rgend;
    private static int puuSuurus = 1;

    public static void main(String[] args )throws Exception {
        long startTime = System.nanoTime();

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");

        long duration = (System.nanoTime() - startTime) / 1000000; // to get milliseconds.
        System.out.println(duration);


        sonePikkus = Integer.parseInt(sisend[0]);
        int qc = Integer.parseInt(sisend[1]);
        String sone = in.readLine();

        while (puuSuurus < sonePikkus){
            puuSuurus *= 2;
        }
        puu = new int[puuSuurus * 2];
        lfend = new int[puuSuurus * 2];
        rgend = new int[puuSuurus * 2];
        for (int i = puuSuurus; i < 2 * puuSuurus; i++) {
            lfend[i] = i - puuSuurus;
            rgend[i] = i - puuSuurus;
        }

        for (int i = puuSuurus - 1; i > 0; i--) {
            lfend[i] = lfend[2 * i];
            rgend[i] = rgend[2 * i + 1];
        }

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
            int l = Integer.parseInt(sisend[0]);
            int r = Integer.parseInt(sisend[1]);
            if (balance[l - 1] != balance[r]) {
                System.out.println("EI");
            } else if (query(l, r - 1, 1) == balance[l - 1]) {
                System.out.println("JAH");
            } else {
                System.out.println("EI");
            }
        }
        long endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // to get milliseconds.
        System.out.println(duration);

    }

    public static void genereeriPuu(int positsioon, int vaartus){
        // Keskele - 1 esimene kord
        puu[puuSuurus + positsioon] = vaartus;
        for (int u = (puuSuurus + positsioon) / 2; u != 0; u /= 2) {
            puu[u] = Math.min(puu[2 * u], puu[2 * u + 1]);
        }
    }

    public static  int query (int ql, int qr, int u) {
        ql = Math.max(ql, lfend[u]);
        qr = Math.min(qr, rgend[u]);

        if (ql > qr) return Integer.MAX_VALUE;
        if (ql == lfend[u] && qr == rgend[u]) return puu[u];
        return Math.min(query(ql, qr, 2 * u), query(ql, qr, 2 * u + 1));
    }

}

