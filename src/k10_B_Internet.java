import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class k10_B_Internet {

    public static class Serv {
        int kaal;
        int ots;
        public Serv(int ots, int kaal) {
            this.kaal = kaal;
            this.ots = ots;
        }

        @Override
        public boolean equals(Object o) {
            Serv o1 = (Serv) o;
            return this.kaal == o1.kaal && this.ots == o1.ots;
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

        // mitu_kooli + 1 - punkt lisaks mille ise lisame
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
        // Meie lisapunkt otseyhenduse kaaluga
        for (int i = 0; i < mitu_kooli; i++) {
            graaf_kaaluga.get(mitu_kooli).add(new Serv(i, otseyhendus));
            graaf_kaaluga.get(i).add(new Serv(mitu_kooli, otseyhendus));
        }

        HashSet<Integer> kasutatud_tipud = new HashSet<>();
        HashMap<Integer, Serv> punktid_kasutuseks_servaga = new HashMap<>();

        // Esimene punkt ja kaal 0, sest see hinda ei lisa
        punktid_kasutuseks_servaga.put(0, new Serv(0,0));
        int vastus = 0;
        int otse = 0;
        while (kasutatud_tipud.size() < mitu_kooli + 1){

            int parim_punkt = -1, parim_kaal = Integer.MAX_VALUE;
            Serv parim_serv = null;
            // Leia ootel olevatest tippudest vähima kaaluga tipp
            for (Map.Entry<Integer, Serv> punkt_kasutuseks : punktid_kasutuseks_servaga.entrySet()) {
                if(punkt_kasutuseks.getValue().kaal < parim_kaal){
                    parim_punkt = punkt_kasutuseks.getKey();
                    parim_kaal = punkt_kasutuseks.getValue().kaal;
                    // Seda on vaja, et me saaks tuvastada otseühendust
                    parim_serv = punkt_kasutuseks.getValue();
                }
            }
            // Leitud valitud, vähima kaaluga tipu järgi, järgmised ootel tipud
            for( Serv serv : graaf_kaaluga.get(parim_punkt) ){
                // Vaid juhul kui juba ei ole kasutuses
                if(!kasutatud_tipud.contains(serv.ots)){
                    // Kuna võis olla mitu ühendust siis vaata ka, et oleks parima kaaluga
                    // Ning võimalusel vali otseühendus
                    Serv eelmine_serv = punktid_kasutuseks_servaga.computeIfAbsent(serv.ots, e -> new Serv(0, Integer.MAX_VALUE));
                    // Tegelikult peaks siin olema uurimine, et kas on täpselt otseühenduse hind ja siis valima otseühenduse
                    // võib ju juhtuda, et võrdub mõni ühendus otseühendusega ning siis ei peab valima otseühenduse
                    serv = serv.kaal < eelmine_serv.kaal ? serv : eelmine_serv;
                    punktid_kasutuseks_servaga.put(serv.ots, serv);
                }
            }
            vastus += parim_kaal;
            otse = graaf_kaaluga.get(mitu_kooli).contains(parim_serv) || parim_punkt == mitu_kooli ? otse + 1 : otse;
            // Võta ära ootel tippudest ning lisa kasutatud tippudesse
            punktid_kasutuseks_servaga.remove(parim_punkt);
            kasutatud_tipud.add(parim_punkt);
        }
        System.out.println(vastus + " " + otse);
    }
}
