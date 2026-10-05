class Solution {
    public int scoreOfParentheses(String s) {
        int sa = 0;
        int d = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                d++;
            } else {
                d--;
                if (s.charAt(i - 1) == '(') {
                    sa += (1 << d);
                }
            }
        }
        return sa;
    }
}