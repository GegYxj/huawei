package org.example;


import java.util.*;

import static org.example.SystemInit.N;


public class Graph {
    private Map<Integer, Edge> edges;

    class Edge {
        private int start;
        private int end;
        private int key;
        private int count = 40;

        public Edge(int key, int start, int end) {
            this.start = start;
            this.end = end;
            this.key = key;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", key=" + key +
                    ", count=" + count +
                    '}';
        }
    }

    private HashMap<Integer, List<Edge>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public void addEdge(int key, int start, int end) {

        Edge edge = new Edge(key, start, end);
        edges.put(key, edge);
        adjList.computeIfAbsent(start, k -> new LinkedList<>()).add(edge);
        Edge edge1 = new Edge(key, end,start);
        adjList.computeIfAbsent(end, k -> new LinkedList<>()).add(edge1);
    }

    public void removeEdge(Edge edge) {
        adjList.get(edge.start).remove(edge);

    }

    public Edge getEdge(int key) {
        return edges.get(key);
    }

    public HashMap<Integer, List<Edge>> getAdjList() {
        return adjList;
    }

    public void setAdjList(HashMap<Integer, List<Edge>> adjList) {
        this.adjList = adjList;
    }

    // 使用DFS来找出所有从s到t的简单路径，并记录边的编号
    void findAllPathsUtil(int s, int t, boolean visited[], Stack<Integer> path, List<List<Integer>> paths) {
        visited[s] = true;

        // 如果当前节点是目标节点，则打印路径
        if (s == t) {
            List<Integer> pathCopy = new ArrayList<>(path);
            paths.add(pathCopy);
        } else {
            // 递归地探索所有未访问的相邻节点
            for (Edge e : adjList.get(s)) {
                int n = e.end;
                if (!visited[n]) {
                    path.push(e.key); // 将边的编号添加到路径中
                    findAllPathsUtil(n, t, visited, path, paths);
                    path.pop(); // 回溯时移除边的编号
                }
            }
        }

        visited[s] = false;
    }

    List<List<Integer>> findAllPaths(int s, int t) {
        // 标记所有顶点为未访问
        boolean visited[] = new boolean[N + 1];

        // 创建一个栈来存储路径
        Stack<Integer> path = new Stack<>();

        // 存储所有路径的列表
        List<List<Integer>> paths = new ArrayList<>();

        // 调用递归辅助函数来打印所有路径
        findAllPathsUtil(s, t, visited, path, paths);

        // 返回路径列表
        return paths;
    }
}
