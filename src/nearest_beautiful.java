import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class nearest_beautiful {

    public static void main(String[] args) throws Exception{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        int amount = Integer.valueOf(in.readLine());
        for (int i = 0; i < amount; i++) {
            String[] input = in.readLine().split(" ");
            process(input[0], Integer.valueOf(input[1]));
        }
    }
    private static void process(String s, Integer amount_of_diff_numbers) {

        ArrayList<String> prevDigits = new ArrayList<>();
        String prevDigit = s.substring(0, 1);
        prevDigits.add(prevDigit);
        int pos = 0;
        for ( int j = 0; j < s.length(); j++) {
            pos++;
            String digit = s.substring(j, j+ 1);
            if(!prevDigit.equals(digit) && !prevDigits.contains(digit)){
                if(prevDigits.size() == amount_of_diff_numbers) {
                    pos--;
                    break;
                }
                prevDigit = digit;
                prevDigits.add(prevDigit);
            }
        }

        if(s.length() <= pos){
            System.out.println(s);
        } else {

        }

    }

    private static void getMin(String s, ArrayList<String> prevDigits, String change, int pos) {
    }
}
