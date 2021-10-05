import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF_A {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        int ullessandeid = Integer.parseInt(in.readLine());
        for (int i = 0; i < ullessandeid; i++) {
            String[] sisend = in.readLine().split(" ");
            int relvade_arv = Integer.parseInt(sisend[0]), elud = Integer.parseInt(sisend[1]);
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int suurim = -1, teine = -1;
            for (int j = 0; j < relvade_arv; j++) {
                int sisse = Integer.parseInt(st.nextToken());
                if(sisse >= suurim){
                    teine = suurim;
                    suurim = sisse;
                }
                else{
                    if(sisse > teine)teine = sisse;
                }
            }
            int tulemus = (elud / (suurim + teine)) * 2; //
            int jaak = elud % (suurim + teine);

            if(jaak != 0){
                if(jaak <= suurim){
                  tulemus++;
                } else {
                    tulemus += 2;
                }
            }
            System.out.println(tulemus);
        }
    }
}