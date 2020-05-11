import java.util.Scanner;

public class l001{

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
        // int n = sc.nextInt();
        // System.out.println(fact(n));
        
        // int a = sc.nextInt();
        // int b = sc.nextInt();
        // System.out.println(power(a,b));

        System.out.print(checkRecFlow(5));
    }

    public static int fact(int n){
        if(n == 0){
            return 1;
        }
        int res = fact(n-1);
        return n*res;
    }

    public static int power(int a,int b){
        if(b == 0){
            return 1;
        }

        int smallAns = power(a,b/2);
        if(b%2 == 0){
            return smallAns*smallAns;
        }else{
            return a*smallAns*smallAns;
        }
    }

    public static int checkRecFlow(int a){
        if(a <= 1){
            System.out.println("Base" + a);
            return a+1;
        }
        int count = 0;
        System.out.println("Pre" + a);
        count+=checkRecFlow(a-1);
        System.out.println("In" + a);
        count+=checkRecFlow(a-2);
        System.out.println("Pos" + a);

        return count;
    }
}