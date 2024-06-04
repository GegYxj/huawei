package copy;

import java.util.*;


public class Graph implements Cloneable{
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

    public static HashMap<Integer, List<Edge>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
        this.edges = new HashMap<>();
    }

    @Override
    protected Graph clone() throws CloneNotSupportedException {
        return (Graph) super.clone();
    }

    public void addEdge(int key, int start, int end) {

        Edge edge = new Edge(key, start, end);
        edges.put(key, edge);
        adjList.computeIfAbsent(start, k -> new LinkedList<>()).add(edge);
        Edge edge1 = new Edge(key, end, start);
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
    public static List<Integer> findPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> prev = new HashMap<>(); // 记录每个节点的前驱节点

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            int currentNode = stack.pop();
            path.add(0, currentNode); // 将节点添加到路径的开头

            if (currentNode == end) {
                return reconstructPath(prev, start, end);
            }

            for (Edge edge : adjList.get(currentNode)) {
                int neighbor = edge.end;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                    prev.put(neighbor, currentNode); // 记录前驱节点
                }
            }
        }

        return null; // 没有找到路径
    }

    private static List<Integer> reconstructPath(Map<Integer, Integer> prev, int start, int end) {
        List<Integer> path = new ArrayList<>();
        List<Integer> edgePath = new ArrayList<>();
        int node = end;
        while (node != start) {
            path.add(0, node);
            node = prev.get(node);
        }
        path.add(0, start);
        for (int i = 0; i < path.size() - 1; i++) {
            int source = path.get(i);
            int destination = path.get(i + 1);
            for (Edge edge : adjList.get(source)) {
                if ((edge.start == source && edge.end == destination) || (edge.start == destination && edge.end == source)) {
                    edgePath.add(edge.key);
                }
            }
        }
        return edgePath;
    }
}
