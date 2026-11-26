import java.util.Scanner;

class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;
        while (n >= remove) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }
        return !aliceTurn;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            Solution sol = new Solution();
            System.out.println(sol.canAliceWin(n));
        }
        sc.close();
    }
}