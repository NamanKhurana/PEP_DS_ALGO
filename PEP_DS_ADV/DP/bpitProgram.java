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

		//stringSet.=================================================

	public static boolean[][] isPalindromicSubString(String str) {
		boolean[][] dp = new boolean[str.length()][str.length()];

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (gap == 0) dp[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) dp[si][ei] = true;
				else dp[si][ei] = str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1];
			}
		}

		return dp;
	}

	public static int longestPalindromicSubstring(String str) {
		int[][] dp = new int[str.length()][str.length()];
		int maxLength = 0;

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {

				if (gap == 0) dp[si][ei] = 1;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) dp[si][ei] = 2;
				else if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1] != 0) {
					dp[si][ei] = dp[si + 1][ei - 1] + 2;
				}
				maxLength = Math.max(maxLength, dp[si][ei]);
			}
		}

		return maxLength;

	}

	public static int longestPalindromicSubsubsequence_Rec(String str, int si, int ei, int[][] dp) {
		if (si > ei) return 0;
		if (si == ei) return dp[si][ei] = 1;

		if (dp[si][ei] != 0) return dp[si][ei];

		if (str.charAt(si) == str.charAt(ei)) {
			return dp[si][ei] = longestPalindromicSubsubsequence_Rec(str, si + 1, ei - 1, dp) + 2;
		}

		int strA = longestPalindromicSubsubsequence_Rec(str, si + 1, ei, dp);
		int strB = longestPalindromicSubsubsequence_Rec(str, si, ei - 1, dp);

		return dp[si][ei] = Math.max(strA, strB);
	}

	public static int longestPalindromicSubsubsequence(String str) {
		int[][] dp = new int[str.length()][str.length()];

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {

				if (gap == 0) dp[si][ei] = 1; //length 1
				else if (str.charAt(si) == str.charAt(ei)) dp[si][ei] = dp[si + 1][ei - 1] + 2;
				else dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
			}
		}

		return dp[0][str.length() - 1];
	}

	public static String longestPalindromicSubsubsequence_String(String str) {
		String[][] dp = new String[str.length()][str.length()];

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {

				if (gap == 0) dp[si][ei] = str.charAt(si) + ""; //length 1
				else if (gap == 1 && str.charAt(si) == str.charAt(ei)) dp[si][ei] = str.substring(si, ei + 1);
				else if (str.charAt(si) == str.charAt(ei)) dp[si][ei] = str.charAt(si) + dp[si + 1][ei - 1] + str.charAt(ei);
				else dp[si][ei] = dp[si + 1][ei].length() >= dp[si][ei - 1].length() ? dp[si + 1][ei] : dp[si][ei - 1];
			}
		}

		return dp[0][str.length() - 1];
	}

    public static int countOfPalindromicSubstring(String str) {
		boolean[][] dp = new boolean[str.length()][str.length()];
		int count = 0;
		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (gap == 0) dp[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) dp[si][ei] = true;
				else dp[si][ei] = str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1];

				count = dp[si][ei] ? count + 1 : count;
			}
		}

		return count;

	}

	public static int countOfPalindromicSubsubsequence_Rec(String str, int si, int ei, int[][] dp) {
		if (si > ei) return 0;
		if (si == ei) return dp[si][ei] = 1;

		if (dp[si][ei] != 0) return dp[si][ei];

		int middleString = countOfPalindromicSubsubsequence_Rec(str, si + 1, ei - 1, dp);
		int withoutFirstCharString = countOfPalindromicSubsubsequence_Rec(str, si + 1, ei, dp);
		int withoutLastCharString = countOfPalindromicSubsubsequence_Rec(str, si, ei - 1, dp);

		int rAns = withoutFirstCharString + withoutLastCharString;

		return dp[si][ei] = (str.charAt(si) == str.charAt(ei) ? rAns + 1 : rAns - middleString);
	}

	public static int countOfPalindromicSubsubsequence_DP(String str, int si, int ei, int[][] dp) {

		for (int gap = 0; gap < str.length(); gap++) {
			for (si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (gap == 0) dp[si][ei] = 1; //length 1
				else if (str.charAt(si) == str.charAt(ei)) dp[si][ei] = dp[si + 1][ei] + dp[si][ei - 1] + 1;
				else dp[si][ei] = dp[si + 1][ei] + dp[si][ei - 1] - dp[si + 1][ei - 1];
			}
		}

		return dp[0][str.length() - 1];
	}

	public static int longestCommonSubsequnece_Rec(String str1, String str2, int i, int j, int[][] dp) {
		if (i == str1.length() || j == str2.length()) return 0;

		if (dp[i][j] != 0) return dp[i][j];

		if (str1.charAt(i) == str2.charAt(j)) return dp[i][j] = longestCommonSubsequnece_Rec(str1, str2, i + 1, j + 1, dp) + 1;

		int a = longestCommonSubsequnece_Rec(str1, str2, i + 1, j, dp);
		int b = longestCommonSubsequnece_Rec(str1, str2, i, j + 1, dp);
		return dp[i][j] = Math.max(a, b);
	}

	static int maxAnsSubstring = 0;
	public static int longestCommonSubstring_Rec(String str1, String str2, int i, int j, int[][] dp) {
		if (i == str1.length() || j == str2.length()) return 0;

		if (dp[i][j] != -1) return dp[i][j];

		int a = 0;
		if (str1.charAt(i) == str2.charAt(j)) {
			a = longestCommonSubsequnece_Rec(str1, str2, i + 1, j + 1, dp) + 1;
			maxAnsSubstring = Math.max(maxAnsSubstring, a);
		}
		longestCommonSubsequnece_Rec(str1, str2, i + 1, j, dp);
		longestCommonSubsequnece_Rec(str1, str2, i, j + 1, dp);

		return dp[i][j] = a;
	}

	public static int longestCommonSubsequnece_DP(String str1, String str2, int i, int j, int[][] dp) {

		for (i = str1.length() - 1; i >= 0; i--) {
			for (j = str2.length() - 1; j >= 0; j--) {

				if (str1.charAt(i) == str2.charAt(j)) dp[i][j] = dp[i + 1][j + 1] + 1;
				else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
			}
		}

		return dp[0][0];
	}

	public static int longestCommonSubstring_DP(String str1, String str2, int i, int j, int[][] dp) {
		int max = 0;
		for (i = str1.length() - 1; i >= 0; i--) {
			for (j = str2.length() - 1; j >= 0; j--) {

				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
					max = Math.max(dp[i][j], max);
				}
			}
		}

		return max;
	}
	
    public static void main(String[] args){
        
    }
}