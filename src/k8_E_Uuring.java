import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class k8_E_Uuring {

    private static final int massiivi_poolsuurus =16000;

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
        // Esimesel real on arvutused kui ühtegi tehet ei ole tehtud - siis on keskel, ehk arvu null kohas 1
        // Teisel real on positisioonides i väärtus suurem kui 0 kui selline arv on tehete tulemusel saadud
        int[][] tulemused = new int[N + 1][massiivi_poolsuurus * 2 + 1];
        tulemused[0][massiivi_poolsuurus] = 1; // 0 ehk lõpptulemus, seda peab olema 1

        // Siin hoiame kõiki ERINEVAID arve mida oleme saanud - neid peab korrutama arvudega mis massiivis arvud[]
        HashSet<Integer> arvutatud_arvud = new HashSet<>();
        // Esimese arvutamise jaoks pane 0 sisse, see tuleb kohe peale esimest arvutust ära võtta meetodis taida_tabel
        arvutatud_arvud.add(0);
        for (int i = 0; i < N; i++) {
            taida_tabel(tulemused, arvud, arvutatud_arvud, i, F);
        }

        // Nüüd hakkame tagasi kerima, et leida mis sobiva.
        HashSet<Integer> sobivad_arvud = new HashSet<>();
        sobivad_arvud.add(F);
        String loppvastus = "";
        // Hakkame tagantpoolt vaatama mis sovibad - sobivate korral peab peale tehet massiivis >0 olema
        for (int i = N; i > 0 ; i--) {
            String vastus = leia_tulemus(tulemused, arvud, i, sobivad_arvud);
            if(vastus.equals("*")){
                loppvastus = vastus;
                break;
            }
            loppvastus = vastus + loppvastus;
        }
        System.out.println(loppvastus);


    }

    private static String leia_tulemus(int[][] tulemused, int[] arvud, int positsioon, HashSet<Integer> sobivad_arvud) {
        String vastus = "";
        HashSet<Integer> uued_arvud = new HashSet<>();
        int kaesolev_arv = arvud[positsioon - 1];

        boolean bPos = false;
        boolean bNeg = false;
        for (Integer i : sobivad_arvud) {
            // Tee tehe
            int tagasi_vaiksem = i - kaesolev_arv;
            int tagasi_suurem = i + kaesolev_arv;

            // Vaata kas on olemas
            if (tulemused[positsioon - 1][tagasi_vaiksem + massiivi_poolsuurus] > 0) {
                bPos = true;
                uued_arvud.add(tagasi_vaiksem);
            }
            if (tulemused[positsioon - 1][tagasi_suurem + massiivi_poolsuurus] > 0) {
                uued_arvud.add(tagasi_suurem);
                bNeg = true;
            }
        }
        if(bNeg && bPos){
            vastus = "?";
        } else if(bNeg && !bPos){
            vastus = "-";
        } else if(!bNeg && bPos){
            vastus = "+";
        } else {
            vastus = "*";
        }
        // Tühjenda ja jäta meelde uued
        sobivad_arvud.clear();
        sobivad_arvud.addAll(uued_arvud);
        return vastus;

    }

    private static void taida_tabel(int[][] tulemused, int[] arvud, HashSet<Integer> arvutatud_arvud, int positsioon, int tulemus) {

        int arvutatud_arve = arvutatud_arvud.size();
        HashSet<Integer> uuedArvud = new HashSet<>();

        for (Integer i : arvutatud_arvud) {
            int arvutatud_arv = i;
            int liidetud = arvutatud_arv + arvud[positsioon];
            int lahutatud = arvutatud_arv - arvud[positsioon];
            uuedArvud.add(liidetud);
            uuedArvud.add(lahutatud);
            // Viimane kord, kui positsioon == väärtus siis pane ainult siis massiivi kui lahutatud = tulemus --> ehk saadi lõplik arv
            // ka tegelikult kätte. Kui me ei ole viimasel arvutusel st positsioon < vaartus
            if(positsioon < arvud.length - 1 || lahutatud == tulemus)
              tulemused[positsioon + 1][lahutatud + massiivi_poolsuurus] ++;
            if(positsioon < arvud.length - 1 || liidetud == tulemus)
              tulemused[positsioon + 1][liidetud + massiivi_poolsuurus] ++;
        }
        // Esimene kord panime 0, seda ei saa sisse jätta, muidu tuleb üleliia tulemus
        // Selle koha võiks paremini teha
        if(arvutatud_arve == 1){
            arvutatud_arvud.remove(0);
        }
        // Lisa uued arvud
        arvutatud_arvud.addAll(uuedArvud);
    }

}
