import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 100_003L;
    private static final long BASE2 = 100_019L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;

        for (int i = 0; i < n; i++) {
            long val = nums[i] + 1L;
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
            h1[i + 1] = (h1[i] * BASE1 + val) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + val) % MOD2;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(mid, n, h1, h2, p1, p2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueOfLength(int len, int n, long[] h1, long[] h2, long[] p1, long[] p2) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int l = 0; l + len <= n; l++) {
            int r = l + len;
            long x1 = (h1[r] - (h1[l] * p1[len]) % MOD1 + MOD1) % MOD1;
            long x2 = (h2[r] - (h2[l] * p2[len]) % MOD2 + MOD2) % MOD2;
            long key = (x1 << 32) ^ x2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Solution sol = new Solution();
        System.out.println(sol.smallestUniqueSubarray(nums));
        sc.close();
    }
}