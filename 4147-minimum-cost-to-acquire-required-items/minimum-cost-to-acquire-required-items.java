class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c = Math.min(need1, need2);
        long r1 = need1 - c;
        long r2 = need2 - c;

        long cc = Math.min((long) costBoth, (long) cost1 + cost2);
        long cr1 = Math.min((long) cost1, (long) costBoth);
        long cr2 = Math.min((long) cost2, (long) costBoth);

        return c * cc + r1 * cr1 + r2 * cr2;
    }
}