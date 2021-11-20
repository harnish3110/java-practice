package workTest;

public class Initialize {
    public static void main(String a[]) {
        DataMap map = new DataMap(32);
        map.printMap();
        System.out.println("Result for Value 124 = " + map.getKey(124));
        System.out.println("Result for Key 67.234 = " + map.getValue(67.234f));
    }

}
