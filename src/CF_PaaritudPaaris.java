import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CF_PaaritudPaaris {

    public static BufferedReader in;
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        in = new BufferedReader(ina);
        int teste = Integer.parseInt(in.readLine());

        int i = 1;
        while (teste-- > 0) {
            lahenda(i++);
        }
    }

    private static void lahenda(int test) throws Exception {
        int teste = Integer.parseInt(in.readLine());
        String algne = in.readLine();
        String[] arvud = algne.split(" ");
        ArrayList<Integer> paaris_indeks = new ArrayList<>();
        ArrayList<Integer> paaritu_indeks = new ArrayList<>();
        for (int i = 0; i < teste; i++) {
            int arv = Integer.valueOf(arvud[i]);
            if(arv%2 == 0){
                paaris_indeks.add(i);
            } else {
                paaritu_indeks.add(i);
            }
        }
        // Kui üks arv vaid siis;
        if((paaris_indeks.size() + paaritu_indeks.size()) <=1){
            System.out.println("0");
        } else {
            // Kui paaris on 2 või enama võrra rohkem kui paarituid või teistpidi, siis võimatu
            if(Math.abs(paaris_indeks.size() - paaritu_indeks.size()) > 1){
                System.out.println("-1");
            } else {
                // Kas esimene on paaris või paaritute seas
                if(paaris_indeks.size() > paaritu_indeks.size()){
                    System.out.println(leia_vahetused(paaris_indeks));
                } else if(paaritu_indeks.size() > paaris_indeks.size()) {
                    System.out.println(leia_vahetused(paaritu_indeks));
                } else {
                    System.out.println(Math.min(leia_vahetused(paaritu_indeks),
                            leia_vahetused(paaris_indeks)));
                }
            }
        }
    }

    private static int leia_vahetused(ArrayList<Integer> rohkematega) {
        int vahetused = 0;
        for (int i = 0; i < rohkematega.size(); i++) {
            vahetused = vahetused + Math.abs(rohkematega.get(i) - i*2);
        }
        return vahetused;
    }
}
