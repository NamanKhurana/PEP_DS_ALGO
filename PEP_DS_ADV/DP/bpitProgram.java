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

	public static int editDistance(String str1, String str2) {
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0) dp[i][j] = j;
				else if (j == 0) dp[i][j] = i;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
			}
		}

		return dp[str1.length()][str2.length()];
	}

	public static int string_occurs_given_string(String str1, String str2, int n, int m, int[][] dp) {
		if (m == 0) return dp[n][m] = 1;
		if (m > n) return 0;

		if (dp[n][m] != 0) return dp[n][m];

		if (str1.charAt(n - 1) == str2.charAt(m - 1)) return dp[n][m] = string_occurs_given_string(str1, str2, n - 1, m - 1, dp) + string_occurs_given_string(str1, str2, n - 1, m, dp);

		return dp[n][m] = string_occurs_given_string(str1, str2, n - 1, m, dp);
	}

	public static int string_occurs_given_string_02(String str1, String str2, int i, int j, int[][] dp) {
		if (j == str2.length() || (i == str1.length() && j == str2.length())) return dp[i][j] = 1;
		if (i == str1.length() || (str2.length() - j) > (str1.length() - i)) return 0;

		if (dp[i][j] != 0) return dp[i][j];

		if (str1.charAt(i) == str2.charAt(j)) return dp[i][j] = string_occurs_given_string_02(str1, str2, i + 1, j + 1, dp) + string_occurs_given_string_02(str1, str2, i + 1, j, dp);

		return dp[i][j] = string_occurs_given_string_02(str1, str2, i + 1, j, dp);
	}

	public static int string_occurs_given_string_DP(String str1, String str2, int i, int j, int[][] dp) {
		for (i = str1.length(); i >= 0; i--) {
			for (j = str2.length(); j >= 0; j--) {
				if (j == str2.length() || (i == str1.length() && j == str2.length())) {
					dp[i][j] = 1;
					continue;
				}
				if (i == str1.length() || (str2.length() - j) > (str1.length() - i)) continue;

				if (str1.charAt(i) == str2.charAt(j)) dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
				else dp[i][j] = dp[i + 1][j];
			}
		}

		return dp[0][0];
	}

    //targetSet.===================================================================

	public static int coinChangePermuatation_Rec(int[] coins, int tar) {
		if (tar == 0) return 1;

		int count = 0;
		for (int c: coins) {
			if (tar - c >= 0) count += coinChangePermuatation_Rec(coins, tar - c);
		}

		return count;
	}

	public static int coinChangePermuatation(int[] coins, int tar) {

		int[] dp = new int[tar + 1];
		dp[0] = 1;

		for (int t = 0; t <= tar; t++) {
			for (int c: coins) {
				if (t - c >= 0) {
					dp[t] += dp[t - c];
				}
			}
		}

		return dp[tar];
	}

	public static int linearEquationOfNvariables(int[] coeff, int y) {
		return coinChangeCombination(coeff, y);
	}

	public static int targetSum(int[] arr, int tar, int idx, int[][] dp) { // dp: arr X tar
		if (tar == 0 || idx == arr.length) {
			return dp[idx][tar] = tar == 0 ? 1 : 0;
		}

		if (dp[idx][tar] != 0) return dp[idx][tar];

		int count = 0;
		if (tar - arr[idx] >= 0) count += targetSum(arr, tar - arr[idx], idx + 1, dp);

		count += targetSum(arr, tar, idx + 1, dp);

		return dp[idx][tar] = count;
	}

	public static int targetSum_DP(int[] arr, int tar, int[][] dp) { // dp: arr X tar
		dp[0][0] = 1;

		for (int idx = 1; idx < dp.length; idx++) {
			for (int t = 0; t <= tar; t++) {

				int count = 0;
				if (t - arr[idx - 1] >= 0) {
					count += dp[idx - 1][t - arr[idx - 1]];
				}
				count += dp[idx - 1][t];

				dp[idx][t] = count;
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	int knapsack_01_rec(int[] weight, int[] value, int w, int idx, int[][] dp) { // dp: weight X W
		if (w == 0 || idx == weight.length) {
			return 0;
		}
		if (dp[idx][w] != 0) return dp[idx][w];

		int pick = 0;
		if (w - weight[idx] >= 0) {
			pick = knapsack_01_rec(weight, value, w - weight[idx], idx + 1, dp) + value[idx];
		}

		int notPicked = knapsack_01_rec(weight, value, w, idx + 1, dp);

		return dp[idx][w] = Math.max(pick, notPicked);
	}

	int knapsack_01_rec_01(int[] weight, int[] value, int w, int n, int[][] dp) { // dp: weight X W, n is size of weight
		if (w == 0 || n == 0) {
			return 0;
		}
		if (dp[n][w] != 0) return dp[n][w];

		int pick = 0;
		if (w - weight[n - 1] >= 0) {
			pick = knapsack_01_rec(weight, value, w - weight[n - 1], n - 1, dp) + value[n - 1];
		}

		int notPicked = knapsack_01_rec(weight, value, w, n - 1, dp);

		return dp[n][w] = Math.max(pick, notPicked);
	}

	int knapsack_01_rec_DP(int[] weight, int[] value, int W, int[][] dp) { // dp: weight X W, n is size of weight
		dp[0][0] = 0;
		for (int idx = 1; idx < dp.length; idx++) {
			for (int w = 0; w <= W; w++) {

				int pick = 0;
				if (w - weight[idx - 1] >= 0) {
					pick = dp[idx - 1][w - weight[idx - 1]] + value[idx - 1];
				}

				int notPicked = dp[idx - 1][w];

				dp[idx][w] = Math.max(pick, notPicked);
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	int unBoundedKnapsack(int[] weight, int[] value, int W) {
		int[] dp = new int[W + 1];

		for (int idx = 0; idx < weight.length; idx++) {
			for (int w = 1; w <= W; w++) {
                int picked=0;
				if (w - weight[idx] >= 0) picked = dp[w - weight[idx]] + value[idx];
				int unPicked = dp[w];

				dp[w] = Math.max(picked, unPicked);
			}
		}

		return dp[W];
	}

	
	//leetcode: decode ways 91.======================================================
	public static int numDecodings_Rec(String s,int idx,int[] dp) {
		if(idx==s.length()) return dp[idx]=1;
		
		char ch=s.charAt(idx);
		int count=0;
		
		if(dp[idx]!=0) return dp[idx];

		if(ch!='0')
		   count+=numDecodings_Rec(s,idx+1,dp);

		if(idx+1<s.length()){
			char ch2=s.charAt(idx+1);
			int val=(ch-'0')*10 + (ch2-'0');
			  if(val>=10 && val<=26)
			    count+=numDecodings_Rec(s,idx+2,dp);
		}

		return dp[idx]=count;		
	}
	
	public static int numDecodings_DP(String s,int[] dp) {
		for(int idx=s.length();idx>=0;idx--){

			if(idx==s.length()){
				dp[idx]=1;
				continue;
			}

			char ch=s.charAt(idx);
			int count=0;
			if(ch!='0')
			   count+=dp[idx+1];
	
			if(idx+1<s.length()){
				char ch2=s.charAt(idx+1);
				int val=(ch-'0')*10 + (ch2-'0');
				  if(val>=10 && val<=26)
					count+=dp[idx+2];
			}
	
		    dp[idx]=count;	
		}

		return dp[0];
    }

	//MISCELLANEOUS
	public static int distinctSubseqII(String S) {
		int[] dp=new int[S.length()+1];
		int[] lastSeen=new int[26];
		dp[0]=1;
		int mod=(int)1e9+7;

		for(int i=1;i<dp.length();i++){
			dp[i] = (dp[i-1] * 2) % mod;
			
			int idx=S.charAt(i-1)-'a';
			if(lastSeen[idx]!=0)
				dp[i] -= dp[lastSeen[idx]-1];
			
				dp[i]%=mod;
			lastSeen[idx]=i;
		}

		int ans = --dp[S.length()];
		if(ans<0) ans+=mod;
		return ans;
	}
	
	//leetcode 338.=========================================================================
	
	public static int[] countingBits(int n){
		int[] ans=new int[n+1];
		for(int i=1;i<=n;i++){
         ans[i] = ans[( i & (i-1) )]+1;
		}
		return ans;
	}

	//geeks: count-subsequences-of-type-ai-bj-ck=========================================

	public static int countSubsequnence_AiBjCk(String str){
		int emptyCount=1;
		int aCount=0;
		int bCount=0;
		int cCount=0;

		for(int i=0;i<str.length();i++){
			char ch=str.charAt(i);
			if(ch=='a')
			  aCount = aCount + (emptyCount + aCount);  // exclude + include
			
			if(ch=='b')
			  bCount = bCount + (aCount + bCount);  // exclude + include
			
			if(ch=='c')
			  cCount = cCount + (bCount + cCount);  // exclude + include
		}

		return cCount;
	}

	// https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad/0

	public static int numericKeyPad_Rec(int n,int idx,int[][] dp,int[][] keyPad,int[][] dirA){
		if(n==1) return dp[n][idx] = 1;  
		
		if(dp[n][idx]!=0) return dp[n][idx];
		int r = idx / 3;
		int c = idx % 3;
		int count=0;
		for(int d=0;d<dirA.length; d++){
			int x = r + dirA[d][0];
			int y = c + dirA[d][1]; 
			if(x >= 0 && y >= 0 && x < 4 && y < 3 && keyPad[x][y] !=-1){
				count+=numericKeyPad_Rec(n-1, x*3+y, dp, keyPad, dirA);  // count+=dp[n-1][x*3+y];
			}
		}

		return dp[n][idx] = count;
	}

	public static void numericKeyPad(int n){
		int[][] dp =new int[n+1][12]; 
		int[][] keyPad ={
						{1,2,3},
						{4,5,6},
						{7,8,9},
						{-1,0,-1}};
		int[][] dirA={{0,0},{1,0},{0,1},{-1,0},{0,-1}};

		int sum=0;
		for(int i=0;i<4;i++){
			for(int j=0;j<3;j++){
				if(keyPad[i][j]!=-1){
					sum+=numericKeyPad_Rec(n , i * 3 + j,dp,keyPad, dirA);
				}
			}
		}

		return sum;
	}

	public static int numericKeyPad_DP(int N){   // N = 3 -> 138
		int[][] dp =new int[N+1][12]; 
		int[][] keyPad ={
						{1,2,3},
						{4,5,6},
						{7,8,9},
						{-1,0,-1}};
		int[][] dirA={{0,0},{1,0},{0,1},{-1,0},{0,-1}};

		int sum=0;
		for(int n=1;n<4;n++){
			for(int idx=0;idx<12;idx++){
				int r = idx / 3;
				int c = idx % 3;
				
				if(keyPad[r][c]==-1) continue;
				
				if(n==1){
					dp[n][idx] = 1;
					continue;
				}  
	
		        int count=0;
		        for(int d=0;d<dirA.length; d++){
			        int x = r + dirA[d][0];
			         int y = c + dirA[d][1]; 
			         if(x >= 0 && y >= 0 && x < 4 && y < 3 && keyPad[x][y] !=-1){
				        count+=dp[n-1][x*3+y];
			    }
		    }

		     dp[n][idx] = count;
			}
		}

		for(int i=0;i<12;i++){
			sum+=dp[N][i];
		}

		return sum;
	}


    public static void main(String[] args){
        
    }
}