package palindrome;

import java.util.HashMap;
import java.util.Map;

public class JavaMainClass {

    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("asdsad!"));
    }

    public static boolean isPalindromePermutation(String str) {
        Map<Character, Integer> charWithCountMap = indexStrByCharCount(str);

        if(str.length() % 2 == 0){
            return checkEvenMap(charWithCountMap);
        } else {
            return checkOddMap(charWithCountMap);
        }
    }

    private static boolean checkOddMap(Map<Character, Integer> charWithCountMap) {
        int numOfOddChars = 0;
        for(Map.Entry<Character, Integer> entry: charWithCountMap.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                numOfOddChars++;
            }
            if(numOfOddChars > 1) {
                return false;
            }
        }
        return numOfOddChars == 1;
    }

    private static boolean checkEvenMap(Map<Character, Integer> charWithCountMap) {
        return charWithCountMap.values().stream().allMatch(count -> count % 2 == 0);
    }

    private static Map<Character, Integer> indexStrByCharCount(String str) {
        Map<Character, Integer> result = new HashMap<>();
        for(Character ch: str.toCharArray()) {
            Integer oldValue = result.getOrDefault(ch, 0);
            result.put(ch, oldValue + 1);
        }
        return result;
    }
}
