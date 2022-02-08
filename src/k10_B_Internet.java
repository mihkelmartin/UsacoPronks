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

    }
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int mitu_kooli = Integer.parseInt(st.nextToken());
        int teid = Integer.parseInt(st.nextToken());
        int otseyhendus = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Serv>> graaf_kaaluga = new ArrayList<>();
        HashSet<Integer> kasutatud_tipud = new HashSet<>();
        HashSet<Integer> alles_tipud = new HashSet<>();
        TreeMap<Integer, Integer> punktid_kasutuseks = new TreeMap<>();
        for (int i = 0; i < mitu_kooli + 1; i++) {
            graaf_kaaluga.add(new ArrayList<>());
            alles_tipud.add(i);
        }
        ArrayList<Serv> servad = new ArrayList<>();
        for (int i = 0; i < teid; i++) {
            StringTokenizer sa = new StringTokenizer(in.readLine());
            int yks = Integer.parseInt(sa.nextToken()) - 1;
            int kaks = Integer.parseInt(sa.nextToken()) - 1;
            int kaal = Integer.parseInt(sa.nextToken());
            if(yks == kaks)
                continue;
            graaf_kaaluga.get(yks).add(new Serv(kaks, kaal));
            graaf_kaaluga.get(kaks).add(new Serv(yks, kaal));
        }
        for (int i = 0; i < mitu_kooli; i++) {
            graaf_kaaluga.get(mitu_kooli).add(new Serv(i, otseyhendus));
        }
        kasutatud_tipud.add(0);
        alles_tipud.remove(0);
        punktid_kasutuseks.put(0, 0);
        int vastus = 0;
        while (alles_tipud.size() > 0){
            int parim_punkt = -1;
            int parim_kaal = Integer.MAX_VALUE;
            TreeMap<Integer, Integer> vahe = new TreeMap<>();
            for (Map.Entry<Integer, Integer> entry : punktid_kasutuseks.entrySet()){
                for(Serv serv : graaf_kaaluga.get(entry.getKey())){
                    if(!kasutatud_tipud.contains(serv.ots)) {
                        if (serv.kaal < parim_kaal) {
                            parim_punkt = serv.ots;
                            parim_kaal = serv.kaal;
                        }
                        int endine = punktid_kasutuseks.computeIfAbsent(serv.ots, e -> Integer.MAX_VALUE);
                        vahe.put(serv.ots, Math.min(serv.kaal, endine));
                    }
                }
                if(parim_punkt != -1){
                    vastus += parim_kaal;
                    alles_tipud.remove(parim_punkt);
                    kasutatud_tipud.add(parim_punkt);
                }
            }
            for (Map.Entry<Integer, Integer> entry : vahe.entrySet()){
                punktid_kasutuseks.put(entry.getKey(), entry.getValue());
            }
            punktid_kasutuseks.remove(parim_punkt);
        }
        System.out.println(vastus);
    }
}
