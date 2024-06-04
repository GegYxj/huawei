package copy;

import java.util.*;

public class SystemInit {
    public static final Scanner in = new Scanner(System.in);
    public static int N; //点的个数
    public static int M;//边的个数
    public static int J;//业务个数
    public static int T;//测试场景个数
    private Map<Integer, ArrayList<Service>> edgeAndService = new HashMap<>();
    private Map<Integer, ArrayList<Service>> edgeAndServiceCopy = new HashMap<>();
    ArrayList maxChangeCount = new ArrayList();
    ArrayList<Service> services = new ArrayList();
    ArrayList<Service> servicesCopy = new ArrayList();
    Graph graph = new Graph();
    Graph graphCopy;

    public SystemInit() {
        N = in.nextInt();
        M = in.nextInt();
        for (int i = 0; i < N; i++) {
            maxChangeCount.add(in.nextInt());//每个点的最大变通数
        }
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

    private void copyInfo() {
        try {
            graphCopy = graph.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        for (Integer key : edgeAndService.keySet()) {
            ArrayList<Service> services1 = edgeAndService.get(key);
            ArrayList<Service> services2 = new ArrayList<>();
            services1.forEach(service -> {
                try {
                    services2.add(service.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            });
            edgeAndServiceCopy.put(key, services2);
        }
        services.forEach(service -> {
            try {
                servicesCopy.add(service.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
    }
//    public void find() {
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                if (i != j) {
//                    List<List<Integer>> allPath = graph.findAllPaths(i, j);
//                    allPaths.put(i+""+j,allPath);
//                }
//            }
//        }
//    }

    public void interactive() {
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            copyInfo();
            int badEdge = in.nextInt();
            List<Integer> badEdges = new ArrayList<>();
            while (badEdge != -1) {
                badEdges.add(badEdge);
                Set<Integer> integers = edgeAndServiceCopy.keySet();
                if (integers.contains(badEdge)) {
                    ArrayList<Service> services1 = edgeAndServiceCopy.get(badEdge);
                    System.out.println(services1.size());
                    Graph.Edge edge1 = graphCopy.getEdge(badEdge);
                    graphCopy.removeEdge(edge1);
                    Collections.sort(services1, new Comparator<Service>() {
                        @Override
                        public int compare(Service s1, Service s2) {
                            return Integer.compare(s2.getValue(), s1.getValue());
                        }
                    });
                    for (Service service : services1) {
                        edgeAndServiceCopy.remove(service.getKey());
                        service.setPath(new ArrayList<>());
                        List<Integer> path = graphCopy.findPath(service.getStart(), service.getEnd());
                        if (Collections.disjoint(badEdges, path)) {
                            service.setPath(path);
                            service.setEdgeNum(path.size());
                        }
                        if (service.getPath().size() == 0) {
                            System.out.println(0);
                            continue;
                        }
                        System.out.println(service.getKey() + " " + service.getPath().size());
                        StringBuffer s = new StringBuffer();
                        int size = 0;
                        for (Integer edge : service.getPath()) {
                            edgeAndServiceCopy.computeIfAbsent(edge, k -> new ArrayList<>()).add(service);
                            s.append(edge);
                            s.append(" ");
                            s.append(service.getChannelLeft());
                            s.append(" ");
                            s.append(service.getChannelRight());
                            if (size < service.getPath().size()) {
                                s.append(" ");
                            }
                            size++;
                        }
                        System.out.println(s);
                    }
                } else
                    System.out.println(0);
                System.out.flush();
                badEdge = in.nextInt();
            }
        }
    }
}
