import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class k8_C_Sillad {

    private static class Firma {
        private String nimi;
        private String usk;
        private int tootlikkus;

        public Firma (String nimi, String usk, int tootlikkus){
            this.nimi = nimi;
            this.usk = usk;
            this.tootlikkus = tootlikkus;
        }
    }

    public static void main(String[] args) throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        int vasak_firmasid = Integer.parseInt(in.readLine());
        ArrayList<Firma> vasakfirmad = new ArrayList<>();
        for (int i = 0; i < vasak_firmasid; i++) {
            String firma[] = in.readLine().split(" ");
            vasakfirmad.add(new Firma(firma[0], firma[1], Integer.valueOf(firma[2])));
        }

        int parem_firmasid = Integer.parseInt(in.readLine());
        ArrayList<Firma> paremfirmad = new ArrayList<>();
        for (int i = 0; i < parem_firmasid; i++) {
            String firma[] = in.readLine().split(" ");
            paremfirmad.add(new Firma(firma[0], firma[1], Integer.valueOf(firma[2])));
        }

        // 1 rohkem, et seal oleks 0 valmis
        int[][] tootlikkuse_tabel = new int[vasak_firmasid + 1][parem_firmasid + 1];

        // Rekursiooni korral hakkaks tulema ülevalt otsast vasakult poolt, pannes vastavusse paremat poolt
        // Dünaamilist teeme alt-üles, seega vasakult alt hakkame tulema ja vaatame kuidas paremal on sama usku
        // firmasid ja täidame tabelit. Firma nimi ei ole tegelikult oluline
        // Alusta eelviimasest, sest viimane on meil selleks, et oleks 0
        int vastus = 0, parima_i = vasak_firmasid, parim_j = parem_firmasid;
        for (int i = vasak_firmasid - 1; i >= 0; i--) {
            for (int j = parem_firmasid - 1; j >= 0; j--) {
                Firma vasak = vasakfirmad.get(i);
                Firma parem = paremfirmad.get(j);
                if (vasak.usk.equals(parem.usk)) {
                    tootlikkuse_tabel[i][j] =
                            // Kui eelmine suurem võta eelmine
                            Math.max(vasak.tootlikkus + parem.tootlikkus + tootlikkuse_tabel[i + 1][j + 1],
                                    // kui pole suurem siis võib alusmisega jätkata alati
                                    tootlikkuse_tabel[i][j + 1]);
                    if(tootlikkuse_tabel[i][j] > vastus){
                        vastus = tootlikkuse_tabel[i][j];
                        parima_i = i; parim_j = j;
                    }
                } else {
                    // Kui pole   mätsi võta enda eelmine või eelmise eelmine kui see suurem
                    tootlikkuse_tabel[i][j] = Math.max(tootlikkuse_tabel[i][j+1], tootlikkuse_tabel[i+1][j]);
                }
            }
        }

        // Üritame nüüd tagurpidi minnes aru saada mitu silda oli vaja
        int vahepealne_parim = vastus;
        int sildu = 0;
        while (vahepealne_parim > 0) {
            // Kõik variandid
            if(tootlikkuse_tabel[parima_i][parim_j+1] == vahepealne_parim && tootlikkuse_tabel[parima_i+1][parim_j] != vahepealne_parim){
                parim_j++;
            } else if(tootlikkuse_tabel[parima_i][parim_j+1] != vahepealne_parim && tootlikkuse_tabel[parima_i+1][parim_j] == vahepealne_parim){
                parima_i++;
            } else if(tootlikkuse_tabel[parima_i][parim_j+1] != vahepealne_parim && tootlikkuse_tabel[parima_i+1][parim_j] != vahepealne_parim){
                if(vahepealne_parim - tootlikkuse_tabel[parima_i][parim_j+1] > vahepealne_parim - tootlikkuse_tabel[parima_i+1][parim_j]){
                    parim_j++;
                } else {
                    parima_i++;
                }
            } else if(tootlikkuse_tabel[parima_i][parim_j+1] == vahepealne_parim && tootlikkuse_tabel[parima_i+1][parim_j] == vahepealne_parim){
                parima_i++;parim_j++;
            }
            if(tootlikkuse_tabel[parima_i][parim_j] != vahepealne_parim){
                vahepealne_parim = tootlikkuse_tabel[parima_i][parim_j];
                sildu++;
            }
        }
        System.out.println(vastus + " " + sildu);
    }
}
