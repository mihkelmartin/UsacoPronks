import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

        int[][] tootlikkuse_tabel = new int[vasak_firmasid][parem_firmasid];

        System.out.println("Uhke");
    }
}
