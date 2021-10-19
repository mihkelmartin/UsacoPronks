import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class vormel {
    public static void main(String[] args)throws Exception{
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int rehvide_arv = Integer.parseInt(st.nextToken());
        int ringide_arv = Integer.parseInt(st.nextToken());
        int pit_stop_orginaal = Integer.parseInt(st.nextToken());

        int[][] andmed = new int[ringide_arv][3];
        int[][] rehvid = new int[rehvide_arv][2];
        for (int i = 0; i < rehvide_arv; i++) {
            StringTokenizer sa = new StringTokenizer(in.readLine());
            int kiirus = Integer.parseInt(sa.nextToken()), aeglustus = Integer.parseInt(sa.nextToken());
            rehvid[i][0] = kiirus;
            rehvid[i][1] = aeglustus;
        }

        for (int j = 0; j < ringide_arv; j++) {
            andmed[j][1] = Integer.MAX_VALUE;
            for (int i = 0; i < rehvide_arv; i++) {
                for (int k = 0; k <= j; k++) {

                    int pit_stop = pit_stop_orginaal;
                    if(k == 0) pit_stop = 0;

                    int eelmine = 0;
                    if(k != 0) eelmine = andmed[k - 1][1];

                    int p = j-k+1;
                    int vahe_tulemus = eelmine + pit_stop + rehvid[i][0]*p + rehvid[i][1] * p*(p-1)/2;
                    if(vahe_tulemus < andmed[j][1]){
                        andmed[j][1] = vahe_tulemus;
                        andmed[j][0] = k;
                        andmed[j][2] = i;
                    }
                }
            }
        }


        int mitu_vastust = 0;
        int kust_votsin = ringide_arv-1;
        int viimane_vahetus = 0;
        ArrayList<String> kus_vahetasin = new ArrayList<>();
        int check = 0;
        while (check == 0){
            if (kust_votsin == 0){
                check++;
            }else {
                mitu_vastust++;
                if(kust_votsin == andmed[kust_votsin][0]){
                    andmed[kust_votsin][0]--;
                }
                kus_vahetasin.add((andmed[kust_votsin][2]+1) + " " + (andmed[kust_votsin][0]+1));
                viimane_vahetus = kust_votsin;
                kust_votsin = andmed[kust_votsin][0];
            }
        }
        if(mitu_vastust == 0)System.out.println((andmed[viimane_vahetus][2]+1) + " " + mitu_vastust);
        else System.out.println((andmed[viimane_vahetus][2]+1) + " " + (mitu_vastust - 1));
        for (int m = kus_vahetasin.size() - 2; m >= 0; m--) {
            System.out.println(kus_vahetasin.get(m));
        }
    }
}

/*
2 2 25
45 11
40 20

2 44 170
60 8
30 29

2 44 170
30 29
60 8

3 1 25
45 10
40 20
55 10

2 44 1
60 8
30 29


 */