import java.util.Scanner;

public class l001{

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
        int n = sc.nextInt();
        System.out.println(fact(n));
    }

    public static int fact(int n){
        if(n == 0){
            return 1;
        }
        int res = fact(n-1);
        return n*res;
    }
}