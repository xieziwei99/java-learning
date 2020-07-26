package t31_next_permutation;

import java.util.Arrays;

/**
 * @author xieziwei99
 * 2020-07-26
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        new Solution().nextPermutation_我的方法(nums);
        System.out.println(Arrays.toString(nums));  // 1 3 2
    }

    // O(n)：将数组扫描了两遍，虽然 for 循环中有一个 while 循环，但在 for 中会进入 while 循环的只有一次
    // 空间上：1
    // 此方法与 leetcode 的解法一致
    public void nextPermutation_我的方法(int[] nums) {
        int temp;
        boolean flag = false;
        int length = nums.length;
        for (int i = length - 1; i >= 1; i--) {
            if (nums[i - 1] < nums[i]) {
                flag = true;
                // 5 3 4 4 4 4 3 2
                int j = length - 1;
                while (nums[j] <= nums[i - 1]) {
                    j--;
                }
                temp = nums[i - 1];
                nums[i - 1] = nums[j];
                nums[j] = temp;
                int ii = i;
                int jj = length - 1;
                while (ii < jj) {
                    temp = nums[ii];
                    nums[ii] = nums[jj];
                    nums[jj] = temp;
                    ii++;
                    jj--;
                }

                break;
            }
        }
        if (!flag) {
            int ii = 0;
            int jj = length - 1;
            while (ii < jj) {
                temp = nums[ii];
                nums[ii] = nums[jj];
                nums[jj] = temp;
                ii++;
                jj--;
            }
        }
    }
}
