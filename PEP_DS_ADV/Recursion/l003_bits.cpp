#include <iostream>
#include<vector>
using namespace std;

int setKthBit(int n, int k)
{
    return n | (1 << k);
}

int unsetKthBit(int n, int k)
{
    return n & (~(1 << k));
}

int noOfSetBits_01(int n)
{
    int count = 0;
    while (n != 0)
    {
        if (n & 1)
        {
            count++;
        }
        n >>= 1;
    }

    return count;
}

int noOfSetBits_02(int n)
{
    int count = 0;
    while (n != 0)
    {
        count++;
        n &= (n - 1);
    }
    return count;
}


vector<int> countBits(int num)
{
    vector<int> result;
    result[0] = 0;

    for (int i = 1; i <= num; i++)
    {
        result.push_back((i & 1) + result[i >> 1]);
    }

    return result;
}

int main()
{
    return 0;
}