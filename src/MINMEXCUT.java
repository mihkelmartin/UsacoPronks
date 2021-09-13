import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MINMEXCUT {

    public static void main(String[] args )throws Exception {

        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);
        int teste = Integer.parseInt(in.readLine());

        while (teste-- > 0) {

            String s = in.readLine();
            int lenght = s.length();
            byte result = 0;
            char previous_character = 0;

            for (int i = 0; i < lenght; i++) {
                char current = s.charAt(i);
                if( current == '0' && previous_character == 0){
                    previous_character = '0';
                    result = 1;
                } else if(current == '0' && previous_character == '1'){
                    result = 2;
                    break;
                } else if(current == '1' && previous_character == '0'){
                    previous_character = '1';
                }
            }
            System.out.println(result);
        }
    }
}
