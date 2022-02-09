import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class k11_B_Enigma {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String fibonaccis[] = in.readLine().split(" ");
        String krupteeritud = in.readLine();
        BigInteger[] fibos = new BigInteger[100];
        genFibos(fibos);


        System.out.println(fibos);
    }

    static void genFibos(BigInteger[] fibos)
    {
        fibos[0] = BigInteger.valueOf(0);
        fibos[1] = BigInteger.valueOf(1);
        for (int i = 2; i < fibos.length; i++) {

            fibos[i] = BigInteger.valueOf(fibos[i - 1].intValue()).add(fibos[i - 2]);
        }
    }

}
