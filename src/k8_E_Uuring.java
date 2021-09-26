import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class k8_E_Uuring {

    private  static  int massiivi_poolsuurus =16000;
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int N = Integer.parseInt(sisend[0]);
        int F = Integer.parseInt(sisend[1]);

        int[] arvud = new int[N];
        for (int i = 0; i < N; i++) {
            arvud[i] = Integer.valueOf(in.readLine());
        }
        int[][] tulemused = new int[N][massiivi_poolsuurus * 2 + 1];

        HashSet<Integer> arvutatud_arvud = new HashSet<>();
        arvutatud_arvud.add(0);
        for (int i = 0; i < N; i++) {
            taida_tabel(tulemused, arvud, arvutatud_arvud, i, N - 1, F);
        }

        HashSet<Integer> sobivad_arvud = new HashSet<>();
        int millest_arvutame = F;
        String vastus = "";
        leia_tulemus(tulemused, arvud, N-1, sobivad_arvud, millest_arvutame, vastus);
        System.out.println(vastus);


    }

    private static void leia_tulemus(int[][] tulemused, int[] arvud, int positsioon, HashSet<Integer> sobivad_arvud, int millest_arvutame, String vastus) {
        int kaesolev_arv = arvud[positsioon];
        int tagasi_vaiksem = millest_arvutame - kaesolev_arv;
        int tagasi_suurem = millest_arvutame + kaesolev_arv;
        String vahe_vastus = "";
        if(tulemused[positsioon - 1][tagasi_vaiksem] > 0){
            vahe_vastus = "+";
        } else if(tulemused[positsioon - 1][tagasi_suurem] > 0){
            if(vahe_vastus.equals("+")){
                vahe_vastus = "?";
            } else {
                vahe_vastus = "-";
            }
        } else {
            vahe_vastus = "*";
            return;
        }
    }

    private static void taida_tabel(int[][] tulemused, int[] arvud, HashSet<Integer> arvutatud_arvud, int positsioon, int vaartus, int tulemus) {

        int arvutatud_arve = arvutatud_arvud.size();
        HashSet<Integer> uuedArvud = new HashSet<>();

        for (Integer i : arvutatud_arvud) {
            int arvutatud_arv = i;
            int liidetud = arvutatud_arv + arvud[positsioon];
            int lahutatud = arvutatud_arv - arvud[positsioon];
            uuedArvud.add(liidetud);
            uuedArvud.add(lahutatud);
            if(positsioon != vaartus || lahutatud == tulemus)
              tulemused[positsioon][lahutatud + massiivi_poolsuurus] ++;
            if(positsioon != vaartus || liidetud == tulemus)
              tulemused[positsioon][liidetud + massiivi_poolsuurus] ++;
        }
        if(arvutatud_arve == 1){
            arvutatud_arvud.remove(0);
        }
        arvutatud_arvud.addAll(uuedArvud);
    }

}
