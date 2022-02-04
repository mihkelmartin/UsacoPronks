import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class k10_A_Laadimispunktid {

    private static ArrayList<String> linnad = new ArrayList<>();
    private static HashMap<String, String> vanemad = new HashMap<>();
    private static HashSet<String> kulastatud = new HashSet<>(); // kas tipp on külastatud
    private static int jrk_nr = 0; // mitmendana tipp leitakse
    private static HashSet<String> ap = new HashSet<>(); // leitud artikulatsioonipunktid
    private static int[] aeg; // külastamise järjekorranumber ehk „aeg“
    private static int[] madalaim; // vähim sellise tipu külastusaeg, millel on antud t

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int linnu  = Integer.parseInt(in.readLine());
        for (int i = 0; i < linnu; i++) {
            linnad.add(in.readLine());
        }
        aeg = new int[linnu];
        madalaim = new int[linnu];

        HashMap<String, ArrayList<String>> linnast_teed = new HashMap<>();
        int teesid  = Integer.parseInt(in.readLine());
        for (int i = 0; i < teesid; i++) {
            String[] paar = in.readLine().split(" ");
            linnast_teed.computeIfAbsent(paar[0], e -> new ArrayList<>()).add(paar[1]);
            linnast_teed.computeIfAbsent(paar[1], e -> new ArrayList<>()).add(paar[0]);
        }
        leiaAPd(linnad.get(0), linnast_teed);
        System.out.println(ap.size());
        Arrays.sort(ap.toArray());
        for (String s : ap) {
            System.out.println(s);
        }

    }

    private static void leiaAPd(String linn, HashMap<String, ArrayList<String>> linnast_teed)
    {
        int alluvaid = 0; // loendame alluvaid alampuus
        kulastatud.add(linn); // siin me oleme
        int linn_index = linnad.indexOf(linn);
        aeg[linn_index] = madalaim[linn_index] = ++jrk_nr; // alguses on madalaim avastamise aeg
        for (String linn_kuhu_saab : linnast_teed.get(linn)) {
            int linn_kuhu_saab_index = linnad.indexOf(linn_kuhu_saab);
            if (!kulastatud.contains(linn_kuhu_saab)) { //Kui v ei ole veel külastatud, siis on see u alluv puus
                alluvaid++; // loeme selle u alluvaks
                vanemad.put(linn_kuhu_saab, linn); // ja paneme u v vanemaks
                leiaAPd(linn_kuhu_saab, linnast_teed); // jätkame v-ga
                madalaim[linn_index] = Math.min(madalaim[linn_index], madalaim[linn_kuhu_saab_index]);
                // u on artikulatsioonipunkt, kui:
                if (!vanemad.containsKey(linn) && alluvaid > 1) // u on juur ja tal on mitu alluvat
                    ap.add(linn);
                if (!vanemad.containsKey(linn) && madalaim[linn_kuhu_saab_index] >= aeg[linn_index]) // u alt ei ole tagasiserva
                    ap.add(linn);
            }
            else if (vanemad.get(linn) == null || !vanemad.get(linn).equals(linn_kuhu_saab)) // kui v ei ole u vanem
                madalaim[linn_index] = Math.min(madalaim[linn_index], aeg[linn_kuhu_saab_index]);
        }
    }
}
