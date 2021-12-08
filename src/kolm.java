import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class kolm {

    public static class Punkt {
        private short x;
        private short y;

        @Override
        public boolean equals(Object obj) {
            return x == ((Punkt)obj).x && y == ((Punkt)obj).y;
        }

        public Punkt min(Punkt o) {
            if(this.x < o.x || this.x == o.x && this.y < o.y)
                return this;
            else
                return o;
        }
        public Punkt max(Punkt o) {
            if(this.x < o.x || this.x == o.x && this.y < o.y)
                return o;
            else
                return this;
        }

        public Punkt(short x, short y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Loik{
        private Punkt algus;
        private Punkt lopp;

        public Loik(Punkt algus, Punkt lopp) {
            this.algus = algus;
            this.lopp = lopp;
        }

        @Override
        public boolean equals(Object obj) {
            return algus.equals(((Loik)obj).algus) && lopp.equals(((Loik)obj).lopp);
        }
    }

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int loike = Integer.parseInt(in.readLine());
        ArrayList<Loik> loigud = new ArrayList<>();
        HashMap<Punkt, ArrayList<Loik>> punkt_loigud = new HashMap<>();
        for (int i = 0; i < loike; i++) {
            String strloik[] = in.readLine().split(" ");
            Punkt punkt1 = new Punkt(Short.parseShort(strloik[0]), Short.parseShort(strloik[1]));
            Punkt punkt2 = new Punkt(Short.parseShort(strloik[2]), Short.parseShort(strloik[3]));
            Loik loik = new Loik(punkt1.min(punkt2), punkt1.max(punkt2));
            loigud.add(loik);
        }

        int vastus = 0;
        for (Loik loik1 : loigud){
            for (Loik loik2 : loigud){
                if(!loik1.algus.equals(loik2.algus)){
                    // Alguspunktid ei võrdu
                    continue;
                }
                // Alguspunktid klapivad teeme lõig is on lõpppunktide vahel
                Loik loik = new Loik(loik1.lopp.min(loik2.lopp), loik1.lopp.max(loik2.lopp));
                if(!loigud.contains(loik)){
                    // Pole sellist lõiku
                    continue;
                }
                vastus++;
            }
        }
        System.out.println(vastus/2);
    }
}
