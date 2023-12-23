package graph;

import java.util.TreeSet;

/**
 * @author: DoubleW2w
 * @description: 图，最终都是红黑树的实现方式
 * @date: 2023/12/23 21:23
 * @project: hello-java-algo
 */
public class Graph {

    /**
     * 图的顶点数
     */
    private int v;
    /**
     * 图的边个数
     */
    private int e;
    /**
     * 图的矩阵
     */
    private TreeSet<Integer>[] table;

    /**
     * 构造函数
     */
    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
        table = new TreeSet[v];
        for (int i = 0; i < v; i++) {
            table[i] = new TreeSet<>();
        }
    }

    /**
     * 插入
     */
    public void insert(int x, int y) {
        table[x].add(y);
        table[y].add(x);
    }

    /**
     * 是否存在这样的边
     * @param v 顶点
     * @param w 权重
     * @return 是否存在
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return table[v].contains(w);
    }

    /**
     * 顶点V的邻接情况
     * @param v 顶点v
     */
    public TreeSet<Integer> adj(int v) {
        validateVertex(v);
        return table[v];
    }

    public int degree(int v) {
        return adj(v).size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.v) {
            throw new IllegalArgumentException("vertex " + v + " is invalid.");
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("图配置：V = %d, E = %d\n", v, e));
        builder.append("⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛⏛\n");
        for (int i = 0; i < v; i++) {
            builder.append(String.format("%d | ", i));
            for (int w : table[i]) {
                builder.append(String.format("%d | ", w));
            }
            builder.append("\n");
            builder.append("-------------------------------");
            builder.append("\n");
        }
        return builder.toString();
    }
}
