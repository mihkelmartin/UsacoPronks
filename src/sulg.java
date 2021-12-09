import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sulg {

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int n = Integer.parseInt(sisend[0]), qc = Integer.parseInt(sisend[1]);
        String sone = in.readLine();
        for (int i = 0; i < qc; i++) {
            sisend = in.readLine().split(" ");
            if(solve(sone, Integer.parseInt(sisend[0])-1, Integer.parseInt(sisend[1])))
                System.out.println("JAH");
            else
                System.out.println("EI");
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

