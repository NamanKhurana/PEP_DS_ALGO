import java.util.Scanner;
import java.util.ArrayList;

public class bpitProgram{
    public static Scanner sc = new Scanner(System.in);

    public static int fib_01(int n, int[] dp) {
		if (n <= 1) {
			dp[n] = n;
			return n;
		}

		if (dp[n] != 0) return dp[n];

		int ans = fib_01(n - 1, dp) + fib_01(n - 2, dp);

		return dp[n] = ans;
	}

	public static int fibDP_01(int N, int[] dp) {
		for (int n = 0; n <= N; n++) {

			if (n <= 1) {
				dp[n] = n;
				continue;
			}

			int ans = dp[n - 1] + dp[n - 2];
			dp[n] = ans;
		}

		return dp[N];
	}

	public static int fibDPExtend_01(int N) {
		int a = 0;
		int b = 1;
		int sum = 0;

		for (int n = 0; n <= N; n++) {
			if (n <= 1) {
				continue;
			}

			sum = a + b;
			a = b;
			b = sum;
		}

		return sum;
	}

    	//PathSeries.=============================================================

	public static int mazePathHV_rec(int sr, int sc, int er, int ec, int[][] dp) {
		if (sr == er && sc == ec) {
			dp[sr][sc] = 1;
			return 1;
		}

		if (dp[sr][sc] != 0) return dp[sr][sc];

		int count = 0;
		if (sr + 1 <= er) count += mazePathHV_rec(sr + 1, sc, er, ec, dp);

		if (sr + 1 <= er && sc + 1 <= ec) count += mazePathHV_rec(sr + 1, sc + 1, er, ec, dp);

		if (sc + 1 <= ec) count += mazePathHV_rec(sr, sc + 1, er, ec, dp);

		return dp[sr][sc] = count;
	}

	public static int mazePathHV_DP(int sr, int sc, int er, int ec, int[][] dp) {

		for (sr = er; sr >= 0; sr--) {
			for (sc = ec; sc >= 0; sc--) {

				if (sr == er && sc == ec) {
					dp[sr][sc] = 1;
					continue;
				}

				int count = 0;
				if (sr + 1 <= er) count += dp[sr + 1][sc];

				if (sr + 1 <= er && sc + 1 <= ec) count += dp[sr + 1][sc + 1];

				if (sc + 1 <= ec) count += dp[sr][sc + 1];

				dp[sr][sc] = count;
			}
		}
		return dp[0][0];
	}

	public static int mazePathMulti_rec(int sr, int sc, int er, int ec, int[][] dp) {
		if (sr == er && sc == ec) {
			dp[sr][sc] = 1;
			return 1;
		}

		if (dp[sr][sc] != 0) return dp[sr][sc];

		int count = 0;
		for (int jump = 1; sr + jump <= er; jump++)
		count += mazePathMulti_rec(sr + jump, sc, er, ec, dp);

		for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
		count += mazePathMulti_rec(sr + jump, sc + jump, er, ec, dp);

		for (int jump = 1; sc + jump <= ec; jump++)
		count += mazePathMulti_rec(sr, sc + jump, er, ec, dp);

		return dp[sr][sc] = count;
	}

	public static int mazePathMulti_DP(int sr, int sc, int er, int ec, int[][] dp) {

		for (sr = er; sr >= 0; sr--) {
			for (sc = ec; sc >= 0; sc--) {

				if (sr == er && sc == ec) {
					dp[sr][sc] = 1;
					continue;
				}

				int count = 0;
				for (int jump = 1; sr + jump <= er; jump++)
				count += dp[sr + jump][sc];

				for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
				count += dp[sr + jump][sc + jump];

				for (int jump = 1; sc + jump <= ec; jump++)
				count += dp[sr][sc + jump];

				dp[sr][sc] = count;
			}
		}

		return dp[0][0];
	}

    public static int boradPath(int si, int ei, int[] dp) {
		if (si == ei) return dp[si] = 1;

		if (dp[si] != 0) return dp[si];

		int count = 0;
		for (int dice = 1; dice <= 6; dice++) {
			if (si + dice <= ei) {
				count += boradPath(si + dice, ei, dp);
			}
		}

		return dp[si] = count;
	}

	public static int boradPath_DP(int si, int ei, int[] dp) {

		for (int i = ei; i >= si; i--) {
			if (i == ei) {
				dp[i] = 1;
				continue;
			}

			int count = 0;
			for (int dice = 1; dice <= 6; dice++) {
				if (i + dice <= ei) {
					count += dp[i + dice];
				}
			}

			dp[i] = count;
		}

		return dp[0];
	}

	public static int boradPath_02_DP(int si, int ei, int[] steps, int[] dp) {

		for (int i = ei; i >= si; i--) {
			if (i == ei) {
				dp[i] = 1;
				continue;
			}

			int count = 0;
			for (int j = 0; j < steps.length; j++) {
				if (i + steps[j] <= ei) {
					count += dp[i + steps[j]];
				}
			}

			dp[i] = count;
		}

		return dp[0];
	}

	public static int boradPath_opti(int si, int ei, int[] dp) {
		LinkedList < Integer > ll = new LinkedList < >();

		for (int i = ei; i >= si; i--) {
			if (i > ei - 2) {
				ll.addFirst(1);
				continue;
			}

			ll.addFirst(2 * ll.getFirst());
			if (ll.size() == 8) {
				int lastValue = ll.removeLast();
				ll.addFirst(ll.removeFirst() - lastValue);
			}
		}

		return ll.getFirst();
	}

	//leetcode 70.
	public static int climbStairs(int n) {
		if (n <= 1) return 1;

		int count = climbStairs(n - 1) + climbStairs(n - 2);
		return count;
	}

	public static int climbStairs_DP(int n) {
		int[] dp = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			if (i <= 1) {
				dp[i] = 1;
				continue;
			}

			dp[i] = dp[i - 1] + dp[i - 2];
		}

		display(dp);
		return dp[n];
	}

	public static int climbStairs_fast(int n) {
		int a = 1;
		int b = 1;
		int ans = 1;
		for (int i = 2; i <= n; i++) {
			ans = a + b;
			a = b;
			b = ans;
		}

		return ans;
	}
    
    public static void main(String[] args){
        
    }
}