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

int queenPermutation1DSub(vector<bool> &boxes, int idx, int qpsf, int tnq, string ans)
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
        count += queenPermutation1DSub(boxes, 0, qpsf + 1, tnq, ans + "B" + to_string(idx + 1) + "Q" + to_string(qpsf + 1) + " ");
        boxes[idx] = false;
    }
    count += queenPermutation1DSub(boxes, idx + 1, qpsf, tnq, ans);

    return count;
}

int queenCombination2D(vector<vector< bool>> &boxes, int qpsf, int idx, int tnq, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        //to convert 2-D to 1-D
        int x = i/boxes[0].size();
        int y = i%boxes[0].size();

        count += queenCombination2D(boxes, qpsf + 1,i + 1, tnq - 1,  ans + "(" + to_string (x) + "," + to_string(y) + ") ");
    }
    return count;
}

int queenPermutation2D(vector<vector<bool>> &boxes,int qpsf, int tnq, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < boxes.size()*boxes[0].size(); i++)
    {
        int x = i/boxes[0].size();
        int y = i%boxes[0].size();

        if (!boxes[x][y])
        {
            boxes[x][y] = true;
            count += queenPermutation2D(boxes, qpsf + 1, tnq - 1, ans + "(" + to_string (x) + "," + to_string(y) + ") ");
            boxes[x][y] = false;
        }
    }
    return count;
}

void queenSet()
{
    // vector<bool> boxes(4, false);
    vector<vector<bool>> boxes(4, vector<bool>(4, false));
    // cout<<queenCombination1D(boxes,0,3,0,"");
    // cout<<queenCombination1DSub(boxes,0,3,0,"");
    // cout << queenPermutation1D(boxes, 0, 3, "");
    // cout<<endl<<endl<<endl;
    // cout << queenPermutation1DSub(boxes, 0, 0,3, "");
    // cout << queenCombination2D(boxes, 0, 0, 3, "");
    cout << queenPermutation2D(boxes, 0,3, "");
}

void solve()
{
    queenSet();
}

int main()
{
    solve();
}