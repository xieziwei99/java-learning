package util;

/**
 * @author xzw
 * 2021-03-10
 */
public class Util {
    public static int getIndex(int[] a, int num) {
        for (int i = 0; i < a.length; i++) {
            if (num == a[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int[] getSlice(int[] a, int low, int high) {
        int[] ret = new int[high - low];
        System.arraycopy(a, low, ret, 0, high - low);
        return ret;
    }
}
