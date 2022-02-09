import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k11_A_Telefoninumberid {
    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend = in.readLine();
        int[] tahe_kood = new int[256];
        tahe_kood['A'] = 2;
        tahe_kood['B'] = 2;
        tahe_kood['C'] = 2;
        tahe_kood['D'] = 3;
        tahe_kood['E'] = 3;
        tahe_kood['F'] = 3;
        tahe_kood['G'] = 4;
        tahe_kood['H'] = 4;
        tahe_kood['I'] = 4;
        tahe_kood['J'] = 5;
        tahe_kood['K'] = 5;
        tahe_kood['L'] = 5;
        tahe_kood['M'] = 6;
        tahe_kood['N'] = 6;
        tahe_kood['O'] = 6;
        tahe_kood['P'] = 7;
        tahe_kood['Q'] = 7;
        tahe_kood['R'] = 7;
        tahe_kood['S'] = 7;
        tahe_kood['T'] = 8;
        tahe_kood['U'] = 8;
        tahe_kood['V'] = 8;
        tahe_kood['W'] = 9;
        tahe_kood['X'] = 9;
        tahe_kood['Y'] = 9;
        tahe_kood['Z'] = 9;
        tahe_kood['1'] = 1;
        tahe_kood['0'] = 0;
        for (int i = 0; i < sisend.length(); i++) {
            System.out.print(tahe_kood[sisend.charAt(i)]);
        }
        System.out.println();
    }
}
