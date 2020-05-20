#include <iostream>
#include <vector>
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

//LEETCODE 191
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

//LEETCODE 338
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

//LEETCODE 137
int singleNumberII(vector<int> &nums)
{
    int ans = 0;
    int k = 3;
    for (int i = 0; i < 32; i++)
    {
        int count = 0;
        int mask = (1 << i);
        for (int ele : nums)
        {
            if (ele & mask)
            {
                count++;
            }
        }
        if (count % k != 0)
        {
            ans |= mask;
        }
    }
    return ans;
}

//LEETCODE 260
vector<int> singleNumberIII(vector<int> &nums)
{
    int xorNum = 0;
    for (int ele : nums)
        xorNum ^= ele;

    int firstLSB = xorNum & (~(xorNum - 1)); //(xorNum & (-xorNum))
    int a = 0;
    int b = 0;
    for (int ele : nums)
    {
        if ((firstLSB & ele) != 0)
            a ^= ele;
        else
            b ^= ele;
    }

    return {a, b}; // java: new int[]{a,b};
}

//LEETCODE 231
bool isPowerOfTwo(int n)
{
    return (n > 0 && (n & (n - 1)) == 0);
}

//LEETCODE 342
bool isPowerOfFour(int n)
{
    if (n > 0 && !(n & (n - 1))) // is num power of 2
    {
        int count = 0; // count of all zeros after 1.
        while (n > 1)
        {
            count++;
            n >>= 1;
        }

        if ((count & 1) == 0)
            return true; // count of zeros after 1 should be a even number.
    }

    return false;
}

int main()
{
    return 0;
}