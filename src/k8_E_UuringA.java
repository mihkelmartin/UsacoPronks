import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class k8_E_UuringA {

    private static final int massiivi_poolsuurus =16000;

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int N = Integer.parseInt(sisend[0]);
        int F = Integer.parseInt(sisend[1]);

        int[] arvud = new int[N];
        ArrayList<HashSet<Integer>> samm_summad = new ArrayList<>();
        HashSet<Integer> esimene = new HashSet<>();
        esimene.add(0);
        samm_summad.add(esimene);
        for (int i = 0; i < N; i++) {
            arvud[i] = Integer.valueOf(in.readLine());
            HashSet<Integer> samm_i = new HashSet<>();
            Iterator<Integer>  iter = samm_summad.get(i).iterator();
            while (iter.hasNext()){
                Integer s = iter.next();
                samm_i.add(s - arvud[i]);
                samm_i.add(s + arvud[i]);
            }
            samm_summad.add(samm_i);
        }
        if(!samm_summad.get(N).contains(F)){
            System.out.println("*");
            System.exit(1);
        }

        System.out.println("A");

    }
}
