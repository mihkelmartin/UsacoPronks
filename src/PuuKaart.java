import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeMap;

public class PuuKaart {
    public static void main(String[] args) throws Exception {

        HashMap<String, String> puu = new HashMap<>();
        puu.put("A", "A");
        puu.put("C", "C");
        puu.put("D", "D");
        puu.put("B", "B");

        for(String s : puu.values()){
            System.out.println(s);
        }
        for(String s : puu.keySet()){
            System.out.println(s);
        }

    }
}



