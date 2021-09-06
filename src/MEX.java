import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MEX {

    private static final int[] xorid= new int[300005];

    public static void main(String[] args )throws Exception {

        // Täida massiiv xorid, nii, et iga indeksi kohal on eelmiste XOR-imine tulemus
        fillXOR();

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int teste = Integer.parseInt(in.readLine());

        while (teste-- > 0) {
            String arvud[] = in.readLine().split(" ");
            int a = Integer.parseInt(arvud[0]);
            int b = Integer.parseInt(arvud[1]);

            //int xor = computeXOR(a - 1);
            int xor = xorid[a - 1];
            // Esialgne pikkus ongi a - näiteks kui XOR 0, 1 ... a - 1 ongi b
            if(xor == b) {
                System.out.println(a);
            } else {
                // Kui esialgse jada pikkus, millest esimene puuduja oli a,
                // XOR ei võrdunud b-ga, siis tuleb jadasse liikmeid juurde lisada
                // tuleb lisada selline liige mida muutujaga xor XOR-itades saaks b
                // ehk xor ^ X = B. XOR opeeraatorit teades saab X-i nii X = xor ^ b
                // Aga kui selle tulemuseks on xor ^ b = a  siis on probleem, sest
                // a-d me jadasse lisada ei tohi, muidu oleks MEX vale.
                // Kui a-d kasutada ei tohi siis tuleb b saamiseks kahte liiget kasutada
                if((xor ^ b) != a){
                    System.out.println(a + 1);
                } else {
                    System.out.println(a + 2 );
                }
            }
        }
    }

    // Efektiivne, aga peab meeles pidama
    private static int computeXOR(int n)
    {

        // If n is a multiple of 4
        if (n % 4 == 0)
            return n;

        // If n%4 gives remainder 1
        if (n % 4 == 1)
            return 1;

        // If n%4 gives remainder 2
        if (n % 4 == 2)
            return n + 1;

        // If n%4 gives remainder 3
        return 0;
    }

    // Lihtne, tuleb vaid korra teha
    private static void fillXOR(){
        int xorid_size = xorid.length;
        for (int i = 1; i < xorid_size; i++) {
            xorid[i] = xorid[i-1] ^ i;
        }
    }
}

