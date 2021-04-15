import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.HashSet;
import java.util.Collections;
import java.util.PriorityQueue;


public class Graph{
    public static HashMap<String,Node> nodes;
    public Graph(){
        this.nodes=new HashMap<String,Node>();
    }
    public class Node implements Comparable<Node>{
        public String label;
        public ArrayList<Edge> edges;
        int dist = Integer.MAX_VALUE;
        Node prev = null;
        int minPath;
        public Node(String label){
            this.label = label;
            this.edges = new ArrayList<Edge>();
        }
        private void addEdge(Edge e){
            this.edges.add(e);
        }
       public int compareTo(Node n){
            if(this.dist>n.dist){
                return 1;
            }
            if(this.dist<n.dist){
                return -1;
            }

                return 0;

        }
    }
    public class Edge{
        public int dist;
        public int price;
        public Node source;
        public Node destination;
        public Edge(int dist, int price,Node destination,Node source){
            this.dist = dist;
            this.price = price;
            this.destination = destination;
            this.source = source;
        }

    }

    public Node addNode(String label){
        Node node = new Node(label);
        nodes.put(label,node);
        return node;
    }
    public void addEdge(Node source, Node destination,int distance,int price){
        Edge edge = new Edge(distance,price,destination,source);
        source.addEdge(edge);
    }
    public void printG(){
        for (Node node :nodes.values() ) {
            System.out.println("Route");
            System.out.println("Origin: "+ node.label);
            for (Edge edge :node.edges ) {
                System.out.println("destination: "+edge.destination.label);
                System.out.println("Price:"+edge.price+" Distance: "+edge.dist);
            }
        }
    }

    /**
     * Prim's Algorithm for Nodes in Graph
     *
     * @author T
     */
    public void primsAlgo(Node starting){
        starting.dist =0;
        PriorityQueue<Node> Q = new PriorityQueue<Node>();
        ArrayList<Node> finalProduct = new ArrayList<Node>();
        for (Graph.Node node :Graph.nodes.values() ) {
            Q.add(node);
        }
        while(Q.size()>0){
            Node u = Q.poll();
            for (Edge edge :u.edges ) {
                if(Q.contains(edge.destination) &&  edge.dist < edge.destination.dist){
                    edge.destination.prev = u;
                    edge.destination.dist = edge.dist;
                }
            }
        }
        for (Node node :nodes.values() ) {
            if(node.prev!=null){
                System.out.println(node.label+" "+ node.prev.label);
            }
        }
        //printSP(starting);
    }
    public  static void  findSPbyDist(Node start, String endlabel){
        start.dist = 0;
        start.prev= null;
        HashSet<Node> S = new HashSet<Node>();
        Stack<Node> stack = new Stack<Node>();
        PriorityQueue<Node> Q = new PriorityQueue<Node>();
        for (Graph.Node node :Graph.nodes.values() ) {
            Q.add(node);
        }
        while(Q.size()>0){
            Node u = Q.poll();
            S.add(u);
            for (Edge edge : u.edges ) {
                if(edge.destination.dist > u.dist + edge.dist){
                    edge.destination.dist = u.dist + edge.dist;
                    edge.destination.prev = u;
                }
            }
        }
        for (Node node :nodes.values() ) {
            //System.out.println(node.label+node.dist);
            if(node.label.equals(endlabel)){
            Node prevt = node;
            do{

                if(prevt!=null){
                    stack.push(prevt);
                    prevt = prevt.prev;
                }
            }while(prevt!=null);
            }
        }
        while (!stack.isEmpty()){
          System.out.print(  stack.pop().label+" --> "  );
        }
        System.out.println("\n");
        System.out.println("");
}
public  static void  findSPbyPrice(Node start, String endlabel){
    start.dist = 0;
    start.prev= null;
    HashSet<Node> S = new HashSet<Node>();
    Stack<Node> stack = new Stack<Node>();
    PriorityQueue<Node> Q = new PriorityQueue<Node>();
    for (Graph.Node node :Graph.nodes.values() ) {
        Q.add(node);
    }
    while(Q.size()>0){
        Node u = Q.poll();
        S.add(u);
        for (Edge edge : u.edges ) {
            if(edge.destination.dist > u.dist + edge.price){
                edge.destination.dist = u.dist + edge.price;
                edge.destination.prev = u;
            }
        }
    }
    for (Node node :nodes.values() ) {
        //System.out.println(node.label+node.dist);
        if(node.label.equals(endlabel)){
        Node prevt = node;
        do{

            if(prevt!=null){
                stack.push(prevt);
                prevt = prevt.prev;
            }
        }while(prevt!=null);
        }
    }
    while (!stack.isEmpty()){
      System.out.print(  stack.pop().label+" --> "  );
    }
    System.out.println("\n");
    System.out.println("");
}
    public  static void printSP(Node target){
        Stack<Node> stack = new Stack<Node>();
        Node temp = target;
        while (temp != null){
          stack.push(temp);
          temp = temp.prev;
        }

        while (!stack.isEmpty()){
          System.out.print(" --> " + stack.pop().label  );
        }
        System.out.println("\n");
    }











    public static void main(String[] args) {
        Graph test = new Graph();
        File routes = new File("testd.txt");
        try{
            Scanner scan = new Scanner(routes);
            while (scan.hasNext()) {
                String text = scan.nextLine();
                String[] holder = text.split(",");
                boolean hasholder1= true;
                boolean hasholder2=true;
                Node q = null;
                Node r = null;
                for (Node node :test.nodes.values() ) {
                    if(node.label.equals(holder[1])){
                        hasholder1 = false;
                        q=node;
                    }
                    if(node.label.equals(holder[2])){
                        hasholder2 = false;
                        r= node;
                    }
                }
                if(hasholder1){
                    q = test.addNode(holder[1]);
                }
                if(hasholder2){
                    r = test.addNode(holder[2]);
                }
                test.addEdge(q,r,Integer.parseInt(holder[3]),Integer.parseInt(holder[4]));
                test.addEdge(r,q,Integer.parseInt(holder[3]),Integer.parseInt(holder[4]));
            }
        }
        catch (FileNotFoundException e) {
             System.out.println("file not found");
        }

        System.out.println("This are a list of possible airports");
        for (String name :nodes.keySet() ) {
            System.out.println(name);
        }
        System.out.println("Below are all of the possible routes");
        test.printG();
        System.out.println("This is a MST of all possible routes starting at LAX");
        //System.out.println("Cost of trip from LAX to" + test.nodes.get("LAG").prev.label);
        //test.primsAlgo(test.nodes.get("LAX"));
        test.findSPbyDist(test.nodes.get("LAX"),"CAL");
        //test.findSPbyPrice(test.nodes.get("DTW"),"LAX");
        //djistrka2.findSPbyDist();
       // System.out.println("Cost of trip from LAX to" + test.nodes.get("LAG").prev.label);
        //djistrka2.findSPbyPrice(test.nodes.get("LAX"));
        //djistrka2.printSP(test.nodes.get("LAX"),test.nodes.get("SAN"));
       // System.out.println("Cost of trip from LAX to" + test.nodes.get("SAN").dist);
    }
}
