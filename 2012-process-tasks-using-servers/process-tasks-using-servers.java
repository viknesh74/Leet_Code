class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        java.util.PriorityQueue<int[]> free = new java.util.PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        );

        java.util.PriorityQueue<long[]> busy = new java.util.PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);

            if (free.isEmpty()) {
                time = busy.peek()[0];
            }

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] cur = busy.poll();
                free.offer(new int[]{(int) cur[1], (int) cur[2]});
            }

            int[] server = free.poll();
            ans[j] = server[1];
            busy.offer(new long[]{time + tasks[j], server[0], server[1]});
        }

        return ans;
    }
}