import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Stack;

class ShortPath{
    // Visited = the set of nodes whose shortest paths have been found
    // Remaining = Nodes who we dont know the shortest paths for
    // Cost array = distance from shortes path to vertex
    // previousVisited = array of previous Visited arrays

    /*TODO: In the main method, ask the user how they'd like to view their flights
    * AKA based on shortest distance, least number of flights, or cheapest
    * Also, incorporate findShortestPath in the main method
    */

    //Initialize distance and previousVisited
    private final HashMap<Node,Integer> distance = new HashMap<Node,Integer>();
    private final HashMap<Node,Node> prevVis = new HashMap<Node,Node>(); // Format is node we're at, then node we came from
    private final HashSet<Node> visited = new HashSet<Node>();
    private final HashSet<Node> unvisited = new HashSet<Node>();
    private final Stack<Node> stack = new Stack<Node>();

    public void checkNeighbors(Node currentNode, String parameter){
        for (Edge edge :currentNode.edges ) {
            if(!visited.contains(edge.destination)){
              if (parameter.equals("distance")){
                double edgeDistance = edge.dist;
              } else if (parameter.equals("price")){
                double edgeDistance = edge.price;
              } else {
                double edgeDistance = edge.dist; // TODO: Change this to account for least # of flights
              }

                double newDistance = distance.get(currentNode) + edgeDistance;
                if (distance.get(edge.destination) > newDistance) {
                    distance.replace(edge.destination,newDistance);
                    unvisited.add(edge.destination);
                    if (prevVis.contains(edge.destination)){ // If we have to replace which node we came from
                        prevVis.replace(edge.destination, currentNode);
                    } else {
                        prevVis.add(edge.destination, currentNode);
                    }
                }
            }
        }
    }

    public void findShortestPath(Node origin, Node end, String parameter){
        for (Node node :nodes ) {
            distance.add(node,Integer.MAX_VALUE);
        }

        unvisited.add(origin);
        distance.put(origin,0);
        while(!unvisited.isEmpty()){
            Node currentNode = Collections.min(unvisited);
            unvisited.remove(currentNode);
            visited.add(currentNode);
            checkNeighbors(currentNode);
        }

        Node temp = end;
        while (temp != origin){
          stack.push(temp);
          temp = prevVis.get(temp);
        }

        System.out.println("The best path from " + origin.label + " to " + end.label + " is: ");
        while (!stack.isEmpty()){
          System.out.print(stack.pop().label + " --> ");
        }

    }
}
