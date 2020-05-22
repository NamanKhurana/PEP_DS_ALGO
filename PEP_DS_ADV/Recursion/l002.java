import java.util.Scanner;
import java.util.ArrayList;

public class l002{
    
    public static Scanner sc = new Scanner(System.in);

    static int[][] floodDir = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
    static String[] dirName = {"R", "RiDi", "Do", "LiDi", "L", "UpLeDi", "T", "ToRiDi"};


    public static boolean isSafe(int sr,int sc,int er,int ec,int[][] board){
        if(sr < 0 || sc < 0 || sr > er || sc > ec || board[sr][sc] == 1){
            return false;
        }

        return true;
    }

    public static int floodFill_height(int sr,int sc,int er,int ec,int[][] board){
        if(sr == sc && er == sc){
            return 0;
        }

        board[sr][sc] = 1;
        int maxHeight = 0;

        for(int i = 0;i<floodDir.length;i++){
            int nr = sr + floodDir[i][0];
            int nc = sc + floodDir[i][1];

            if(isSafe(nr,nc,er,ec,board))
            {
                int recHeight = floodFill_height(nr,nc,er,ec,board);
                maxHeight = Math.max(maxHeight,recHeight);
            }
        }

        board[sr][sc] = 0;
        return maxHeight + 1;

    }

    static class pair{
        int len = 0;
        String str = "";

        pair(int len,String str){
            this.len = len;
            this.str = str;
        }
    }

    public static pair floodFill_LongestPath(int sr,int sc,int er,int ec,int[][] board){
        if(sr == er && sc == ec){
            return new pair(0,"");
        }

        pair myPair = new pair(0,"");
        board[sr][sc] = 1;

        for(int i = 0;i<floodDir.length;i++){
            int nr = sr + floodDir[i][0];
            int nc = sc + floodDir[i][1];

            if(isSafe(nr,nc,er,ec,board))
            {
                pair recP = floodFill_LongestPath(nr,nc,er,ec,board);
                if(recP.len + 1 > myPair.len)
                {
                    myPair.len = recP.len + 1;
                    myPair.str = dirName[i] + recP.str; 
                }
            }
        }
        
        board[sr][sc] = 0;
        return myPair;
    }

    public static pair floodFill_ShortestPath(int sr,int sc,int er,int ec,int[][] board){
        if(sr == er && sc == ec){
            return new pair(0,"");
        }

        // pair myPair = new pair((int)1e7,"");
        pair myPair = new pair(Integer.MAX_VALUE,"");
        board[sr][sc] = 1;
      for(int i = 0;i<floodDir.length;i++){
            int nr = sr + floodDir[i][0];
            int nc = sc + floodDir[i][1];

            if(isSafe(nr,nc,er,ec,board))
            {
                pair recP = floodFill_ShortestPath(nr,nc,er,ec,board);
                if(recP.len != Integer.MAX_VALUE && recP.len + 1 < myPair.len)
                {
                    myPair.len = recP.len + 1;
                    myPair.str = dirName[i] + recP.str; 
                }
            }
        }
        
        board[sr][sc] = 0;
        return myPair;
    }

    public static void floodFillVariations(){
        int[][] board = new int[4][4];
        // int ans = floodFill_height(0,0,3,3,board);
        pair p1 = floodFill_LongestPath(0,0,3,3,board);
        System.out.println(p1.str + "==>" + p1.len);
        pair p2 = floodFill_ShortestPath(0,0,3,3,board);
        System.out.println(p2.str + "==>" + p2.len);
    }

    /**********************************************************************/

    public static int coinChangePermInf(int[] ar,int tar,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0;i<ar.length;i++){
            if(tar-ar[i]>=0){
                count+=coinChangePermInf(ar,tar-ar[i],ans+ar[i]);
            }
        }
        return count;
    }

    public static int coinChangeCombInf(int[] ar,int tar,String ans,int idx){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx;i<ar.length;i++){
            if(tar-ar[i]>=0){
                count+=coinChangeCombInf(ar,tar-ar[i],ans+ar[i],i);
            }
        }
        return count;
    }

    public static int coinChangeComb(int[] ar,int tar,String ans,int idx){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx;i<ar.length;i++){
            if(tar-ar[i] >= 0){
                count+=coinChangeComb(ar,tar-ar[i],ans+ar[i],i+1);
            }
        }

        return count;
    }

    public static int coinChangePerm(int[] ar,int tar,String ans,boolean[] vis){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0;i<ar.length;i++){
            if(!vis[i]){
            vis[i] = true;
            if(tar-ar[i]>=0)
            {
                count+=coinChangePerm(ar,tar-ar[i],ans+ar[i],vis);
            }
            vis[i] = false;
            }
        }
        return count;
    }

    public static int coinChangeCombSubsequenceMethod(int[] ar,int tar,String ans,int idx){
        if(tar == 0 || idx == ar.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - ar[idx] >= 0)
        count+=coinChangeCombSubsequenceMethod(ar,tar-ar[idx],ans+ar[idx],idx+1);
        count+=coinChangeCombSubsequenceMethod(ar,tar,ans,idx+1);
        return count;
    }

    public static int coinChangeCombInfSubsequenceMethod(int[] ar,int tar,String ans,int idx){
        if(tar == 0 || idx == ar.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - ar[idx] >= 0)
        count+=coinChangeCombInfSubsequenceMethod(ar,tar-ar[idx],ans+ar[idx],idx);
        count+=coinChangeCombInfSubsequenceMethod(ar,tar,ans,idx+1);
        return count;
    }

    public static int coinChangePermInfSubsequenceMethod(int[] arr,int tar,String ans,int idx) {
		if (idx == arr.length || tar == 0) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}

			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinChangePermInfSubsequenceMethod(arr,tar - arr[idx], ans + arr[idx] + " ",0);
		count += coinChangePermInfSubsequenceMethod(arr,tar, ans,idx+1);

		return count;
	}

	public static int coinChangePermSubsequenceMethod(int[] arr, boolean[] vis, int idx, int tar, String ans) {
		if (idx == arr.length || tar == 0) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}

			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0 && !vis[idx]) {
			vis[idx] = true;
			count += coinChangePermSubsequenceMethod(arr, vis, 0, tar - arr[idx], ans + arr[idx] + " ");
			vis[idx] = false;
		}

		count += coinChangePermSubsequenceMethod(arr, vis, idx + 1, tar, ans);

		return count;
	}

    public static void coinChangeVariations(){
        int[] ar = {2,3,5,7};
        int tar = 10;
        // System.out.println("\n" + coinChangePermInf(ar,tar,""));
        // System.out.println("\n" + coinChangeCombInf(ar,tar,"",0));
        // System.out.println("\n" + coinChangeComb(ar,tar,"",0));
        // System.out.println("\n" + coinChangePerm(ar,tar,"",new boolean[ar.length]));
        // System.out.println("\n" + coinChangeCombSubsequenceMethod(ar,tar,"",0)); 
        // System.out.println("\n" + coinChangeCombInfSubsequenceMethod(ar,tar,"",0));
        // System.out.println("\n" + coinChangePermInfSubsequenceMethod(ar,tar,"",0));
        System.out.println("\n" + coinChangePermSubsequenceMethod(ar,new boolean[ar.length],0,tar,""));
         
    }

/***************************************************************************************/
    
    public static boolean isSafeToPlaceNumber(int[][] board,int r,int c,int num){
        
        for(int i = 0;i<board.length;i++){
            if(board[r][i] == num || board[i][c] == num){
                return false;
            }
        }

        //(i/Math.sqrt(board[0].length)*Math.sqrt(board[0].length
        int x = (r/3)*3;
        int y = (c/3)*3;

        for(int i = x ;i<x+3;i++){
            for(int j = y;j<y+3;j++){
                if(board[i][j] == num){
                    return false;
                }
            }
        }

        return true;
    }

    public static int sudoku_01_sub(int[][] board,int idx){
        if(idx == 81)
        {
            for(int i = 0;i<board.length;i++){
                for(int j = 0;j<board[0].length;j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }  

            System.out.println(); 

        // for(int[] ar: arr){
        //     for(int ele: ar){
        //         System.out.print(ele + " ");
        //     }
        //     System.out.println();
        // }
            return 1;
        } 

        int x = idx/board[0].length;
        int y = idx%board[0].length;

        int count = 0;

        if(board[x][y] == 0){
            for(int i = 1;i<=9;i++){
                if(isSafeToPlaceNumber(board,x,y,i)){
                    board[x][y] = i;
                    count+=sudoku_01_sub(board,idx+1);
                    board[x][y] = 0;
                }
            }
        }else{
            count+=sudoku_01_sub(board,idx+1);
        }

        return count;
    }

    //gives one solution
    public static boolean sudoku_02(int[][] board, int idx) {
		if (idx == 81) {
            for(int i = 0;i<board.length;i++){
                for(int j = 0;j<board[0].length;j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }  
			return true;
		}

		int r = idx / 9;
		int c = idx % 9;
        boolean res = false;
        
		if (board[r][c] == 0) {
			for (int num = 1; num <= 9 ; num++) {
				if (isSafeToPlaceNumber(board, r, c, num)) {

					board[r][c] = num;
                    //if res is true then it wont call the function again
                    res= res || sudoku_02(board, idx + 1);
					board[r][c] = 0;
                }
			}
		} else res= res || sudoku_02(board, idx + 1);

		return res;
    }

    //avoid non-zero number calls
    public static boolean sudoku_03(int[][] board,ArrayList<Integer> calls, int idx) {
		if (idx == calls.size()) {
            for(int i = 0;i<board.length;i++){
                for(int j = 0;j<board[0].length;j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }  
			return true;
		}

        //get 2-D board index from 1-D array index
		int r = calls.get(idx) / 9;
		int c = calls.get(idx) % 9;
        boolean res = false;
        
		if (board[r][c] == 0) {
			for (int num = 1; num <= 9 ; num++) {
				if (isSafeToPlaceNumber(board, r, c, num)) {

					board[r][c] = num;
                    //if res is true then it wont call the function again
                    res= res || sudoku_03(board,calls,idx + 1);
					board[r][c] = 0;
                }
			}
		}

		return res;
    }

    static int[] row;
    static int[] col;
    static int[][] mat;

    public static int sudoku_04_bits(int[][] board,ArrayList<Integer> calls, int idx) {
		if (idx == calls.size()) {
            for(int i = 0;i<board.length;i++){
                for(int j = 0;j<board[0].length;j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }  
            System.out.println();
			return 1;
		}

        //get 2-D board index from 1-D array index
		int r = calls.get(idx) / 9;
		int c = calls.get(idx) % 9;
        int count = 0;
        
		for (int num = 1; num <= 9 ; num++) {
            int mask=(1<<num);
            if ((row[r]&mask)==0 && (col[c]&mask)==0 && (mat[r/3][c/3]&mask)==0) {

                board[r][c] = num;
                row[r]^=mask;
                col[c]^=mask;
                mat[r/3][c/3]^=mask;
            
                count+= sudoku_04_bits(board,calls ,idx + 1);
            
                board[r][c] = 0;
                row[r]^=mask;
                col[c]^=mask;
                mat[r/3][c/3]^=mask;
            
            }
		}
		return count;
    }

//LEETCODE 36---------------------------------------------------------------------------------
    static int[] row;   
    static int[] col;
    static int[][] mat;
    
    public boolean isValidSudoku(char[][] board) {
        
        row = new int[board.length];
        col = new int[board[0].length];
        mat = new int[board.length/3][board[0].length];
        
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(board[i][j]!='.'){
                     int mask=(1<<(board[i][j]-'0'));
                     if ((row[i]&mask)==0 && (col[j]&mask)==0 && (mat[i/3][j/3]&mask)==0) {
                           row[i]^=mask;
                           col[j]^=mask;
                           mat[i/3][j/3]^=mask;
                     }else{
                         return false;
                     }
                }
            }
        }
        return true;
    }
//-----------------------------------------------------------------------------------------------

    public static void sodukuVariations(){
     
    int[][] board =  {{3, 0, 0, 0, 0, 0, 0, 0, 0},  
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},  
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};  

    // System.out.println(sudoku_01_sub(board,0));
    // System.out.println(sudoku_02(board,0));
    
    //*to avoid function calls from non-zero numbers in sudoku grid
    ArrayList<Integer> calls = new ArrayList<>();

    row = new int[9];
    col = new int[9];
    mat = new int[3][3];

    //*to get 1-D index of numbers from 2-D grid
    for(int i = 0;i<9;i++){
        for(int j = 0;j<9;j++){
            if(board[i][j]==0){
                    calls.add((i*9 + j));
                }else{
                    int mask=(1<<board[i][j]);
                    row[i]^=mask;
                    col[j]^=mask;
                    mat[i/3][j/3]^=mask; 
                    
                }
        }
    }
    // System.out.println(sudoku_03(board,calls,0));
    System.out.println(sudoku_04_bits(board,calls,0));
    }

    public static void solve(){
        // floodFillVariations();
        // coinChangeVariations();
        sodukuVariations();
    }

    public static void main(String[] args){
        solve();
    }

}