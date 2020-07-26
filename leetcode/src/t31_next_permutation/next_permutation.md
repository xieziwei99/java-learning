## Next Permutation

英文原题：

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1



题目解释：

给定一个数，找出该数各位的排列中下一个比此数大的数



解题思路1

从后往前，找到第一个相邻两个数 `左边 < 右边` 的

对应代码1

```java
package t31_next_permutation;

import java.util.Arrays;

/**
 * @author xieziwei99
 * 2020-07-26
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        new Solution().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));  // 1 3 2
    }

    // O(n^2)  空间上：1
    public void nextPermutation(int[] nums) {
        int temp;
        int length = nums.length;
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i - 1] < nums[i]) {
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
            }
        }
    }
}
```


