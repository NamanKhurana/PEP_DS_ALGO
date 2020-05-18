#include <iostream>
#include <vector>
using namespace std;

int queenCombination1D(vector<bool> &boxes, int qpsf, int tnq, int idx, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = idx; i < boxes.size(); i++)
    {
        count += queenCombination1D(boxes, qpsf + 1, tnq - 1, i + 1, ans + "B" + to_string(i + 1) + "Q" + to_string(qpsf + 1) + " ");
    }
    return count;
}

int queenCombination1DSub(vector<bool> &boxes, int qpsf, int tnq, int idx, string ans)
{

    if (idx == boxes.size() + 1)
    {
        return 0;
    }

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    count += queenCombination1DSub(boxes, qpsf + 1, tnq - 1, idx + 1, ans + "B" + to_string(idx + 1) + "Q" + to_string(qpsf + 1) + " ");
    count += queenCombination1DSub(boxes, qpsf, tnq, idx + 1, ans);
    return count;
}

int queenPermutation1D(vector<bool> &boxes, int qpsf, int tnq, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenPermutation1D(boxes, qpsf + 1, tnq - 1, ans + "B" + to_string(i + 1) + "Q" + to_string(qpsf + 1) + " ");
            boxes[i] = false;
        }
    }
    return count;
}

int queenPermutation1DSub(vector<bool> &boxes,int idx,int qpsf, int tnq, string ans)
{

    if (idx == boxes.size())
    {
        return 0;
    }

    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (!boxes[idx])
    {
        boxes[idx] = true;
        count += queenPermutation1DSub(boxes, 0, qpsf + 1, tnq, ans + "B" + to_string(idx+1) + "Q" + to_string(qpsf+1) + " ");
        boxes[idx] = false;
    }
    count += queenPermutation1DSub(boxes, idx + 1, qpsf, tnq, ans);

    return count;

}

void queenSet()
{
    vector<bool> boxes(5, false);
    // cout<<queenCombination1D(boxes,0,3,0,"");
    // cout<<queenCombination1DSub(boxes,0,3,0,"");
    // cout << queenPermutation1D(boxes, 0, 3, "");
    // cout<<endl<<endl<<endl;
    cout << queenPermutation1DSub(boxes, 0, 0,3, "");
}

void solve()
{
    queenSet();
}

int main()
{
    solve();
}