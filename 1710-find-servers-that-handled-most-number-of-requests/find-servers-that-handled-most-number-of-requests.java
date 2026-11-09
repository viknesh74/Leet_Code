class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        TreeSet<Integer> availableServers = new TreeSet<>();
        
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        int[] requestCount = new int[k];
        int maxRequests = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int endTime = arrivalTime + load[i];
            
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                availableServers.add(busyServers.poll()[1]);
            }
            
            if (availableServers.isEmpty()) {
                continue;
            }
            
            int preferred = i % k;
            Integer serverId = availableServers.ceiling(preferred);
            
            if (serverId == null) {
                serverId = availableServers.first();
            }
            
            availableServers.remove(serverId);
            busyServers.offer(new int[] { endTime, serverId });
            requestCount[serverId]++;
            maxRequests = Math.max(maxRequests, requestCount[serverId]);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}