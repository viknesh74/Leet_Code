import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) maxFactor = item[0];
            if (item[1] < minPrice) minPrice = item[1];
        }

        int[] factorFreq = new int[maxFactor + 1];
        for (int[] item : items) {
            factorFreq[item[0]]++;
        }

        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                multiplesCount[f] += factorFreq[m];
            }
        }

        List<long[]> offers = new ArrayList<>();
        long maxBoostedPrice = 2L * minPrice;
        for (int[] item : items) {
            long price = item[1];
            long gain = multiplesCount[item[0]] - 1L;
            if (gain > 0 && price <= maxBoostedPrice) {
                offers.add(new long[]{price, gain});
            }
        }

        offers.sort(Comparator.comparingLong(a -> a[0]));

        long remBudget = budget;
        long totalCopies = 0;

        for (long[] offer : offers) {
            long price = offer[0];
            long gain = offer[1];
            if (remBudget < price) break;
            long buyCount = Math.min(gain, remBudget / price);
            totalCopies += buyCount * 2L;
            remBudget -= buyCount * price;
        }

        totalCopies += remBudget / minPrice;
        return (int) totalCopies;
    }
}