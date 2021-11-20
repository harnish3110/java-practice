package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] arguments) {
        int[] case1 = new int[]{1, 2, 3, 4, 5, 6, 7, 7, 0, 5, 3, 3};
        int[] case2 = new int[]{0,0,0,0};
        int target1 = 10;
        int target2 = 0;

//        System.out.println(FourSum.fourSum(case1, target1));
        System.out.println(FourSum.fourSum(case2, target2));

    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 4) {
            return result;
        }

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int currTarget = target - (nums[i] + nums[j]);
                FourSum.search(nums, currTarget, i, j, result);
            }
        }

        return result;
    }

    private static void search(int[] nums, int target, int first, int second, List<List<Integer>> result) {
        int left = second + 1;
        int right = nums.length - 1;

        while (left < right) {
            int currTarget = nums[left] + nums[right];
            if (currTarget == target) {
                Integer[] four = new Integer[]{nums[first], nums[second], nums[left], nums[right]};
                List<Integer> set = Arrays.asList(four);
                result.add(set);
                left += 1;
                right -= 1;
                while (left < right && nums[left] == nums[left - 1]) left += 1;
                while (left < right && nums[right] == nums[right + 1]) right -= 1;
            } else if (currTarget > target) {
                right -= 1;
            } else {
                left += 1;
            }
        }
    }
}
