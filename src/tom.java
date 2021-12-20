import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class tom {
    public static void main(String[] args)throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        long kaugus = Integer.parseInt(in.readLine());
        long majade_arv = Integer.parseInt(in.readLine());
        ArrayList<Long> majad = new ArrayList<>();
        for (int i = 0; i < majade_arv; i++) {
            majad.add(Long.parseLong(in.readLine()));
        }
        Collections.sort(majad);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        if(majad.get(0) != 1) {
            if (kaugus == 1) {
                out.write((majad.get(0) - 1) + "\n");
            } else {
                if(majad.get(0) == 2){
                    out.write("1\n");
                }else {
                    if (kaugus + 1 >= majad.get(0)) out.write("1.." + (majad.get(0) - 1) + "\n");
                    else out.write((majad.get(0) - kaugus) + ".." + (majad.get(0) - 1) + "\n");
                }
            }
        }
        int eelmine = 0;
        for (int i = 0; i < majade_arv - 1; i++) {
            long ii = majad.get(i+1);
            long lihtsalt_i = majad.get(i);
            if(ii == lihtsalt_i)continue;
            if(ii - lihtsalt_i != 1){
                if(ii -lihtsalt_i == 2){
                    out.write(lihtsalt_i + 1 + "\n");
                }
                else {
                    if(ii -lihtsalt_i - 1 <= kaugus*2)out.write((lihtsalt_i + 1) +".." + (ii - 1) + "\n");
                    else{
                        if(kaugus == 1){
                            out.write(lihtsalt_i+1 + "\n");
                            out.write(ii-1 + "\n");
                        }else {
                            out.write((lihtsalt_i + 1) + ".." + (lihtsalt_i + kaugus)+ "\n");
                            out.write((ii - kaugus) + ".." + (ii - 1)+ "\n");
                        }
                    }
                }
            }
        }
        if(kaugus == 1)out.write((majad.get((int) (majade_arv-1)) + 1)+ "\n");
        else {
            out.write((majad.get((int) (majade_arv-1)) + 1) + ".." + (majad.get((int) (majade_arv-1)) + kaugus)+ "\n");
        }
        out.flush();
    }
}
