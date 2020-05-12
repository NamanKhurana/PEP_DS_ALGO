#include <iostream>
#include <vector>
//link library functions with our cpp file
using namespace std;

vector<string> words = {
    ":;/",
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",KT
    "pqrs",
    "tuv",
    "wxyz",
    "&*%",
    "#@$",
};

void printIncreasingDecreasing(int a, int b)
{
    if (a > b)
    {
        return;
    }

    cout << a << " ";
    printIncreasingDecreasing(a + 1, b);
    if (a != b)
        cout << a << " ";
}

void printEvenOdd(int a, int b)
{
    if (a > b)
    {
        return;
    }
    if (a & 1)
    {
        cout << a << " ";
    }
    printEvenOdd(a + 1, b);
    if (a != b && a % 2 == 0)
    {
        cout << a << " ";
    }
}

void printNokiaPad(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << " ";
        // cout<<endl;
        return;
    }

    // cout<<"HELLO";
    int ch = str[0] - '0';
    int ch1 = str[1] - '0';
    int num = ch * 10 + ch1;
    for (int i = 0; i < words[ch].length(); i++)
    {
        printNokiaPad(str.substr(1), ans + words[ch][i]);
    }

    if (num == 10)
    {
        for (int i = 0; i < words[num].length(); i++)
        {
            printNokiaPad(str.substr(2), ans + words[num][i]);
        }
    }
}

vector<string> getNokiaPad(string str)
{
    if (str.length() == 0)
    {
        vector<string> baseCase;
        baseCase.push_back("");
        return baseCase;
    }

    int ch = str[0] - '0';

    vector<string> myRes;
    vector<string> recRes = getNokiaPad(str.substr(1));

    for (int i = 0; i < words[ch].length(); i++)
    {
        for (string s : recRes)
        {
            myRes.push_back(words[ch][i] + s);
        }
    }
    return myRes;
}

void solve()
{
    // int a,b;
    // cin>>a>>b;
    // printIncreasingDecreasing(a,b);
    // cout<<endl;
    // printEvenOdd(a,b);
    // cout<<endl;
    // vector<string> ans = getNokiaPad("123");
    // for(int i = 0;i<ans.size();i++){
    //     cout<<ans[i]<<" ";
    // }
    printNokiaPad("10", "");
}

int main()
{
    solve();
    return 0;
}
