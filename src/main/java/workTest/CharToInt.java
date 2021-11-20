package workTest;

public class CharToInt {
    public static void main(String[] arg) {
        int x, count = 0;

        for (x = 9000; x > 0; x--) {
            char ch = (char) x;
            System.out.print(x+"-" + ch + "| ");
//            System.out.print(" " + ch + " ");
            count++;
            if (count % 20 == 0) System.out.println();
        }
    }
}
