import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class k11_B_Enigma {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        in.readLine();
        String fibonaccis[] = in.readLine().split(" ");
        String krupteeritud = in.readLine();
        BigInteger[] fibos = new BigInteger[101];
        genFibos(fibos);

        int pikkus = 0;
        int[] positsioonid = new int[101];
        for (int i = 0; i < fibonaccis.length; i++) {
            int k = mitmesFibo(fibonaccis[i], fibos);
            positsioonid[i+1] = k;
            pikkus = Math.max(k, pikkus);
            System.out.println(k);
        }
        String suuredtahed = "";
        for (int i = 0; i < krupteeritud.length(); i++) {
            String taht = Character.toString(krupteeritud.charAt(i));
            if(taht.equals(taht.toUpperCase()) && !taht.equals(" ") && !taht.equals(",") && !taht.equals("."))
                suuredtahed += taht;
        }


        System.out.println(fibos);
    }

    private static int mitmesFibo(String fibonacci, BigInteger[] fibos) {
        for (int i = 1; i < fibos.length; i++) {
            if (fibos[i].toString().equals(fibonacci)) {
                return i;
            }
        }
        return 0;
    }

    static void genFibos(BigInteger[] fibos)
    {
        fibos[0] = BigInteger.valueOf(1);
        fibos[1] = BigInteger.valueOf(1);
        for (int i = 2; i < fibos.length; i++) {
            fibos[i] = BigInteger.ZERO;
            fibos[i] = fibos[i].add(fibos[i - 1]).add(fibos[i - 2]);
        }
    }

}
