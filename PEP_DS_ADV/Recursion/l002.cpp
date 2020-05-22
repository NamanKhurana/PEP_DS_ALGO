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

int queenCombination2D(vector<vector<bool>> &boxes, int qpsf, int idx, int tnq, string ans)
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
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();

        count += queenCombination2D(boxes, qpsf + 1, i + 1, tnq - 1, ans + "(" + to_string(x) + "," + to_string(y) + ") ");
    }
    return count;
}

int queenPermutation2D(vector<vector<bool>> &boxes, int qpsf, int tnq, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < boxes.size() * boxes[0].size(); i++)
    {
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();

        if (!boxes[x][y])
        {
            boxes[x][y] = true;
            count += queenPermutation2D(boxes, qpsf + 1, tnq - 1, ans + "(" + to_string(x) + "," + to_string(y) + ") ");
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
    cout << queenPermutation2D(boxes, 0, 3, "");
}

//HELPER FUNCTION
bool isSafe(vector<vector<bool>> &boxes, int sr, int sc)
{
    vector<vector<int>> dir = {{0, -1},
                               {-1, -1},
                               {-1, 0},
                               {-1, 1}};

    for (int i = 0; i < dir.size(); i++)
    {
        for (int jump = 1; jump <= boxes.size(); jump++)
        {
            int nr = sr + jump * dir[i][0];
            int nc = sc + jump * dir[i][1];

            if (nr >= 0 && nc >= 0 && nr < boxes.size() && nc < boxes.size())
            {
                if (boxes[nr][nc])
                {
                    return false;
                }
            }
            else
            {
                break;
            }
        }
    }
    return true;
}

//NQUEEN VARIATIONS..................................................................................
int Nqueen_01(vector<vector<bool>> &boxes, int idx, int tnq, string ans)
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
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();

        if (isSafe(boxes, x, y))
        {
            boxes[x][y] = true;
            count += Nqueen_01(boxes, i + 1, tnq - 1, ans + "(" + to_string(x) + "," + to_string(y) + ") ");
            boxes[x][y] = false;
        }
    }
    return count;
}

vector<bool> row(4, false);
vector<bool> col(4, false);
vector<bool> dia(7, false);     //m+n-1 = 7
vector<bool> antiDia(7, false); //m+n-1 = 7

int Nqueen_02(int m, int n, int idx, int tnq, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;

    for (int i = idx; i < m * n; i++)
    {
        int x = i / n;
        int y = i % n;

        if (!row[x] && !col[y] && !dia[x - y + n - 1] && !antiDia[x + y])
        {
            row[x] = true;
            col[y] = true;
            dia[x - y + n - 1] = true;
            antiDia[x + y] = true;

            count += Nqueen_02(m, n, i + 1, tnq - 1, ans + "(" + to_string(x) + "," + to_string(y) + ") ");

            row[x] = false;
            col[y] = false;
            dia[x - y + n - 1] = false;
            antiDia[x + y] = false;
        }
    }

    return count;
}

int rowBit = 0;
int colBit = 0;
int diaBit = 0;
int antiDiaBit = 0;

int Nqueen_03_bits(int m, int n, int idx, int tnq, string ans)
{

    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;

    for (int i = idx; i < m * n; i++)
    {
        int x = i / n;
        int y = i % n;

        if (!(rowBit & (1 << x)) && !(colBit & (1 << y)) && !(diaBit & (1 << (x - y + n - 1))) && !(antiDiaBit & (1 << (x + y))))
        {
            rowBit ^= (1 << x);
            colBit ^= (1 << y);
            diaBit ^= (1 << (x - y + n - 1));
            antiDiaBit ^= (1 << (x + y));

            count += Nqueen_03_bits(m, n, i + 1, tnq - 1, ans + "(" + to_string(x) + "," + to_string(y) + ") ");

            rowBit ^= (1 << x);
            colBit ^= (1 << y);
            diaBit ^= (1 << (x - y + n - 1));
            antiDiaBit ^= (1 << (x + y));
        }
    }

    return count;
}

int Nqueen_03_bits_sub(int m, int n, int idx, int tnq, string ans)
{
    if (idx == m * n || tnq == 0)
    {
        if (tnq == 0)
        {
            return 1;
        }
        return 0;
    }

    int count = 0;

    int x = idx / n;
    int y = idx % n;

    if (!(rowBit & (1 << x)) && !(colBit & (1 << y)) && !(diaBit & (1 << (x - y + n - 1))) && !(antiDiaBit & (1 << (x + y))))
    {
        rowBit ^= (1 << x);
        colBit ^= (1 << y);
        diaBit ^= (1 << (x - y + n - 1));
        antiDiaBit ^= (1 << (x + y));

        count += Nqueen_03_bits_sub(m, n, idx + 1, tnq - 1, ans + "(" + to_string(x) + "," + to_string(y) + ") ");

        rowBit ^= (1 << x);
        colBit ^= (1 << y);
        diaBit ^= (1 << (x - y + n - 1));
        antiDiaBit ^= (1 << (x + y));
    }

    count += Nqueen_03_bits_sub(m, n, idx + 1, tnq, ans);

    return count;
}

int Nqueen_04(int n, int m, int tnq, int r, string ans) // n means houses and m means rooms
{                                                       //qpsf : queen place so far, tnq: total no of queen
    if (r == n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = 0; i < m; i++)
    {
        int x = r;
        int y = i;

        if (!(rowBit & (1 << x)) && !(colBit & (1 << y)) && !(diaBit & (1 << (x - y + m - 1))) && !(antiDiaBit & (1 << (x + y))))
        {
            rowBit ^= (1 << x);
            colBit ^= (1 << y);
            diaBit ^= (1 << (x - y + m - 1));
            antiDiaBit ^= (1 << (x + y));

            count += Nqueen_04(n, m, tnq - 1, r + 1, ans + "(" + to_string(x) + ", " + to_string(y) + ") ");

            rowBit ^= (1 << x);
            colBit ^= (1 << y);
            diaBit ^= (1 << (x - y + m - 1));
            antiDiaBit ^= (1 << (x + y));
        }
    }
    return count;
}

//!if the board size is greater than the queens
int Nqueen_04_generic_sub(int n, int m, int tnq, int r, string ans) // n means houses and m means rooms
{                                                           //qpsf : queen place so far, tnq: total no of queen
    if (r == n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = 0; i < m; i++)
    {
        int x = r;
        int y = i;

        if (!(rowBit & (1 << x)) && !(colBit & (1 << y)) && !(diaBit & (1 << (x - y + m - 1))) && !(antiDiaBit & (1 << (x + y))))
        {
            rowBit ^= (1 << x);
            colBit ^= (1 << y);
            diaBit ^= (1 << (x - y + m - 1));
            antiDiaBit ^= (1 << (x + y));

            count += Nqueen_04_generic_sub(n, m, tnq - 1, r + 1, ans + "(" + to_string(x) + ", " + to_string(y) + ") ");

            rowBit ^= (1 << x);
            colBit ^= (1 << y);
            diaBit ^= (1 << (x - y + m - 1));
            antiDiaBit ^= (1 << (x + y));
        }
    }
    count += Nqueen_04_generic_sub(n, m, tnq, r + 1, ans);
    return count;
}

void Nqueen()
{
    vector<vector<bool>> boxes(4, vector<bool>(4, false));
    // cout<<Nqueen_01(boxes,0,4,"");

    //  int n = 6;
    // int m = 6;
    // rowA.resize(n, false);
    // colA.resize(m, false);
    // diag.resize(n + m - 1, false);
    // adiag.resize(n + m - 1, false);
    // cout<<Nqueen_02(4,4,0,4,"");
    // cout << Nqueen_03_bits(4, 4, 0, 4, "");
    // cout << Nqueen_03_bits_sub(4, 4, 0, 4, "");
    // cout << Nqueen_04(4, 4, 4, 0, "") << endl;
    cout << Nqueen_04_generic_sub(4, 4, 4, 0, "") << endl;
}

int main()
{
    // queenSet();
    Nqueen();
}