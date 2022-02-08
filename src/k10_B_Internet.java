import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class k10_B_Internet {
    public static boolean on_tsykkel(int tipp, int eelmine, ArrayList<ArrayList<Integer>> graaf, int m, boolean[] kaidud) {
        kaidud[tipp] = true;
        for (int i = 0; i < graaf.get(tipp).size(); i++) {
            int naaber = graaf.get(tipp).get(i);
            if (!kaidud[naaber]) {
                if (on_tsykkel(naaber, tipp, graaf, m, kaidud))
                    return true;
            }
            else if (naaber != eelmine)
                return true;
        }
        return false;
    }

    public static void leiaPikkus(ArrayList<Serv> servad, ArrayList<ArrayList<Integer>> graaf, int m)
    {
        int servi_puus = 0;
        int jrgm_serv = 0;
        int vastus = 0;
        int otsekaid = 0;
        while (servi_puus < m - 1) // puus on m-1 serva
        {
            int kaugus = servad.get(jrgm_serv).kaal;
            int yks = servad.get(jrgm_serv).ots_1, kaks = servad.get(jrgm_serv).ots_2;
            graaf.get(yks).add(kaks);
            graaf.get(kaks).add(yks);
            //kui ei teki tsüklit, lisame serva puusse
            boolean[] kaidud = new boolean[m];
            if (!on_tsykkel(yks, -1, graaf, m, kaidud)) {
                vastus += kaugus;
                if(yks == m - 1 || kaks == m - 1){
                    otsekaid++;
                }
                servi_puus++;
            }
            else {
                Integer b = yks;
                Integer c = kaks;
                graaf.get(yks).remove(c);
                graaf.get(kaks).remove(b);
            }
            jrgm_serv++;
        }
        System.out.println(vastus + " " + otsekaid);
    }

    public static class Serv implements Comparable<Serv>{
        int kaal;
        int ots_1;
        int ots_2;
        public Serv(int ots_1, int ots_2, int kaal) {
            this.kaal = kaal;
            this.ots_1 = Math.min(ots_1, ots_2);// esimene väiksem
            this.ots_2 = Math.max(ots_2, ots_1);// teine suurem
            if(ots_1 == ots_2) System.out.println(ots_1 + " " + ots_2 + " " + kaal);
        }

        @Override
        public int compareTo(Serv teine) {
            if(kaal == teine.kaal){
                return Integer.compare(teine.ots_2, ots_2);
            }
            else{
                return Double.compare(kaal, teine.kaal);
            }
        }

        @Override
        public boolean equals(Object o) {
            return this.ots_1 == ((Serv)o).ots_1 && this.ots_2 == ((Serv)o).ots_2;
        }
    }
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int mitu_kooli = Integer.parseInt(st.nextToken());
        int teid = Integer.parseInt(st.nextToken());
        int otseyhendus = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graaf = new ArrayList<>();
        for (int i = 0; i < mitu_kooli + 1; i++) {
            graaf.add(new ArrayList<>());
        }
        ArrayList<Serv> servad = new ArrayList<>();
        for (int i = 0; i < teid; i++) {
            StringTokenizer sa = new StringTokenizer(in.readLine());
            int yks = Integer.parseInt(sa.nextToken()) - 1;
            int kaks = Integer.parseInt(sa.nextToken()) - 1;
            int kaal = Integer.parseInt(sa.nextToken());
            Serv serv = new Serv(yks, kaks, kaal);
            if(servad.contains(serv)) {
                int indeks = servad.indexOf(serv);
                servad.get(indeks).kaal = Math.min(kaal, servad.get(indeks).kaal);
            }else {
                if(yks != kaks){
                    servad.add(serv);
                }
            }
        }
        mitu_kooli++;
        for (int i = 0; i < mitu_kooli - 1; i++) {
            Serv serv = new Serv(mitu_kooli - 1, i, otseyhendus);
            servad.add(serv);
        }
        Collections.sort(servad);
        leiaPikkus(servad, graaf, mitu_kooli);
    }
}
