import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class k10_B_Internet_Compact {

    public static class Serv {
        int kaal;
        int ots;
        public Serv(int ots, int kaal) {
            this.kaal = kaal;
            this.ots = ots;
        }
    }
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int mitu_kooli = Integer.parseInt(st.nextToken());
        int teid = Integer.parseInt(st.nextToken());
        int otseyhendus = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Serv>> graaf_kaaluga = new ArrayList<>();
        for (int i = 0; i < mitu_kooli + 1; i++) {
            graaf_kaaluga.add(new ArrayList<>());
        }
        for (int i = 0; i < teid; i++) {
            StringTokenizer sa = new StringTokenizer(in.readLine());
            int yks = Integer.parseInt(sa.nextToken()) - 1;
            int kaks = Integer.parseInt(sa.nextToken()) - 1;
            int kaal = Integer.parseInt(sa.nextToken());
            graaf_kaaluga.get(yks).add(new Serv(kaks, kaal));
            graaf_kaaluga.get(kaks).add(new Serv(yks, kaal));
        }
        for (int i = 0; i < mitu_kooli; i++) {
            graaf_kaaluga.get(mitu_kooli).add(new Serv(i, otseyhendus));
            graaf_kaaluga.get(i).add(new Serv(mitu_kooli, otseyhendus));
        }

        HashSet<Integer> kasutatud_tipud = new HashSet<>();
        HashMap<Integer, Serv> punktid_kasutuseks_servaga = new HashMap<>();
        punktid_kasutuseks_servaga.put(0, new Serv(0,0));
        int vastus = 0, otse = 0;

        while (kasutatud_tipud.size() < mitu_kooli + 1){
            int parim_punkt = -1;
            Serv parim_serv = new Serv(0, Integer.MAX_VALUE);
            for (Map.Entry<Integer, Serv> punkt_kasutuseks : punktid_kasutuseks_servaga.entrySet()) {
                if(punkt_kasutuseks.getValue().kaal < parim_serv.kaal){
                    parim_punkt = punkt_kasutuseks.getKey();
                    parim_serv = punkt_kasutuseks.getValue();
                }
            }
            for( Serv serv : graaf_kaaluga.get(parim_punkt) ){
                if(!kasutatud_tipud.contains(serv.ots)){
                    Serv eelmine_serv = punktid_kasutuseks_servaga.computeIfAbsent(serv.ots, e -> new Serv(0, Integer.MAX_VALUE));
                    serv = serv.kaal < eelmine_serv.kaal ? serv : eelmine_serv;
                    punktid_kasutuseks_servaga.put(serv.ots, serv);
                }
            }
            vastus += parim_serv.kaal;
            otse = graaf_kaaluga.get(mitu_kooli).contains(parim_serv) || parim_punkt == mitu_kooli ? otse + 1 : otse;
            punktid_kasutuseks_servaga.remove(parim_punkt);
            kasutatud_tipud.add(parim_punkt);
        }
        System.out.println(vastus + " " + otse);
    }
}
