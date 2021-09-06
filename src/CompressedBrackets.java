import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class CompressedBrackets {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int pikkus = Integer.parseInt(in.readLine());
        int[] numbrid = new int[pikkus];
        StringTokenizer sc = new StringTokenizer(in.readLine());
        for (int i = 0; i < pikkus; i++) {
            numbrid[i] = Integer.parseInt(sc.nextToken());
        }
        long tulemus = 0;
        int  meeles = 0;
        int jarjes = 0;
        for (int i = 0; i < pikkus-1; i = i + 2) {
            int a = numbrid[i];
            int b = numbrid[i+1];
            if(a >= b){
                meeles = meeles + a - b;
                tulemus = tulemus + b;
                jarjes++;
            }
            if(b > a){
                int vahe = b - a;
                jarjes++;
                tulemus = tulemus + a;
                if(vahe > meeles){
                    tulemus = tulemus + meeles;
                    tulemus = tulemus + ((long) jarjes * (jarjes - 1))/ 2;
                    jarjes = 0;
                    meeles = 0;
                } else {
                    tulemus = tulemus + vahe;
                    meeles = meeles - vahe;
                    tulemus = tulemus + ((long) jarjes * (jarjes - 1))/ 2;
                    jarjes = 1;
                }
            }
        }
        tulemus = tulemus + (((long) jarjes * (jarjes - 1))/ 2);
        System.out.println(tulemus);
    }
}
