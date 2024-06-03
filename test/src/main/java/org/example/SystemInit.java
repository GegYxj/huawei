package org.example;

import java.util.*;

public class SystemInit {
    public static final Scanner in = new Scanner(System.in);
    public static int N; //点的个数
    public static int M;//边的个数
    public static int J;//业务个数
    public static int T;//测试场景个数
    private Map<Integer, ArrayList<Service>> edgeAndService = new HashMap<>();
    ArrayList maxChangeCount = new ArrayList();
    ArrayList<Service> services = new ArrayList();

    public SystemInit() {
        N = in.nextInt();
        M = in.nextInt();
        for (int i = 0; i < N; i++) {
            maxChangeCount.add(in.nextInt());//每个点的最大变通数
        }
        Graph graph = new Graph();
        for (int i = 0; i < M; i++) {//对边的信息进行保存
            int i1 = in.nextInt();
            int i2 = in.nextInt();
            graph.addEdge(i + 1, i1, i2);
        }
        J = in.nextInt();
        for (int i = 0; i < J; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int edgeNums = in.nextInt();
            int channelLeft = in.nextInt();
            int channelRight = in.nextInt();
            int value = in.nextInt();
            Service service = new Service(i + 1, start, end, edgeNums, channelLeft, channelRight, value);
            ArrayList path = new ArrayList();
            for (int j = 0; j < edgeNums; j++) {
                int edge = in.nextInt();
                path.add(edge);
                edgeAndService.computeIfAbsent(edge, k -> new ArrayList<>()).add(service);
            }
            service.setPath(path);
            services.add(service);
        }
    }

    public void interactive() {
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int badEdge = in.nextInt();
            while (badEdge != -1) {
                ArrayList<Service> services1 = edgeAndService.get(badEdge);
                Collections.sort(services1, new Comparator<Service>() {
                    @Override
                    public int compare(Service s1, Service s2) {
                        return Integer.compare(s2.getValue(),s1.getValue());
                    }
                });
                for (int j = 0; j < services1.size(); j++) {
                    System.out.println(services1.get(j));
                }
                System.out.flush();
                badEdge = in.nextInt();
            }
        }
    }

    public static void sort(ArrayList<Service> services) {

    }
}
