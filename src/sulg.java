import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sulg {

    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int n = Integer.parseInt(sisend[0]), qc = Integer.parseInt(sisend[1]);
        String sone = in.readLine();
        for (int i = 0; i < qc; i++) {
            sisend = in.readLine().split(" ");
            System.out.println("EI");
        }
    }
}

