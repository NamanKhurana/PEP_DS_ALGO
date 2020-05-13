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
    "mno",
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

vector<vector<int>> dir = {{0, 1}, {1, 1}, {1, 0}};

bool isSafe(int sr, int sc, int dr, int dc)
{
    if (sr > dr || sc > dc)
    {
        return false;
    }

    return true;
}

void printMazePaths(int sr, int sc, int dr, int dc, string ans)
{

    if (sr == dr && sc == dc)
    {
        cout << ans << " ";
        return;
    }

    for (int i = 0; i < dir.size(); i++)
    {
        int nr = sr + dir[i][0];
        int nc = sc + dir[i][1];

        if (isSafe(nr, nc, dr, dc))
        {
            if (i == 0)
                printMazePaths(nr, nc, dr, dc, ans + "H");
            if (i == 1)
                printMazePaths(nr, nc, dr, dc, ans + "D");
            if (i == 2)
                printMazePaths(nr, nc, dr, dc, ans + "V");
        }
    }
}

void printMazeMulJumps(int sr, int sc, int dr, int dc, string ans)
{
      if (sr > dr || sc > dc)
    {
        return;
    }

    if (sr == dr && sc == dc)
    {
        cout << ans << " ";
        return;
    }

    for (int i = 0; i < dir.size(); i++)
    {
        for (int jump = 1; jump <= 3; jump++)
        {
            int nr = sr + (jump * dir[i][0]);
            int nc = sc + (jump * dir[i][1]);

                if (i == 0)
                {
                    printMazeMulJumps(nr, nc, dr, dc, ans + "H"+to_string(jump));
                }
                if (i == 1)
                {
                    printMazeMulJumps(nr, nc, dr, dc, ans + "D"+to_string(jump));
                }
                if (i == 2)
                {
                    printMazeMulJumps(nr, nc, dr, dc, ans + "V"+to_string(jump));
                }
        }
    }
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
    // printNokiaPad("10", "");
    // printMazePaths(0, 0, 3, 3, "");
    printMazeMulJumps(0, 0, 3, 3, "");
}

int main()
{
    solve();
    return 0;
}
