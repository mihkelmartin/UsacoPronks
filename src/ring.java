import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ring {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int N = Integer.parseInt(sisend[0]);
        int M = Integer.parseInt(sisend[1]);
        int S = Integer.parseInt(sisend[2]);
        ArrayList<ArrayList<Integer>> servad = new ArrayList<>();
        // Loo väljakutele koht kust teid hoida
        // Pane 1 rohkem, et saaks alustada indeksiga 1
        for (int i = 0; i <= N; i++) {
            servad.add(new ArrayList<>());
        }
        // Lisa teed
        for (int i = 0; i < M; i++) {
            sisend = in.readLine().split(" ");
            int s1 = Integer.valueOf(sisend[0]);
            int s2 = Integer.valueOf(sisend[1]);
            servad.get(s1).add(s2);
            servad.get(s2).add(s1);
        }

        // 1 rohkem, et saaks indeksiga 1 alustada
        int[] kaidud = new int[N + 1];
        kaidud[S] = 1;
        int vastus = otsi(S, servad, kaidud, S);
        System.out.println(vastus);

    }

    private static int otsi(int valjak, ArrayList<ArrayList<Integer>> servad, int[] kaidud, int eelmine) {
        int vastus = 0;
        ArrayList<Integer> valjaku_teed = servad.get(valjak);
        boolean bLeiti = false;
        for (int i = 0; i < valjaku_teed.size(); i++) {
            int uus_valjak = valjaku_teed.get(i);
            if(kaidud[uus_valjak] == 0){
                bLeiti = true;
                kaidud[uus_valjak] = 1;
                vastus += otsi(uus_valjak, servad, kaidud, valjak);
            } else if(uus_valjak != eelmine){
                // Ringi lõppu - mis teeme - oleks vaja 2-ga korrutada aga me oleme rekursioonis !!
                // Ära tee midagi ? Et siis ringi teiselpoolt tulija leiab sama asja uuesti ja ongi 2x
                // Aga ei leia, sest meil on 1 pandud

                // See jura *2 ei tööta kuidagi - me oleme ju rekursiooni kuskil otsas
                // Kuna on ring, siis ka ei saada 1 kuidagi kätte -tuleb ära lõpetada ja kuidagi märkida, et ring
                bLeiti = true;
                vastus = vastus * 2;
            }
        }
        if(!bLeiti){
            return 1;
        }
        return vastus;
    }
}
