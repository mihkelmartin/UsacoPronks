import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class k8_B_Bussireisid {

    private static class Tee{
        private int algus_peatus;
        private int lopp_peatus;
        private int soidu_kulu;

        public Tee(int algus_peatus, int lopp_peatus, int soidu_kulu) {
            this.algus_peatus = algus_peatus;
            this.lopp_peatus = lopp_peatus;
            this.soidu_kulu = soidu_kulu;
        }
    }
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int n = Integer.parseInt(sisend[0]);
        int m = Integer.parseInt(sisend[1]);

        ArrayList<Tee> teed = new ArrayList<>();
        for (int i = 0; i < m ; i++) {
            sisend = in.readLine().split(" ");
            int algus = Integer.parseInt(sisend[0]);
            int lopp = Integer.parseInt(sisend[1]);
            // Algandmed võivad olla nõmedad
            // Jätta välja kui algus ja lõpp on sama
            if(algus != lopp) {
                // Vaheta järjekord kui lõpp on väiksem kui algus, kuna teed on kahesuunalised siis nii
                // võib ja meie tabeli-täitmise-loogika toimib nii, et on õiges järjekorras
                if(algus < lopp)
                   teed.add(new Tee(algus, lopp, Integer.parseInt(sisend[2])));
                else
                    teed.add(new Tee(lopp, algus, Integer.parseInt(sisend[2])));
            }
        }
        int p = Integer.valueOf(in.readLine());
        int[] peatuse_tulu = new int[n + 1];
        for (int i = 0; i < p; i++) {
            sisend = in.readLine().split(" ");
            peatuse_tulu[Integer.parseInt(sisend[0])] = Math.max(peatuse_tulu[Integer.parseInt(sisend[0])],
                    Integer.parseInt(sisend[1]));
        }

        // Esimene rida edasisõit, teine rida tagasisõit.
        // kuidas siis kui peatust polegi - kas 0, või MIN, vaatame
        int[][] soidu_tulu_peatuses = new int[2][n + 1];
        // Algväärtusta miinimumi peale
        soidu_tulu_peatuses[0][0] = 0;
        soidu_tulu_peatuses[1][0] = 0;

        // Liigume üle peatuste
        int vastus = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            soidu_tulu_peatuses[0][i] = Integer.MIN_VALUE;
            soidu_tulu_peatuses[1][i] = Integer.MIN_VALUE;
            for(Tee tee : teed){
                // Vaatame neid teid mis lõpevad käesolevas peatuses - ja leiame kõige tulusama
                if(tee.lopp_peatus == i){
                    // Alguspeatuses on meil juba arvestatud parim hind sinnani
                    int kokku_edasi_tulu = soidu_tulu_peatuses[0][tee.algus_peatus] + peatuse_tulu[tee.lopp_peatus] - tee.soidu_kulu;
                    soidu_tulu_peatuses[0][i] = Math.max(soidu_tulu_peatuses[0][i], kokku_edasi_tulu);
                    int kokku_tagasi_tulu = soidu_tulu_peatuses[1][tee.algus_peatus] - tee.soidu_kulu;
                    soidu_tulu_peatuses[1][i] = Math.max(soidu_tulu_peatuses[1][i], kokku_tagasi_tulu);
                    vastus = Math.max(vastus, kokku_tagasi_tulu + kokku_edasi_tulu);
                }
            }
        }
        System.out.println(vastus >= 0 ? vastus : 0);
    }
}
