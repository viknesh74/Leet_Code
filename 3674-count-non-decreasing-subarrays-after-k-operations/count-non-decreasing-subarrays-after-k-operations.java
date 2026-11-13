import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long currentOps = 0;
        
        Deque<Integer> dq = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            while (!dq.isEmpty() && nums[dq.peekFirst()] <= nums[l]) {
                int prevPeakIdx = dq.pollFirst();
                int nextBoundary = dq.isEmpty() ? r + 1 : dq.peekFirst();
                long count = nextBoundary - prevPeakIdx;
                currentOps += count * (long) (nums[l] - nums[prevPeakIdx]);
            }
            dq.addFirst(l);

            while (currentOps > k) {
                int rightmostPeakIdx = dq.peekLast();
                currentOps -= (nums[rightmostPeakIdx] - nums[r]);
                if (rightmostPeakIdx == r) {
                    dq.pollLast();
                }
                r--;
            }

            ans += (r - l + 1);
        }

        return ans;
    }
}