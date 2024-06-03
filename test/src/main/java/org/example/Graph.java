package org.example;

import java.util.*;

public class Graph {
    class Edge{
        private int start;
        private int end;
        private int key;
        private int count = 40;
        public Edge(int key,int start, int end) {
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
    }
    private Map<Integer, List<Edge>> adjList;
    public Graph(){
        this.adjList = new HashMap<>();
    }
    public void addEdge(int key,int start, int end){
        Edge edge = new Edge(key,start, end);
        adjList.computeIfAbsent(start, k -> new LinkedList<>()).add(edge);
        adjList.computeIfAbsent(end, k -> new LinkedList<>()).add(edge);
    }
}
