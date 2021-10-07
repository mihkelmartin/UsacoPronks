import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ring {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int N = Integer.parseInt(sisend[0]);
        int M = Integer.parseInt(sisend[1]);
        int S = Integer.parseInt(sisend[2]);
        ArrayList<ArrayList<Integer>> servad = new ArrayList<>();
        // Loo väljakutele koht kust teid hoida
        // Pane 1 rohkem, et saaks alustada indeksiga 1 --> arusaadavam, aga ei pea
        for (int i = 0; i <= N; i++) {
            servad.add(new ArrayList<>());
        }
        // Lisa teed
        for (int i = 0; i < M; i++) {
            sisend = in.readLine().split(" ");
            int s1 = Integer.valueOf(sisend[0]);
            int s2 = Integer.valueOf(sisend[1]);
            // Mõlemapoolselt, sest mõlematpidi saab liikuda
            servad.get(s1).add(s2);
            servad.get(s2).add(s1);
        }

        // 1 rohkem, et saaks indeksiga 1 alustada, arusaadavam, aga ei pea
        int[] kaidud = new int[N + 1];
        kaidud[S] = 1;
        int vastus = otsi(S, servad, kaidud, S);
        System.out.println(vastus);

    }

    private static int otsi(int valjak, ArrayList<ArrayList<Integer>> servad, int[] kaidud, int eelmine) {
        int vastus = 0;
        ArrayList<Integer> teed_valjakult = servad.get(valjak);
        boolean bLiigutiEdasi = false;
        for (int i = 0; i < teed_valjakult.size(); i++) {
            int uus_valjak = teed_valjakult.get(i);
            if(kaidud[uus_valjak] == 0){
                bLiigutiEdasi = true;
                kaidud[uus_valjak] = 1;
                vastus += otsi(uus_valjak, servad, kaidud, valjak);

                // Reksursioonis saabusime tagasi punkti kus avastati ring
                // Siin probleem sellest, et peame tegutsema siis kui just ringi pealt siia jõudsime
                // MITTE teistel juhtudel --> sellest punktist võib ka teisi harusid minna !!
                // Kas tuleks uus staatus võtta näiteks 3 ?! ring töödeldud
                if(kaidud[valjak]==2){
                    // Ainult siis lisa juurde kui eelmisel ei olnud teisi harusid, vaid sellisel saab
                    // eelmist ennast ära kasutada
                    if(servad.get(uus_valjak).size() <= 2){
                        vastus++;
                    }
                    // Arvesse võetud = 3
                    kaidud[valjak]=3;
                }
            } else if(uus_valjak != eelmine) {
                // Avastasime ringi, sätime püsti = 2, sest sellega on vaja tegeleda kui ringist tagasi jõuame
                // Siin tähtis, et kui ringi lõppu tuldi tipust kus muid harusid polnud, siis jäib bLiigutiEdas == false
                // ja tagastame 1, ehk 1 juurde. Kui aga oli eelemisel teisi harusid, siis seda eelmist kasutada ei saanud
                // ja juurde ei lisata siin midagi
                kaidud[uus_valjak] = 2;
            }
        }
        // Käidi tipus või avastati ringi mille servas ei olnud muid tippe
        if(!bLiigutiEdasi){
            vastus = 1;
        }
        return vastus;
    }
}
/*
Iga silmus tuleb kohe läbi teha, ei saa tagasi tulla

8 9 3
1 2
2 3
3 4
4 1
2 5
5 6
6 7
5 7
7 8

9 10 8
1 2
2 3
3 4
4 1
2 5
5 6
6 7
5 7
7 8
4 9

 */