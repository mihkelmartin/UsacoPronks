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

        // 1 rohkem, et seal oleks 0 äärtes valmis siis saab arvutusvalemid teha Math.max kasutades
        int[][] tootlikkuse_tabel = new int[vasak_firmasid + 1][parem_firmasid + 1];

        // Rekursiooni korral hakkaks tulema alt otsast vasakult poolt, pannes vastavusse paremat poolt alt otsast alates
        // Nii saame ülespoole liikudes maksimaalselt võimalikku väärtust eelmise põhjal lihtsalt vaadata
        // Dünaamilist teeme alt-üles, seega vasakult alt hakkame tulema ja vaatame kuidas paremal on sama usku
        // firmasid ja täidame tabelit. Firma nimi ei ole tegelikult oluline
        // Alusta eelviimasest reast, sest viimane on meil selleks, et oleks 0

        // jätame meelde koha kus parim väärtus saada parim_j ja parima_i
        int vastus = 0, parima_i = vasak_firmasid, parim_j = parem_firmasid;
        for (int i = vasak_firmasid - 1; i >= 0; i--) {
            for (int j = parem_firmasid - 1; j >= 0; j--) {
                Firma vasak = vasakfirmad.get(i);
                Firma parem = paremfirmad.get(j);
                if (vasak.usk.equals(parem.usk)) {
                    tootlikkuse_tabel[i][j] =
                            Math.max(
                                    // Liida kokku ja lisa eelmise taseme tootlikkus. i+1 on eelmine vasak ja
                                    // j+1 eelmine parem --> sest sillad ei tohi lõikuda
                                    vasak.tootlikkus + parem.tootlikkus + tootlikkuse_tabel[i + 1][j + 1],
                                    // Ülal tehtud liitmine ei pruugi olukorda parandada, seega on need siin all, siis
                                    // jätame eelmise parema tulemuse alles
                                    Math.max(tootlikkuse_tabel[i][j + 1], tootlikkuse_tabel[i + 1][j]));
                    // Jäta parim vastus ja selle indeksid meelde
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
            if(tootlikkuse_tabel[parima_i+1][parim_j] == vahepealne_parim){
                parima_i++;
            } else if(tootlikkuse_tabel[parima_i][parim_j+1] == vahepealne_parim){
                parim_j++;
            } else {
                // Oleme nurgas kust järgneb muutus
                if(vasakfirmad.get(parima_i).tootlikkus +
                        paremfirmad.get(parim_j).tootlikkus +
                        tootlikkuse_tabel[parima_i+1][parim_j+1] >
                        tootlikkuse_tabel[parima_i][parim_j+1]){
                    parim_j++;
                    parima_i++;
                } else {
                    parim_j++;
                }
                sildu++;
            }
            vahepealne_parim = tootlikkuse_tabel[parima_i][parim_j];
        }
        System.out.println(vastus + " " + sildu);
    }
}
