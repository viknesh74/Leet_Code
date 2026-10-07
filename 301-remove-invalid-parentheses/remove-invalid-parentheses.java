
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> q = new LinkedList<>(Arrays.asList(s));
        Set<String> visited = new HashSet<>(q);
        boolean found = false;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (isValid(cur)) {
                    res.add(cur);
                    found = true;
                }
                if (found) continue;
                for (int j = 0; j < cur.length(); j++) {
                    if (cur.charAt(j) != '(' && cur.charAt(j) != ')') continue;
                    String next = cur.substring(0, j) + cur.substring(j + 1);
                    if (visited.add(next)) q.add(next);
                }
            }
            if (found) break;
        }
        return res;
    }
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            if (c == ')' && --count < 0) return false;
        }
        return count == 0;
    }
}
