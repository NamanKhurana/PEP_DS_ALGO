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

    public static void solve(){
        floodFillVariations();
    }

    public static void main(String[] args){
        solve();
    }

}