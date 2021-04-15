import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
class djistrka2{

public  static void  findSPbyDist(Graph.Node start){
    start.dist = 0;
    HashSet<Graph.Node> S = new HashSet<Graph.Node>();
    PriorityQueue<Graph.Node> Q = new PriorityQueue<Graph.Node>();
    for (Graph.Node node :Graph.nodes.values() ) {
        Q.add(node);
    }
    while(Q.size()>0){
        Graph.Node u = Q.poll();
        S.add(u);
        for (Graph.Edge edge : u.edges ) {
            if(edge.destination.dist > u.dist + edge.dist){
                edge.destination.dist = u.dist + edge.dist;
                edge.destination.prev = u;
            }
        }
    }
}
public static void findSPbyPrice(Graph.Node start){
    start.dist = 0;
    HashSet<Graph.Node> S = new HashSet<Graph.Node>();
    PriorityQueue<Graph.Node> Q = new PriorityQueue<Graph.Node>();
    for (Graph.Node node :Graph.nodes.values() ) {
        Q.add(node);
    }
    while(Q.size()>0){
        Graph.Node u = Q.poll();
        S.add(u);
        for (Graph.Edge edge : u.edges ) {
            if(edge.destination.dist > u.dist + edge.price){
                edge.destination.dist = u.dist + edge.price;
                edge.destination.prev = u;
            }
        }
    }
}
public  static void printSP(Graph.Node target,Graph.Node origin){
    Stack<Graph.Node> stack = new Stack<Graph.Node>();
    Graph.Node temp = target;
    while (temp != null){
      stack.push(temp);
      temp = temp.prev;
    }
    System.out.print( origin.label  );
    while (!stack.isEmpty()){
      System.out.print(" --> " + stack.pop().label  );
    }
    System.out.println("\n");
}
}
