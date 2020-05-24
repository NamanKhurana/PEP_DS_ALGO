#include <iostream>
using namespace std;

void lexOrder(int n, int idx, int prevPart)
{

    for (int i = idx; i <= 9; i++)
    {
        int num = prevPart * 10 + i;
        if (num < n)
        {
            cout << num << endl;
        }else{
            return;
        }
        lexOrder(n, 0, num);
    }
}

int numTilePossibilities(string &str)
{
    if (str.length() == 0)
    {
        return 0;
    }

    int count = 0;
    // vector<bool> vis(26, false);
    int vis = 0;
    for (int i = 0; i < str.length(); i++)
    {
        // int chIdx = str[i] - 'A';
        int mask = 1 << (str[i] - 'A');

        // if (vis[chIdx] == false)
        if ((vis & mask) == 0)
        {
            vis ^= mask;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += numTilePossibilities(nstr) + 1;
        }
    }
    return count;
}

int board[13][5] = {0};
int originalBoard[13][5] = {0};
int dir[3] = {-1, 0, 1};
int maxCoin;

void Blast(int row)
{
    for (int i = row, count = 0; count < 5 && i >= 0; count++, i--)
    {
        for (int j = 0; j < 5; j++)
        {
            if (board[i][j] == 2)
                board[i][j] = 0;
        }
    }
}

void unBlast(int row)
{
    for (int i = row, count = 0; count < 5 && i >= 0; count++, i--)
    {
        for (int j = 0; j < 5; j++)
        {
            if (originalBoard[i][j] == 2)
                board[i][j] = 2;
        }
    }
}

void oldDaysSolu(int n, int c, int coins)
{
    //   if(coins == -1) return;
    if (n == 0 || coins == -1)
    {
        maxCoin = max(maxCoin, coins);
        return;
    }

    for (int d = 0; d < 3; d++)
    {
        int y = c + dir[d];
        if (y >= 0 && y < 5)
        {
            if (board[n - 1][y] == 1)
                coins += 1;
            if (board[n - 1][y] == 2)
                coins -= 1;

            oldDaysSolu(n - 1, y, coins);

            if (board[n - 1][y] == 1)
                coins -= 1;
            if (board[n - 1][y] == 2)
                coins += 1;
        }
    }
}

void oldDays_()
{
    int n;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            int a;
            cin >> a;
            originalBoard[i][j] = board[i][j] = a;
        }
    }
    maxCoin = -1;
    for (int row = n - 1; row >= 4; row--)
    {
        Blast(row);
        oldDaysSolu(n, 2, 0);
        unBlast(row);
    }

    cout << maxCoin << endl;
}

void oldDays()
{
    int t;
    cin >> t;
    while (t-- > 0)
        oldDays_();
}

int main()
{
    int n;
    cin >> n;
    lexOrder(n, 1, 0);
}