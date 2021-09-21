import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k9_B_Korrutamisemang {
    public static void main(String[] args )throws Exception {

        final long startTime = System.currentTimeMillis();

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int arve = Integer.parseInt(sisend[0]);
        int kaske = Integer.parseInt(sisend[1]);
        byte[] arvud = new byte[arve+1];
        if(arve <= 5){
            String arvud_str[] = in.readLine().split(" ");
            for (int i = 1; i < arve + 1; i++) {
                arvud[i] = Byte.valueOf(arvud_str[i-1]);
            }
        } else {
            for (int i = 1; i < arve + 1; i++) {
                arvud[i] = Byte.valueOf(in.readLine());
            }
        }

        for (int i = 0; i < kaske; i++) {
            String arvud_str[] = in.readLine().split(" ");
            byte tulemus = 1;
            if(arvud_str[0].equals("M")){
                arvud[Integer.valueOf(arvud_str[1])] = Byte.valueOf(arvud_str[2]);
            } else {
                byte algus = Byte.valueOf(arvud_str[1]);
                byte lopp = Byte.valueOf(arvud_str[2]);

                for (int j = algus; j <=lopp ; j++) {
                    if(arvud[j] == 0){
                        tulemus = 0;
                        break;
                    } else {
                        tulemus = (byte) (tulemus * (arvud[j]/Math.abs(arvud[j])));
                    }
                }
                System.out.print(tulemus==0 ? "0" : tulemus>0 ? "+" : "-");
            }
        }

    }
}
