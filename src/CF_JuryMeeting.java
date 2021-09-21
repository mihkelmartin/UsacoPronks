import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class CF_JuryMeeting {

    private static final long MOD=998244353;
    private static final int mx = 2 * 100001;
    private static final long[] fact = new long[mx];
    private static final long[] inv = new long[mx];
    private static final long[] invfact = new long[mx];
    private static InputStreamReader ina = new InputStreamReader(System.in);
    private static BufferedReader in = new BufferedReader(ina);

    public static void main(String[] args )throws Exception {

        fillFactorials();
        int teste = Integer.parseInt(in.readLine());
        while (teste-- > 0) {
            solve();
        }
    }

    private static void fillFactorials() {
        fact[0] = fact[1] = inv[1] = invfact[0] = invfact[1] = 1;
        for (int i = 2; i < mx; i++) {
            fact[i] = (fact[i-1]*i) % MOD;
            inv[i] = MOD - MOD/i*inv[(int)MOD%i]%MOD;
            invfact[i] = inv[i]*invfact[i-1]%MOD;
        }
    }

    private static void solve() throws Exception {
        int inimesi = Integer.parseInt(in.readLine());
        String[] tasks_str = in.readLine().split(" ");
        ArrayList<Long> tasks = new ArrayList<>();
        for (int i = 0; i < inimesi; i++) {
            tasks.add(Long.valueOf(tasks_str[i]));
        }

        long max_tasks = Collections.max(tasks);
        int max_taskide_arv = Collections.frequency(tasks, max_tasks);
        int max_miinus_1_taskide_arv = Collections.frequency(tasks, max_tasks-1);
        int ylejaanud = tasks.size() - max_taskide_arv - max_miinus_1_taskide_arv;

        // Kui max_taskide arvuga inimesi on rohkem kui 1 siis on kõik permutatsiooni ilusad, sest nad omavahel saavad hakkama
        if(max_taskide_arv > 1){
            System.out.println(fact[tasks.size()]);
        } else if (max_miinus_1_taskide_arv == 0){ // Kui max_taskidega on 1 inimene, siis peab leiduma vähemnalt 1 inimene kellel on taske max_taskide_arv - 1
            System.out.println("0");
        } else {
            // Kõik võimalikud
            long permutatsioonid = fact[tasks.size()];

            // Nüüd hakkame maha lahutama neid mis ei sobi e kus kõik max_miinus_1_taskide_arv on eespool
            // Niipalju kui on ylejäänuuid tuleb kombineerida
            // 3 3 3 4 1 2 kõik max miinus-1 ees
            // 3 3 3 1 4 2 kõik max miinus-1 1 ees ja 1 ylejäänud ka
            // 3 3 3 1 2 4
            for (int i = 0; i <= ylejaanud; i++) {

                // Kõik miinus-1 on eesotsas ja lisaks ylejäänutest i
                long eesmise_otsa_perm = fact[max_miinus_1_taskide_arv + i];

                // Taga permuteeruvad ylejäänud - u
                long tagumise_otsa_perm = fact[ylejaanud - i];

                // Lisaks kombinatsioonid mida saab teha ylejäänutes koguarvust niipalju kaupa kui on ees otas
                // eesotsas on i tükki, järelikult i kaupa ylejaannu-st
                // https://cp-algorithms.com/combinatorics/binomial-coefficients.html

                long kombinatsioonid = kombinatsioonid(ylejaanud, i);

                // Pane tähele, et vahepeal tuleb %MOD teha, muidu "jookseb üle"
                permutatsioonid = (permutatsioonid - ((eesmise_otsa_perm * tagumise_otsa_perm)%MOD * kombinatsioonid)%MOD + MOD) % MOD;
            }
            System.out.println(permutatsioonid);
        }
        // precomp(25);
    }

    private static long kombinatsioonid(int millest, int kaupa) {

        // MOD-i tuleb igakorrutamise järel teha
        return fact[millest] * invfact[millest-kaupa] % MOD * invfact[kaupa] % MOD;
    }


    private static long nCrModp(int n, int r)
    {
        if (r > n - r)
            r = n - r;

        long[] C = new long[r+1];

        C[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, r); j > 0; j--)
                C[j] = (C[j] + C[j - 1]) % MOD;
        }
        return C[r];
    }


}
