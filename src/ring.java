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
        boolean[] kaidud = new boolean[N + 1];
        int vastus = otsi(S, servad, kaidud);
        System.out.println(vastus);

    }

    private static int otsi(int valjak, ArrayList<ArrayList<Integer>> servad, boolean[] kaidud) {
        int vastus = 0;
        kaidud[valjak] = true;

        ArrayList<Integer> valjaku_teed = servad.get(valjak);
        boolean oliLapsi = false;
        for (int i = 0; i < valjaku_teed.size(); i++) {
            int uus_valjak = valjaku_teed.get(i);
            if(!kaidud[uus_valjak]){
                vastus += otsi(uus_valjak, servad, kaidud);
                oliLapsi = true;
            }
        }
        // Järelikult puu tipp
        if(!oliLapsi){
            return 1;
        }
        return vastus;
    }
}
