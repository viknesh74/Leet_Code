import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + (long) k2;
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        long[] count = new long[maxDiff + 1];
        long totalDiffSum = 0;
        for (int d : diffs) {
            count[d]++;
            totalDiffSum += d;
        }
        
        if (totalDiffSum <= totalK) {
            return 0;
        }
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            if (count[d] == 0) continue;
            
            long take = Math.min(count[d], totalK);
            count[d] -= take;
            count[d - 1] += take;
            totalK -= take;
        }
        long res = 0;
        for (int d = 0; d <= maxDiff; d++) {
            if (count[d] > 0) {
                res += count[d] * (long) d * d;
            }
        }
        
        return res;
    }
}