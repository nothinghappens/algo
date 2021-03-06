package L10;

import java.util.Arrays;
import java.util.PriorityQueue;

import L1L2.WeightedQuickUnion;

public class KruskalMST {
  // 1. sorting - ElogE
  // 2. isCircle - E^2(dfs) / logE(WQU)

  private PriorityQueue<Edge> mst;

  public KruskalMST(WeightedGraph G) {
    mst = new PriorityQueue<Edge>();
    WeightedQuickUnion wqu = new WeightedQuickUnion(G.V());
    Edge[] edges = G.edges();
    Arrays.sort(edges);

    for (int i = 0; i < edges.length; i++) {
      Edge e = edges[i];
      int v = e.either();
      int w = e.other(v);
      if (!wqu.isConnected(v, w)) {
        wqu.union(v, w);
        mst.add(e);
      }
    }
  }

  public void printMST() {
    if (!mst.isEmpty()) {
      Edge e = mst.remove();
      int v = e.either();
      int w = e.other(v);
      System.out.print("(" + v + "," + w + "," + e.weight() + ")");
    } else
      return;
    while (!mst.isEmpty()) {
      Edge e = mst.remove();
      int v = e.either();
      int w = e.other(v);
      System.out.print(", (" + v + "," + w + "," + e.weight() + ")");
    }
  }

  public static void main(String[] args) {
    WeightedGraph wg = WeightedGraph.example();
    KruskalMST test = new KruskalMST(wg);
    test.printMST();
  }
}