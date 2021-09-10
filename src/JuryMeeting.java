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
        int max_taskide_arv_1 = Collections.frequency(tasks, max_tasks-1);
        int ylejaanud = tasks.size() - max_taskide_arv - max_taskide_arv_1;

        System.out.println(max_taskide_arv + " " + max_taskide_arv_1 + " " + ylejaanud);

    }
}
