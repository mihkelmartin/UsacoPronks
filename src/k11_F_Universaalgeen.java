import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeSet;

public class k11_F_Universaalgeen {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int alamstringi_pikkus = Integer.parseInt(in.readLine());
        String genoom = in.readLine();
        HashMap<String, Integer> alamstringid = new HashMap<>();

        String maxString = "";
        int max = 0;
        for (int i = 0; genoom.length() - i >= alamstringi_pikkus; i++) {
            final String alamstring = genoom.substring(i, i + alamstringi_pikkus);
            alamstringid.computeIfAbsent(alamstring, value -> 0);
            int mitu = alamstringid.computeIfPresent(alamstring, (key, value) -> value = value + 1);

            if(mitu > max){
                max = mitu;
                maxString = alamstring;
            } else if(mitu == max){
                if(alamstring.compareTo(maxString) < 0)
                    maxString = alamstring;
            }
        }
        System.out.println(maxString);
    }
}
