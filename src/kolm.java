import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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

        @Override
        public int hashCode() {
            return Objects.hash(algus.x,algus.y,lopp.x, lopp.y);
        }
    }

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int loike = Integer.parseInt(in.readLine());
        HashSet<Loik> loigud = new HashSet<>();
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
                if(vektorKorrutis(loik1.algus, loik1.lopp, loik2.lopp) == 0) {
                    // samal sirgel, seega kolmnurk kõdunud
                    continue;
                }
                vastus++;
            }
        }
        System.out.println(vastus/2);
    }

    private static int vektorKorrutis(Punkt p0, Punkt p1, Punkt p2){
        int dx1 = p1.x - p0.x, dy1 = p1.y - p0.y;
        int dx2 = p2.x - p0.x, dy2 = p2.y - p0.y;
        return dx1 * dy2 - dx2 * dy1;
    }
}

