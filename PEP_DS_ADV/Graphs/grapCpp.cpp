#include <iostream>
#include <vector>

using namespace std;

class Edge
{
public:
    int v = 0;
    int w = 0;

    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};

int N = 7;
vector<vector<Edge>> graph(N, vector<Edge>());
// vector<vector<Edge*>> graph;

//need to pass gp as a variable in this function
void addEdge(vector<vector<Edge>> &gp, int u, int v, int w)
{
    //copy constructor is fired
    gp[u].push_back(Edge(v, w));
    gp[v].push_back(Edge(u, w));
}

int findEdge(int u, int v)
{
    for (int i = 0; i < graph[u].size(); i++)
    {
        if (graph[u][i].v == v)
        {
            return i;
        }
    }
    return -1;
}

// int findEdge(int v1, int v2)
// {
//     int vtx = -1;
//     for (int i = 0; i < graph[v1].size(); i++)
//     {
//         Edge e = graph[v1][i];
//         if (e.v == v2)
//         {
//             vtx = i;
//             break;
//         }
//     }

//     return vtx;
// }

void removeEdge(int u, int v)
{
    int idx1 = findEdge(u, v);
    int idx2 = findEdge(v, u);

    graph[u].erase(graph[u].begin() + idx1);
    graph[v].erase(graph[v].begin() + idx2);
}

void removeVertex(int vtx)
{

    // while(graph[vertex].size()!=0){
    //     removeEdge(vertex,graph[vertex][graph[vertex].size()-1].v);
    // }

    while (graph[vtx].size() != 0)
    {
        //int vtx2=graph[vtx][graph[vtx].size()-1];

        Edge e = graph[vtx].back();
        removeEdge(vtx, e.v);
    }
}

class allSolutionPair
{
public:
    int lightW = 1e7;
    int heavyW = 0;
    int ceil = 1e7;
    int floor = 0;
};

void allSolution(int src, int dest, vector<bool> &vis, int w, string ans, allSolutionPair &pair, int data)
{
    if (src == dest)
    {
        pair.heavyW = max(pair.heavyW, w);
        pair.lightW = min(pair.lightW, w);

        if (w > data)
            pair.ceil = min(pair.ceil, w);
        if (w < data)
            pair.floor = max(pair.floor, w);
        return;
    }

    vis[src] = true;

    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            allSolution(e.v, dest, vis, w + e.w, ans + to_string(src) + " ", pair, data);
    }

    vis[src] = false;
}

void preOrder(int src, vector<bool> &vis, int w, string ans)
{
    vis[src] = true;
    cout << ans << " @ " << w << endl;
    for (Edge e : graph[src])
        if (!vis[e.v])
            preOrder(e.v, vis, w + e.w, ans + to_string(e.v) + " ");

    vis[src] = false;
}

void display(vector<vector<Edge>> &gp)
{
    for (int i = 0; i < gp.size(); i++)
    {
        cout << i << " -> ";
        for (Edge e : gp[i])
        {
            cout << "(" << e.v << ", " << e.w << "), ";
        }
        cout << endl;
    }
    cout << endl;
}

void hamiltonianPath(int src,int osrc,vector<bool>& vis,int count,string ans){

    if(count == vis.size()-1){
        if(findEdge(src,osrc) == -1){
            cout<<"Path "<<ans + " " + to_string(src)<<endl;
        }else{
            cout<<"Cycle "<<ans + " " + to_string(src)<<endl;
        }
        return;
    }

    vis[src] = true;
    for(Edge e : graph[src]){
        if(!vis[src]){
            hamiltonianPath(e.v,osrc,vis,count+1,ans+ " " + to_string(src));
        }
    }
    vis[src] = false;
}

int GCC_dfs(int src, vector<bool> &vis)
{
    vis[src] = true;
    int count = 0;

    for (Edge e : graph[src])
        if (!vis[e.v])
            count += GCC_dfs(e.v, vis);

    return count + 1;
}

int GCC()
{ //getConnectedComponents
    vector<bool> vis(N, false);
    int count = 0;
    int maxSize = 0;
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
        {
            count++;
            maxSize = max(maxSize, GCC_dfs(i, vis));
        }
    }
    cout << maxSize << endl;
    return count;
}

void constructGraph()
{
    // for(int i = 0;i<N;i++){
    //     vector<Edge*> a;
    //     graph.push_back(a);
    // }

    addEdge(graph, 0, 1, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);
}

int main()
{
    constructGraph();
    display(graph);
    // removeEdge(0,1);
    removeVertex(3);
    display(graph);
    return 0;
}