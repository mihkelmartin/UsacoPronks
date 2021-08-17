import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lemmikloomad {

    public static class Lemmikloom {
        private String liik;
        private int hind;

        Lemmikloom(String liik, int hind){
            this.liik = liik;
            this.hind = hind;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader f = new BufferedReader(new FileReader("lemmikl.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemmikl.out")));

        StringTokenizer st_p = new StringTokenizer(f.readLine(), " ");
        int loomi = Integer.valueOf(st_p.nextToken());
        int eelarve = Integer.valueOf(st_p.nextToken());
        ArrayList<Lemmikloom> lemmikloomad = new ArrayList<>();

        for (int i = 0; i < loomi; i++) {
            st_p = new StringTokenizer(f.readLine(), " ");
            lemmikloomad.add(new Lemmikloom(st_p.nextToken(), Integer.valueOf(st_p.nextToken())));
        }
        lemmikloomad.forEach(loom -> out.println(loom.liik + " " + loom.hind));

        int n = lemmikloomad.size();
        // Erinevate hulkade arv - midagi, 1 kaupa, 2 kaupa, 3 kaupa jne
        // n on mitu objekti on ja hulkasid on 2 astmel n
        for (int b = 0; b < (1<<n); b++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((b & (1<<i)) > 0) subset.add(i);
            }

            int summa = 0;
            String loomad = "";
            for (Integer nr : subset){
                summa += lemmikloomad.get(nr).hind;
                loomad += lemmikloomad.get(nr).liik + " ";
            }
            if(eelarve >= summa) out.println(loomad + " " + summa);
        }
        ArrayList<Integer> permutatsioonid = new ArrayList<>();
        boolean[] valitud = new boolean[4];
        permutatsioonid(permutatsioonid, valitud);


        out.close();
    }

    static void permutatsioonid(ArrayList<Integer> permutatsioonid, boolean[] valitud){

        if(permutatsioonid.size() == valitud.length){
            permutatsioonid.forEach(i -> System.out.print(i + " "));
            System.out.println("");
            return;
        }
        for (int i = 0; i < valitud.length; i++) {
            if(valitud[i]) continue;
            permutatsioonid.add(i);
            valitud[i] = true;
            permutatsioonid(permutatsioonid, valitud);
            permutatsioonid.remove(permutatsioonid.size()-1);
            valitud[i] = false;
        }
    }
}
