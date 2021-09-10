import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class JuryMeeting {

    private static final long MOD=998244353;
    private static InputStreamReader ina = new InputStreamReader(System.in);
    private static BufferedReader in = new BufferedReader(ina);

    public static void main(String[] args )throws Exception {

        int teste = Integer.parseInt(in.readLine());
        while (teste-- > 0) {
            solve();
        }
    }

    private static void solve() throws Exception {
        int inimesi = Integer.parseInt(in.readLine());
        String[] tasks_str = in.readLine().split(" ");
        ArrayList<Long> tasks = new ArrayList<>();
        long max_tasks = 0;
        for (int i = 0; i < inimesi; i++) {
            long task = Integer.parseInt(tasks_str[i]);
            if(task > max_tasks){
                max_tasks = task;
            }
            tasks.add(task);
        }
        int max_taskide_arv = Collections.frequency(tasks, max_tasks);
        int max_miinus_1_taskide_arv = Collections.frequency(tasks, max_tasks-1);
        int ylejaanud = tasks.size() - max_taskide_arv - max_miinus_1_taskide_arv;
        // Kõigil sama arv taske st kõik on max_tasks
        if(max_miinus_1_taskide_arv == 0 && ylejaanud == 0){
            System.out.println(permutatsioonid(max_taskide_arv));
        } else if (max_miinus_1_taskide_arv == 0){ // Kui on muid ka siis peab leiduma vähemnalt 1 inimene kellel on taske max_taskide_arv - 1
            System.out.println("0");
        } else {
            // Kõik võimalikud
            long permutatsioonid = permutatsioonid(tasks.size());

            // Nüüd hakkame maha lahutama neid mis ei sobi e kus kõik max_miinus_1_taskide_arv on eespool
            // Niipalju kui on ylejäänuuid tuleb kombineerida
            // 3 3 3 4 1 2 kõik max miinus-1 ees
            // 3 3 3 1 4 2 kõik max miinus-1 1 ees ja 1 ylejäänud ka
            // 3 3 3 1 2 4
            for (int i = 0; i <= ylejaanud; i++) {

                // Kõik miinus-1 on eesotsas ja lisaks ylejäänutest i
                long eesmise_otsa_perm = permutatsioonid(max_miinus_1_taskide_arv + i);

                // Tuleb arvestada, et maksimume võib mitu olla, siis tekib tagumises otsa
                // permutatsioone ylejaanud -i  korda max_taskide_arv
                long tagumise_otsa_perm = (permutatsioonid(ylejaanud - i) * max_taskide_arv)%MOD;

                // Lisaks kombinatsioonid mida saab teha ylejäänutes koguarvust niipalju kaupa kui on ees otas
                // eesotsas on i tükki, järelikult i kaupa ylejäänust
                long kombinatsioonid = kombinatsioonid(ylejaanud, i);

                // Pane tähele, et vahepeal tuleb %MOD teha, muidu "jookseb üle" KONTROLLI !
                permutatsioonid = (permutatsioonid - ((eesmise_otsa_perm * tagumise_otsa_perm)%MOD * kombinatsioonid)%MOD + MOD) % MOD;
            }
            System.out.println(permutatsioonid);
        }
    }

    private static long kombinatsioonid(long millest, long kaupa) {
        long retVal = 1;
        for (long d = 1; d <= kaupa; ++d) {
            retVal *= millest--;  // Hakkame tagantpoolt, ehk kui 10, siis retval = 1*10 esimene kord, järgmine 10 * 9, kuni kaupa jätkub
            retVal /= d;  // Kuna muurujoone all oli k!(n-k)! siis siin jagame seda k!-d 1, 2, 3  (n-k)! pole vaja sest eelmine samm väldib seda
            retVal %= MOD; // Siin peaks ka tohtima MOD teha
        }
        return retVal;
    }

    private static long permutatsioonid(long max_taskide_arv) {
        long retVal = 1;
        for (int i = 1; i <= max_taskide_arv ; i++) {
         retVal = (retVal*i)%MOD;
        }
        return retVal;
    }
}
