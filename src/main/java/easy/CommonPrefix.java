package easy;

import java.util.HashSet;
import java.util.Set;

public class CommonPrefix {
    public String longestCommonPrefixOld(String[] strs) {
        String result = "";
        if (strs.length == 0) return result;
        if (strs.length == 1) return strs[0];
        for (int i = 1; i < strs[0].length(); i++) {
            String match = strs[0].substring(0, i);
            Boolean flag = false;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].startsWith(match)) flag = true;
                else {
                    flag = false;
                    break;
                }
            }
            System.out.println(match);
            if (flag) result = match;
            else {
                result = strs[0].substring(0, i - 1);
                break;
            }
        }

        return result;
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] arguments) {
        CommonPrefix cp = new CommonPrefix();
        String[] s1 = new String[]{"flower", "flow", "flight"};
        String[] s2 = new String[]{"flower", "flow", "car"};
        String[] s3 = new String[]{"aaa"};
        String[] s4 = new String[]{"flower","flower","flower","flowe"};
        System.out.println("Common Prefix for 1 is: ");
        //System.out.println("Result is " + cp.longestCommonPrefix(s1));
/*        System.out.println("Common Prefix for 2 is: ");
        System.out.println("Result is " + cp.longestCommonPrefix(s2));
        System.out.println("Common Prefix for 3 is: ");
        System.out.println("Result is " + cp.longestCommonPrefix(s3));*/
        System.out.println("Common Prefix for 4 is: ");
        System.out.println("Result is " + cp.longestCommonPrefix(s4));
    }
}
