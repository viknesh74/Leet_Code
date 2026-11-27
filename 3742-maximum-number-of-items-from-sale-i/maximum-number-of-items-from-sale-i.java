class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }
        int[] divCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int mult = f; mult <= maxFactor; mult += f) {
                divCount[f] += freq[mult];
            }
        }

        final int NEG_INF = Integer.MIN_VALUE / 4;
        int[] dp = new int[budget + 1];
        java.util.Arrays.fill(dp, NEG_INF);
        dp[0] = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) {
                continue;
            }

            int firstCopyGain = divCount[factor];
            int[] nextDp = dp.clone();

            for (int b = price; b <= budget; b++) {
                if (dp[b - price] != NEG_INF) {
                    nextDp[b] = Math.max(nextDp[b], dp[b - price] + firstCopyGain);
                }
            }

            for (int b = price; b <= budget; b++) {
                if (nextDp[b - price] != NEG_INF) {
                    nextDp[b] = Math.max(nextDp[b], nextDp[b - price] + 1);
                }
            }

            dp = nextDp;
        }

        int ans = 0;
        for (int val : dp) {
            if (val > ans) {
                ans = val;
            }
        }
        return ans;
    }
}