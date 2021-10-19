import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Vormel {
    public static void main(String[] args)throws Exception{
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int rehvide_arv = Integer.parseInt(st.nextToken());
        int ringide_arv = Integer.parseInt(st.nextToken());
        int pit_stop = Integer.parseInt(st.nextToken());
        int[][] andmed = new int[ringide_arv][3];
        int[][] rehvid = new int[rehvide_arv][2];
        int parim_rehv = Integer.MAX_VALUE, parima_rehvi_indeks = 0;
        for (int i = 0; i < rehvide_arv; i++) {
            StringTokenizer sa = new StringTokenizer(in.readLine());
            int kiirus = Integer.parseInt(sa.nextToken()), aeglustus = Integer.parseInt(sa.nextToken());
            if (parim_rehv > kiirus){
                parim_rehv = kiirus;
                parima_rehvi_indeks = i;
            }
            rehvid[i][0] = kiirus;
            rehvid[i][1] = aeglustus;
        }
        andmed[0][1] = rehvid[parima_rehvi_indeks][0];
        andmed[0][2] = parima_rehvi_indeks;
        int pit_stop_meelse = pit_stop;
        for (int j = 1; j < ringide_arv; j++) {
            andmed[j][1] = andmed[j-1][1] + rehvid[andmed[j-1][2]][0] + rehvid[andmed[j-1][2]][1]*(j - andmed[j-1][0]);
            andmed[j][0] = andmed[j-1][0];
            andmed[j][2] = andmed[j-1][2];
            for (int i = 0; i < rehvide_arv; i++) {
                int eelmine_vahetulemus = Integer.MAX_VALUE;
                for (int k = j; k >=0; k--) {

                    pit_stop = pit_stop_meelse;
                    int eelmine_tegelikult = 0;
                    if(k == 0) {
                        pit_stop = 0;
                    }
                    else {
                        eelmine_tegelikult = andmed[k - 1][1];
                    }
                    int vahe_tulemus = eelmine_tegelikult + pit_stop  + rehvid[i][0] * (j-k+1) + rehvid[i][1] * ((j-k+1)*(j-k) / 2);
                    if(vahe_tulemus < andmed[j][1]){
                        andmed[j][1] = vahe_tulemus;
                        andmed[j][0] = k;
                        andmed[j][2] = i;
                    }
                }
            }
        }
        System.out.println("Ahmeddov");
    }
}

/*
        int parim_aeg = andmed[0][ringide_arv-1][1], parim_indeks = 0;
        for (int i = 0; i < rehvide_arv; i++) {
            if(andmed[i][ringide_arv-1][1] < parim_aeg){
                parim_aeg = andmed[i][ringide_arv-1][1];
                parim_indeks = i;
            }
        }
        int mitu_vastust = 0;
        String tulemus = "";
        int kust_votsin = ringide_arv-1;
        ArrayList<Integer> kus_vahetasin = new ArrayList<>();
        int check = 0;
        while (check == 0){
            if (kust_votsin == 0){
                check++;
            }else {
                mitu_vastust++;
                kus_vahetasin.add(andmed[parim_indeks][kust_votsin][0]);
                kust_votsin = andmed[parim_indeks][kust_votsin][0];
            }
        }
        Collections.sort(kus_vahetasin);
        parim_indeks++;
        if(mitu_vastust == 0)System.out.println(parim_indeks + " " + mitu_vastust);
        else System.out.println(parim_indeks + " " + (mitu_vastust - 1));
        for (int m = 1; m < mitu_vastust; m++) {
            System.out.println(kus_vahetasin.get(m) + " " + parim_indeks);
        }
    }
}


 */