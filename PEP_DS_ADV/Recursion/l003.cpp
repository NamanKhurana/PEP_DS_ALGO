#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

unordered_set<string> map = {};

int wordBreakHelper(string str, string ans)
{

    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
        ;
    }

    int count = 0;

    for (int i = 1; i <= str.length(); i++)
    {
        string smallStr = str.substr(0, i);
        if (map.find(smallStr) != map.end())
        {
            count += wordBreakHelper(str.substr(i), ans + smallStr + " ");
        }
    }

    return count;
}

void wordBreak()
{
    vector<string> words{"i", "ilike", "like", "sam", "sung", "samsung", "and", "man", "go", "mango"};
    string str = "ilikesamsungandmango";

    for (string word : words)
    {
        map.insert(word);
    }

    cout << wordBreakHelper(str, "");
}

string str1 = "send";
string str2 = "more";
string str3 = "money";

//maps the letters to their assigned numbers
vector<int> mapping(26, 0);
int numUsed = 0;

int stringToNum(string &str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        res = res * 10 + mapping[str[i] - 'a'];
    }
    // cout<<res<<endl;
    return res;
}

int cryptoHelper(string str, int idx)
{
    if (idx == str.length())
    {
        int a = stringToNum(str1);
        int b = stringToNum(str2);
        int c = stringToNum(str3);

        if ((a + b == c) && mapping[str1[0] - 'a'] != 0 && mapping[str2[0] - 'a'] != 0 && mapping[str3[0] - 'a'] != 0)
        {
            cout << a << endl
                 << "+" << b << endl
                 << "------" << endl
                 << c << endl
                 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    for(int num = 0;num<=9;num++){
        if((numUsed & (1 << num)) == 0){
            numUsed^= (1 << num);
            mapping[str[idx] - 'a'] = num;
            count+=cryptoHelper(str,idx+1);
            numUsed^= (1 << num);
            mapping[str[idx] - 'a'] = 0;
        }
    }

    return count;
}

void crypto()
{
    string str = str1 + str2 + str3;
    // cout<<str<<endl;
    int freq = 0;
    for (int i = 0; i < str.length(); i++)
    {
        int mask = 1 << (str[i] - 'a');
        freq |= mask;
    }
    str = "";
    for (int i = 0; i < 26; i++)
    {
        int mask = 1 << i;
        if ((freq & mask) != 0)
        {
            str += (char)(i + 'a');
        }
    }
    // cout<<str;
    cout<<cryptoHelper(str, 0);
}

//crossword.========================================================
vector<vector<char>> board{{'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};
vector<string> words = {"agra", "norway", "england", "gwalior"};

bool canPlaceWordH(int r, int c, string &word)
{
    if (c == 0 && word.length() < board[0].size())
    {
        if (board[r][c + word.length()] != '+')
            return false;
    }
    else if ((c + word.length()) == board[0].size() && word.length() != board[0].size())
    {
        if (board[r][c - 1] != '+')
            return false;
    }
    else
    {
        if (((c - 1) >= 0 && board[r][c - 1] != '+') || ((c + word.length()) < board[0].size() && board[r][c + word.length()] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((c + i) < board[0].size() && board[r][c + i] != '-' && board[r][c + i] != word[i])
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeWordH(int r, int c, string &word)
{
    vector<bool> mark(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[r][c + i] == '-')
        {
            mark[i] = true;
            board[r][c + i] = word[i];
        }
    }
    return mark;
}

void unPlaceWordH(int r, int c, string &word, vector<bool> &mark)
{
    for (int i = 0; i < word.length(); i++)
    {
        if (mark[i])
            board[r][c + i] = '-';
    }
}

bool canPlaceWordV(int r, int c, string &word)
{
    if (r == 0 && r + word.length() < board.size())
    {
        if (board[r + word.length()][c] != '+')
            return false;
    }
    else if ((r + word.length()) == board.size() && word.length() != board.size())
    {
        if (board[r - 1][c] != '+')
            return false;
    }
    else
    {
        if (((r - 1) >= 0 && board[r - 1][c] != '+') || ((r + word.length()) < board.size() && board[r + word.length()][c] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((r + i) < board.size() && board[r + i][c] != '-' && board[r + i][c] != word[i])
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeWordV(int r, int c, string &word)
{
    vector<bool> mark(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[r + i][c] == '-')
        {
            mark[i] = true;
            board[r + i][c] = word[i];
        }
    }
    return mark;
}

void unPlaceWordV(int r, int c, string &word, vector<bool> &mark)
{
    for (int i = 0; i < word.length(); i++)
    {
        if (mark[i])
            board[r + i][c] = '-';
    }
}

bool crossWord_(int idx)
{
    if (idx == words.size())
    {
        for (vector<char> &ar : board)
        {
            for (char ch : ar)
                cout << ch << " ";
            cout << endl;
        }
        return true;
    }

    string word = words[idx];
    bool res = false;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == '-' || board[i][j] == word[0])
            {
                if (canPlaceWordH(i, j, word))
                {
                    vector<bool> mark = placeWordH(i, j, word);
                    res = res || crossWord_(idx + 1);
                    unPlaceWordH(i, j, word, mark);
                }

                if (canPlaceWordV(i, j, word))
                {
                    vector<bool> mark = placeWordV(i, j, word);
                    res = res || crossWord_(idx + 1);
                    unPlaceWordV(i, j, word, mark);
                }
            }
        }
    }

    return res;
}

void crossWord()
{
    cout << crossWord_(0) << endl;
}

int main()
{
    // wordBreak();
    // crypto();
}