package sortedmerge;

import java.util.Arrays;

public class JavaMainClass {

    public static void main(String[] args) {
        String[] arr = {"asd", "bsd", "dsa", "dsaz", "dsb"};
//        String[] arr = {"bsd", "asd"};
        System.out.println(Arrays.toString(sortGroupedAnagrams(arr)));
    }

    public static String[] sortGroupedAnagrams(String[] arr) {
        return Arrays.stream(arr)
                .map(str -> new ListEntry(str, sortStr(str)))
                .sorted()
                .map(ListEntry::getOriginal)
                .toList()
                .toArray(new String[arr.length]);
    }

    public static String sortStr(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}

class ListEntry implements Comparable<ListEntry> {
    String original;
    String sorted;

    public ListEntry(String original, String sorted) {
        this.original = original;
        this.sorted = sorted;
    }

    public String getOriginal() {
        return original;
    }

    @Override
    public int compareTo(ListEntry o) {
        return this.sorted.compareTo(o.sorted);
    }
}