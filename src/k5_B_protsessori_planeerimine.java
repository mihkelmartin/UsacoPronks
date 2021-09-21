import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k5_B_protsessori_planeerimine {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        String sisend[] = in.readLine().split(" ");
        int m = Integer.parseInt(sisend[0]);
        int n = Integer.parseInt(sisend[1]);
        int t = Integer.parseInt(sisend[2]);

        // Erijuhtumid
        if(t == 0 || (t < m && t < n)){
            System.out.println("0");
            System.exit(0);
        } else {
            int eri_pikkusega_ylesandeid = 1;
            if(m != n ){
                eri_pikkusega_ylesandeid = 2;
            }
            int[] ylesande_kestused = new int[eri_pikkusega_ylesandeid];
            if(m < n){
                ylesande_kestused[0] = m;
                ylesande_kestused[1] = n;
             } else {
                ylesande_kestused[0] = n;
                ylesande_kestused[1] = m;
            }
            int[] ylesandeid = new int[t+1];  // Javas vaikimisi massiivis, mis ongi 0 ylesannet
            for (int i = 1; i <= t; i++) {
                for (int j = 0; j < ylesande_kestused.length; j++) {
                    if(ylesande_kestused[j] >= i &&
                            i%ylesande_kestused[j] == 0 &&
                            ylesandeid[i-ylesande_kestused[j]] + 1 > ylesandeid[i]){
                        ylesandeid[i]++;
                    }
                }
                // Kui ei jagunud võta eelmine, see on parim
                if(ylesandeid[i] == 0) ylesandeid[i] = ylesandeid[i-1];
            }
            System.out.println(ylesandeid[t]);
        }



    }
}
