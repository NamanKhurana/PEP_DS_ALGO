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

int main()
{
    int n;
    cin >> n;
    lexOrder(n, 1, 0);
}