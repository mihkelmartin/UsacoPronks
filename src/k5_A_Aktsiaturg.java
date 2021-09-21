import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k5_A_Aktsiaturg {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int t = Integer.parseInt(in.readLine());

        int[] tehingud = new int[t];
        String sisend[] = in.readLine().split(" ");
        for (int i = 0; i < t ; i++) {
            tehingud[i] = Integer.valueOf(sisend[i]);
        }
        System.out.println(maxSubArraySum(tehingud));
    }
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
}
