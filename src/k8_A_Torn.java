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

        // Selle massiivi indeksi i kohal on i kasti korral suurim kandevõime
        // Näiteks kui i = 1, siis algses massiivis parim kandevõime
        int[] parim_alles_kandevoime = new int[max_kandevoime + 1];
        // Alustame 2st ses 0 ja 1 on olemas
        for (int j = 0; j <= kaste; j++) {
            for (int i = 0; i < kaste; i++) {
                int parimast_eelmisest_kandevoimest_kasti_kaal_maha =parim_alles_kandevoime[j-1] - kastid_kandevoimed[i][0];
                int kaesolev_kandevoime = kastid_kandevoimed[i][1];
                // Kasi enda kandevõime võib olla väiksem kui allesjäänust maha lahutatud kaal
                int alles_jaav_kandevoime = Math.min(parimast_eelmisest_kandevoimest_kasti_kaal_maha, kaesolev_kandevoime);
                // Pane suurim
                parim_alles_kandevoime[j] = Math.max(parim_alles_kandevoime[j], alles_jaav_kandevoime);
            }
        }
        System.out.println("");
    }
}
