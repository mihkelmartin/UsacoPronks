import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class k11_B_Arelofansi {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sona1 = in.readLine().toUpperCase();
        String sona2 = in.readLine().toUpperCase();
        List<String> eba = Arrays.asList("A","E","I","O","U");
        for (String s : eba) {
            sona1.replace(s,"");
            sona2.replace(s,"");
        }
        System.out.println(sona1.equals(sona2) ? "SAMA" : "ERINEV");
    }
}
