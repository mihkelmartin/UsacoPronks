import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Lehmanumbrid {

    static char[][] margid = {
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'},
            {'J', 'K', 'L'},
            {'M', 'N'},
            {'O', 'P'},
            {'R', 'S'},
            {'T', 'U'},
            {'V', 'X'},
            {'Y', 'Z'},
    };

    static String[][] margidStr2 = {
            {"A", "B", "C"},
            {"D", "E", "F"},
            {"G", "H", "I"},
            {"J", "K", "L"},
            {"M", "N"},
            {"O", "P"},
            {"R", "S"},
            {"T", "U"},
            {"V", "X"},
            {"Y", "Z"},
    };

    static String[] margidStr1 = {"ABC","DEF","GHI","JKL","MN","OP","RS","TU","VX","XZ"};


    public static void main(String[] args) throws Exception{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        String numberStr = in.readLine();
        int pikkus = numberStr.length();
        arvuta(numberStr, numberStr.length(), "", 0, 0);

    }

    private static void arvuta(String numberStr, int length, String str, int numberStrPos, int charPos) {
        // length on kaasa antud vaid kiiruse optimeerimiseks, et mitte kutsuda numberStr.length() iga kord
        if(str.length() == length){
            System.out.println(str);
            return;
        }

        int number = Integer.valueOf(Character.toString(numberStr.charAt(numberStrPos)));
        if(charPos < margidStr1[number].length() - 1  ){
            arvuta(numberStr, length, str, numberStrPos, charPos + 1);
        }
        str = str + margidStr1[number].charAt(charPos);
        arvuta(numberStr, length, str, numberStrPos + 1, 0);
    }
}
