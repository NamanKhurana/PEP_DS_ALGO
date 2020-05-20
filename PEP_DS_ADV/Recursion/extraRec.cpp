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

int main()
{
    int n;
    cin >> n;
    lexOrder(n, 1, 0);
}