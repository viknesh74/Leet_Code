class Solution {
    static class WindowMedianCost {
        private final int leftSize;
        private final PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> right = new PriorityQueue<>();
        private final Map<Integer, Integer> delayed = new HashMap<>();
        private long leftSum = 0;
        private long rightSum = 0;
        private int leftCount = 0;
        private int rightCount = 0;

        public WindowMedianCost(int k) {
            this.leftSize = (k + 1) / 2;
        }

        private void prune(PriorityQueue<Integer> pq) {
            while (!pq.isEmpty() && delayed.getOrDefault(pq.peek(), 0) > 0) {
                int val = pq.peek();
                delayed.put(val, delayed.get(val) - 1);
                pq.poll();
            }
        }

        public void add(int num) {
            if (left.isEmpty() || num <= left.peek()) {
                left.offer(num);
                leftSum += num;
                leftCount++;
            } else {
                right.offer(num);
                rightSum += num;
                rightCount++;
            }
            rebalance();
        }

        public void remove(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if (!left.isEmpty() && num <= left.peek()) {
                leftSum -= num;
                leftCount--;
                if (num == left.peek()) prune(left);
            } else {
                rightSum -= num;
                rightCount--;
                if (!right.isEmpty() && num == right.peek()) prune(right);
            }
            rebalance();
        }

        private void rebalance() {
            if (leftCount > leftSize) {
                prune(left);
                int move = left.poll();
                leftSum -= move;
                leftCount--;
                right.offer(move);
                rightSum += move;
                rightCount++;
                prune(left);
            } else if (leftCount < leftSize && rightCount > 0) {
                prune(right);
                int move = right.poll();
                rightSum -= move;
                rightCount--;
                left.offer(move);
                leftSum += move;
                leftCount++;
                prune(right);
            }
        }

        public long getCost() {
            prune(left);
            long median = left.peek();
            long costLeft = (long) leftCount * median - leftSum;
            long costRight = rightSum - (long) rightCount * median;
            return costLeft + costRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCost = new long[n];
        
        WindowMedianCost tracker = new WindowMedianCost(x);
        for (int i = 0; i < n; i++) {
            tracker.add(nums[i]);
            if (i >= x) {
                tracker.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                windowCost[i] = tracker.getCost();
            }
        }

        long INF = (long) 1e18;
        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = j * x; i <= n; i++) {
                long res = dp[j][i - 1];
                if (dp[j - 1][i - x] != INF) {
                    res = Math.min(res, dp[j - 1][i - x] + windowCost[i - 1]);
                }
                dp[j][i] = res;
            }
        }

        return dp[k][n];
    }
}