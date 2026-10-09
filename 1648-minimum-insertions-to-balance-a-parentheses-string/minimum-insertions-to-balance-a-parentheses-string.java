class Solution {
    public int minInsertions(String s) {
        int c = 0;
        int o = 0;  
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                if (c % 2 == 1) {
                    o++;
                    c--;
                }
                c += 2;
            } else {
                c--;
                if (c < 0) {
                    o++;       
                    c = 1; 
                }
            }
        }
        return o + c;
    }
}