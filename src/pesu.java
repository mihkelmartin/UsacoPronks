import java.io.BufferedReader;
import java.io.InputStreamReader;

public class pesu {

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String[] sisend = in.readLine().split(" ");
        int sonePikkus = Integer.parseInt(sisend[0]);
        int qc = Integer.parseInt(sisend[1]);
        String sone = in.readLine();

        for (int i = 0; i < qc; i++) {
            String[] q = in.readLine().split(" ");
            String kask = q[0];
            if(kask.equals("POORA")){
                int algus = Integer.parseInt(q[1]) - 1;
                int lopp = Integer.parseInt(q[2]) - 1;
                String pooratud = "";
                for (int j = lopp; j >= algus; j--) {
                    pooratud = pooratud + sone.charAt(j);
                }
                sone = sone.substring(0, algus) + pooratud + sone.substring(Math.min(lopp + 1, sonePikkus-1), sonePikkus);
            } else if(kask.equals("ETTE")){
                int algus = Integer.parseInt(q[1]) - 1;
                int lopp = Integer.parseInt(q[2]) - 1;
                sone = sone.substring(algus, lopp + 1) + sone.substring(0, algus) + sone.substring(lopp + 1, sonePikkus);
            } else if(kask.equals("TAHA")){
                int algus = Integer.parseInt(q[1]) - 1;
                int lopp = Integer.parseInt(q[2]) - 1;
                sone =sone.substring(0, algus) + sone.substring(lopp + 1, sonePikkus) + sone.substring(algus, lopp + 1);
            }  else  {
                int algus = Integer.parseInt(q[1]) - 1;
                int lopp = Integer.parseInt(q[2]) - 1;
                loenda(sone, kask, algus, lopp);
            }

        }


    }

    private static void loenda(String sone, String kask, int algus, int lopp) {
        char otsi = 'P';
        switch (kask){
            case "PUNASEID":
                otsi = 'P';
                break;
            case "ROHELISI":
                otsi = 'R';
                break;
            case "SINISEID":
                otsi = 'S';
                break;
        }
        int mitu = 0;
        for (int i = algus; i <= lopp; i++) {
            if(sone.charAt(i) == otsi){
                mitu++;
            }
        }
        System.out.println(mitu);
    }

}

