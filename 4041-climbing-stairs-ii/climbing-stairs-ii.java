class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            int minVal = Integer.MAX_VALUE;
            for (int step = 1; step <= 3; step++) {
                int i = j - step;
                if (i >= 0) {
                    minVal = Math.min(minVal, dp[i] + step * step);
                }
            }
            dp[j] = costs[j - 1] + minVal;
        }

        return dp[n];
    }
}