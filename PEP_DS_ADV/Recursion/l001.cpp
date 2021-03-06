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
                printMazeMulJumps(nr, nc, dr, dc, ans + "H" + to_string(jump));
            }
            if (i == 1)
            {
                printMazeMulJumps(nr, nc, dr, dc, ans + "D" + to_string(jump));
            }
            if (i == 2)
            {
                printMazeMulJumps(nr, nc, dr, dc, ans + "V" + to_string(jump));
            }
        }
    }
}

//!-------------------------------------------------------------------
//* void printMazeMulJumps(int sr, int sc, int dr, int dc, string ans)
// {
//     if (sr == dr && sc == dc)
//     {
//         cout << ans << " ";
//         return;
//     }

//     for (int i = 0; i < dir.size(); i++)
//     {
//         for (int jump = 1; jump <= 3; jump++)
//         {
//             int nr = sr + (jump * dir[i][0]);
//             int nc = sc + (jump * dir[i][1]);

//             if (isSafe(nr, nc, sr, sc))
//             {
//                 if (i == 0)
//                 {
//                     printMazeMulJumps(nr, nc, dr, dc, ans + "H");
//                 }
//                 if (i == 1)
//                 {
//                     printMazeMulJumps(nr, nc, dr, dc, ans + "D");
//                 }
//                 if (i == 2)
//                 {
//                     printMazeMulJumps(nr, nc, dr, dc, ans + "V");
//                 }
//             }
//         }
//     }
//* }
//!--------------------------------------------------------------------------

vector<string> getMazePaths(int sr, int sc, int dr, int dc)
{
    if (sr == dr && sc == dc)
    {
        vector<string> baseName;
        baseName.push_back("");
        return baseName;
    }

    vector<string> ans;

    for (int jump = 1; jump <= 3; jump++)
    {
        if (sc + jump <= dc)
        {
            vector<string> horizontal = getMazePaths(sr, sc + jump, dr, dc);
            for (string s : horizontal)
            {
                ans.push_back("H" + to_string(jump) + s);
            }
        }
    }

    for (int jump = 1; jump <= 3; jump++)
    {
        if (sr + jump <= dr)
        {
            vector<string> vertical = getMazePaths(sr + jump, sc, dr, dc);
            for (string s : vertical)
            {
                ans.push_back("V" + to_string(jump) + s);
            }
        }
    }

    for (int jump = 1; jump <= 3; jump++)
    {
        if (sr + jump <= dr && sc + jump <= dc)
        {
            vector<string> diagonal = getMazePaths(sr + jump, sc + jump, dr, dc);
            for (string s : diagonal)
            {
                ans.push_back("D" + to_string(jump) + s);
            }
        }
    }

    return ans;
}

vector<vector<int>> floodDir = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
vector<string> dirName = {"R", "RiDi", "Do", "LiDi", "L", "UpLeDi", "T", "ToRiDi"};
// int jump = max(er,ec);
int jump = 1;

bool isValid(int sr, int sc, int er, int ec, vector<vector<bool>> &vis)
{
    if (sr < 0 || sr > er || sc < 0 || sc > ec || vis[sr][sc])
    {
        return false;
    }
    return true;
}

int floodFillMul(int sr, int sc, int er, int ec, vector<vector<bool>> &vis, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << " ";
        return 1;
    }

    vis[sr][sc] = true;
    int count = 0;
    for (int i = 0; i < floodDir.size(); i++)
    {
        // for(int jump = 1;jump<=3;jump++){
        int nr = sr + jump * floodDir[i][0];
        int nc = sc + jump * floodDir[i][1];
        if (isValid(nr, nc, er, ec, vis))
        {
            count += floodFillMul(nr, nc, er, ec, vis, ans + dirName[i]);
        }
        // }
    }
    vis[sr][sc] = false;
    return count;
}

bool isValidIsl(int sr, int sc, int er, int ec, vector<vector<char>> &grid)
{
    if (sr < 0 || sr > er || sc < 0 || sc > ec || grid[sr][sc] == '2' || grid[sr][sc] == '0')
    {
        return false;
    }
    return true;
}

void dfs(int sr,int sc,int er,int ec,vector<vector<char>> &grid){
    if (sr == er && sc == ec)
    {
        return;
    }

    grid[sr][sc] = '2';
    int count = 0;
    for (int i = 0; i < floodDir.size(); i++)
    {
        // for(int jump = 1;jump<=3;jump++){
        int nr = sr + floodDir[i][0];
        int nc = sc + floodDir[i][1];
        if (isValidIsl(nr, nc, er, ec, grid))
        {
            dfs(nr, nc, er, ec, grid);
        }
        // }
    }
}

//LEETCODE 200
int numIslands(vector<vector<char>> &grid)
{
    int count = 0;
    for(int i = 0;i<grid.size();i++){
        for(int j = 0;j<grid[0].size();j++){
            if(grid[i][j] == '1'){
                count++;
                dfs(i,j,grid.size()-1,grid[0].size()-1,grid);
            }
        }
    }
    return count;
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
    // printMazeMulJumps(0, 0, 3, 3, "");
    // vector<string> v = getMazePaths(0, 0, 3, 3);
    // for (int i = 0; i < v.size(); i++)
    // {
    //     cout << v[i] << " ";
    // }

    vector<vector<bool>> vis(3, vector<bool>(3, false));
    cout << "\n" << floodFillMul(0, 0, 2, 2, vis, "");
}

int main()
{
    solve();
    return 0;
}
