import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k7_H_Bitmask {

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int N = Integer.parseInt(sisend[0]);
        int L = Integer.parseInt(sisend[1]);
        int U = Integer.parseInt(sisend[2]);

        int max = N | L;
        int tulem = L;
        for (int i = L + 1; i <= U ; i++) {
            int xor = N | i;
            if(xor > max){
                max = xor;
                tulem = i;
            }
        }
        System.out.println(tulem);
    }

}
