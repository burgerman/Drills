package cp600assign;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NetworkRapidChange {

    private static String END_LINE = "-1 -1 -1";


    private static int getMSTWeight(MSTEntity mst) {
        int weight = 0;
        if(mst.tree!=null) {
            for (Map.Entry<String, Integer> entry : mst.tree.entrySet()) {
                weight+=entry.getValue();
            }
        }
        return weight;
    }

    private static void getLargestEdgeWeight(MSTEntity mst) {
        int largest=Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : mst.tree.entrySet()) {
            largest = entry.getValue()>largest? entry.getValue():largest;
        }
        mst.setLargestEdge(largest);
    }

    private static MSTEntity getMST(int[][] graph, int V) {
        MSTEntity mst = new MSTEntity(V);
        int edges = 0;
        int u, v, u1, v1;
        String uv, uv1;
        boolean[] visited = new boolean[V];
        visited[0] = true;
        for (int i = 1; i < V; i++) {
            visited[i] = false;
        }
        System.out.println("MST Edges:");
        while (edges < V-1) {
            u =0;
            v=0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < V; i++) {
                if(visited[i] == true) {
                    for(int j = 0; j < V; j++) {
                        if(graph[i][j]!=-1 && visited[j] == false) {
                            if(graph[i][j] < min) {
                                u = i;
                                v = j;
                                min = graph[u][v];
                            }
                        }
                    }
                }
            }
            u1=u+1;
            v1=v+1;
            uv1 = u1<v1? u1+"-"+v1 : v1+"-"+u1;
            System.out.println("edge: "+uv1+"; weight: "+graph[u][v]);

            uv = u<v? u+"-"+v : v+"-"+u;
            mst.tree.put(uv, graph[u][v]);
            visited[v] = true;
            edges++;
        }
        getLargestEdgeWeight(mst);
        return mst;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i =0;
        MSTEntity mst=null;
        int weight, currentWeight;
        String edge, edge2;
        int w;
        int V=0;
        int u, v;
        int[][] arr = new int[1][1];
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if(input.equals(END_LINE)) {
                break;
            } else {
                if(input.length()==1) {
                    V = Integer.parseInt(input);
                    arr = new int[V][V];
                } else if(input.length()>1) {
                    String[] matrix = input.split("\\s");
                    if(matrix.length==V) {
                        for (int j = 0; j < matrix.length; j++) {
                            arr[i][j] = Integer.parseInt(matrix[j]);
                        }
                        if(i==V-1) {
                            mst = getMST(arr, V);
                            weight = getMSTWeight(mst);
                            System.out.println("The total weight of the MST: "+weight);
                        }
                        i++;
                    } else if(matrix.length==3 && mst!=null) {
                        u = Integer.parseInt(matrix[0])-1;
                        v = Integer.parseInt(matrix[1])-1;
                        w = Integer.parseInt(matrix[2]);
                        edge = u<v? u+"-"+v : v+"-"+u;
                        if(mst.tree.containsKey(edge)) {
                            // (u,v) edge in MST
                            if(w<mst.tree.get(edge)) {
                                arr[u][v] = w;
                                arr[v][u] = w;
                                mst.tree.put(edge, w);
                                getLargestEdgeWeight(mst);
                                weight = getMSTWeight(mst);
                                System.out.println("MST weight changes to "+weight);
                            } else if(w==mst.tree.get(edge)) {
                                System.out.println("MST weight does not change");
                            } else {
                                arr[u][v] = w;
                                arr[v][u] = w;
                                currentWeight = getMSTWeight(mst);
                                mst = getMST(arr, V);
                                weight = getMSTWeight(mst);
                                if(currentWeight==weight) {
                                    System.out.println("MST weight does not change");
                                } else{
                                    System.out.println("MST weight changes to "+weight);
                                }
                            }

                        } else {
                            // (u,v) edge not in MST
                            if (w>mst.largestEdge) {
                                arr[u][v] = w;
                                arr[v][u] = w;
                                System.out.println("MST weight does not change");
                            } else if(w!=-1) {
                                arr[u][v] = w;
                                arr[v][u] = w;
                                currentWeight = getMSTWeight(mst);
                                mst = getMST(arr, V);
                                weight = getMSTWeight(mst);
                                if(currentWeight==weight) {
                                    System.out.println("MST weight does not change");
                                } else{
                                    System.out.println("MST weight changes to "+weight);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    static class MSTEntity {
        int V;
        Map<String,Integer> tree;
        int largestEdge;
        public MSTEntity(int V) {
            this.V = V;
            this.tree = new HashMap<>(V-1, 1.0f);
        }

        public void setLargestEdge(int largestEdge) {
            this.largestEdge = largestEdge;
        }
    }
}
