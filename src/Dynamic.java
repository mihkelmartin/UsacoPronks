import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dynamic {

    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        String[] numbers = in.readLine().split( " ");
        int n = Integer.valueOf(numbers[0]), mod = Integer.valueOf(numbers[1]);
        long[] dp1 = new long[4000005];
        long[] dp2 = new long[4000005];
        long[] sum = new long[4000005];
        dp1[1] = dp2[1] = 1;

        for (int i = 1; i <= n; i++) {
            dp1[i]+=sum[i]+dp2[i-1];
            dp1[i] %= mod;
            dp2[i] = (dp2[i - 1] + dp1[i]) % mod;
            sum[i + 1] += sum[i];
            sum[i + 1] %= mod;
            for (int j = 2; j * i <= n; ++j) {
                sum[j * i] += dp1[i];
                sum[j * i] %= mod;
                final int min = Math.min(j * i + j, n + 1);
                sum[min] += mod - dp1[i];
                sum[min] %= mod;
            }
            System.out.println(dp1[i-1] + " " + dp1[i] + " " + dp2[i-1] + " " + dp2[i]);

        }
        System.out.println(dp1[n]);
        System.exit(0);
    }
}
