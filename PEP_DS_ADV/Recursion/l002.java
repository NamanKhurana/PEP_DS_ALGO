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

    public static void coinChangeVariations(){
        int[] ar = {2,3,5,7};
        int tar = 10;
        // System.out.println("\n" + coinChangePermInf(ar,tar,""));
        // System.out.println("\n" + coinChangeCombInf(ar,tar,"",0));
        // System.out.println("\n" + coinChangeComb(ar,tar,"",0));
        // System.out.println("\n" + coinChangePerm(ar,tar,"",new boolean[ar.length]));
        // System.out.println("\n" + coinChangeCombSubsequenceMethod(ar,tar,"",0)); 
        System.out.println("\n" + coinChangeCombInfSubsequenceMethod(ar,tar,"",0)); 
    }

    public static void solve(){
        // floodFillVariations();
        coinChangeVariations();
    }

    public static void main(String[] args){
        solve();
    }

}