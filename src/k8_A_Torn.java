import java.io.BufferedReader;
import java.io.InputStreamReader;


public class k8_A_Torn {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int kaste = Integer.parseInt(in.readLine());

        int[][] kastid_kandevoimed = new int[kaste][2];
        int max_kandevoime = 0;
        for (int i = 0; i < kaste; i++) {
            String sisend[] = in.readLine().split(" ");
            int kaal = Integer.parseInt(sisend[0]);
            int kandevoime = Integer.parseInt(sisend[1]);
            kastid_kandevoimed[i][0] = kaal;
            kastid_kandevoimed[i][1] = kandevoime;
            // Selle abil saame teha sobiva massiivi
            max_kandevoime = kandevoime > max_kandevoime ? kandevoime : max_kandevoime;
        }


        // i-sse summerrime i kandevõime maximaalset kastide arvu
        int[] kandevoime_kaste = new int[max_kandevoime + 1];
        // Käime üle kõikide kastide - pane tähele, et iga kasti vaid 1 kord, seetõttu, et ei tohtinud suurema numbriga vaiksema peale panna
        int vastus = 0;
        for (int j = 0; j < kaste; j++) {
            // Kui vastavat kandevõimet pole pane 1
            if(kandevoime_kaste[kastid_kandevoimed[j][1]] == 0)
                kandevoime_kaste[kastid_kandevoimed[j][1]] = 1;
            // Leia kandevõimed mille peale sobib kaalu järgi
            for (int i = max_kandevoime; i >= kastid_kandevoimed[j][0]; i--) {
                // Kandevõime peab olemas  olema
                if(kandevoime_kaste[i] != 0){
                    int uus_kandevoime = Math.min(i - kastid_kandevoimed[j][0], kastid_kandevoimed[j][1]);
                    kandevoime_kaste[uus_kandevoime] = Math.max(kandevoime_kaste[i] + 1, kandevoime_kaste[uus_kandevoime]);
                    vastus = Math.max(vastus, kandevoime_kaste[uus_kandevoime]);
                }
            }
        }
        System.out.println(vastus);
    }
}
