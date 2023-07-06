package bitinsertion;

public class JavaMainClass {

    public static void main(String[] args) {
        int value = 10;
        System.out.println("value: " + Integer.toBinaryString(value));
        int insertValue = 5;
        System.out.println("insertValue: " + Integer.toBinaryString(insertValue));
        System.out.println("result: " + Integer.toBinaryString(insertion(value, insertValue, 0, 2)));
    }

    public static int insertion(int value, int insertValue, int start, int end) {
        return clear(value, start, end) | (insertValue << start);
    }

    public static int clear(int value, int start, int end) {
        int onesInTheRightPlace = 1;
        for(int i = 0; i < end - start; i++) {
            onesInTheRightPlace <<= 1;
            onesInTheRightPlace++;
        }
        onesInTheRightPlace <<= start;
        return value & ~onesInTheRightPlace;
    }
}