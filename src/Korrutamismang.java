import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Korrutamismang {

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");

        byte N = Byte.parseByte(sisend[0]);
        byte[] arvud = new byte[N];
        String arvudStr[] = in.readLine().split(" ");
        for (byte i = 0; i < N; i++) {
            arvud[i] = Byte.parseByte(arvudStr[i]);
        }

        byte K = Byte.parseByte(sisend[1]);
        for (byte i = 0; i < K; i++) {
            String kask[] = in.readLine().split(" ");
            byte esim = Byte.parseByte(kask[1]);
            byte teine = Byte.parseByte(kask[2]);

            if(kask[0].equals("M")){
                arvud[esim - 1] = teine;
            } else {
                byte korrutis = 1;
                for (int j = esim; j <= teine; j++) {
                    if(arvud[j - 1] == 0){
                        korrutis = 0;
                        break;
                    }
                    korrutis *= arvud[j - 1]/Math.abs(arvud[j - 1]);
                }
                System.out.print( korrutis > 0 ? "+" : korrutis < 0 ? "-" : "0");
            }
        }
    }

}
