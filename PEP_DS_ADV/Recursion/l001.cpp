#include<iostream>
//link library functions with our cpp file
using namespace std;

void printIncreasingDecreasing(int a,int b){
    if(a > b){
        return;
    }

    cout<<a<<" ";
    printIncreasingDecreasing(a+1,b);
    if(a!=b)
    cout<<a<<" ";
}

void solve(){
    int a,b;
    cin>>a>>b;
    printIncreasingDecreasing(a,b);
}

int main(){
    solve();
    return 0;
}
