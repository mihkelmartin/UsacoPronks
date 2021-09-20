import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k5_ArvudeLiitmine {
    public static void main(String[] args )throws Exception {

        final long mod = 1000000000 + 7;

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        String sisend[] = in.readLine().split(" ");
        int K = Integer.parseInt(sisend[0]);
        int N = Integer.parseInt(sisend[1]);

        int KN_VAHE = 0;
        int faktoriaale = 1;
        int tegur = N;
        if(K > N){
            KN_VAHE = K-N;
            faktoriaale = N;
            tegur = N;
        } else {
            faktoriaale = K;
            tegur = N;
        }
        long result = 1;
        if(K == 1){
            System.out.println("1");
        } else
        if(K==2) {
            System.out.println(N+1);
        } else {
            // SEE EI TÖÖTA - ERITI KUI K on suurem kui N näiteks, siis tekib 0 palju
            // Lisaks mis siis kui K sama suur kui N või natuke väiksem
            boolean bJagatudKahega = false;
            while (faktoriaale-- > 1) {
                result = result * (tegur+2);
                if(!bJagatudKahega && result%2 == 0){
                    result /= 2;
                    bJagatudKahega = true;
                }
                if(bJagatudKahega) {
                    result %= mod;
                }
                tegur--;
            }
            // Äkki on nii palju kombinatsioone rohkem kui K on suurem kui N, siis saab 0 panne igale poole
            if (KN_VAHE >0){
                result += (KN_VAHE) * (N+1)*(N+2)/2;
                result %= mod;
            }
            System.out.println(result);
        }
    }
}
