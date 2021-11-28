import GraphPackage.*;
import ADTPackage.*;
import java.io.*;
import java.util.Arrays;

public class GraphInterfaceTest {
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    public static void main(String[] args)
    {
        DirectedGraph<String> airMap = new DirectedGraph<String>();
        airMap.addVertex("Boston");
        airMap.addVertex("Provincetown");
        airMap.addVertex("Nantucket");
        airMap.addEdge("Boston", "Provincetown");
        airMap.addEdge("Boston", "Nantucket");

        System.out.println("Labels = " + Arrays.toString(airMap.getLabels()));

        System.out.println("Boston, Princetown, Nantucket");
        System.out.println("Boston -> Provincetown");
        System.out.println("Boston -> Nantucket\n");

        System.out.print("Depth-first Traversal from Boston: <");
        QueueInterface depthFirst = airMap.getDepthFirstTraversal("Boston");
        while(!depthFirst.isEmpty())
            System.out.print(" " + depthFirst.dequeue() + " ");
        System.out.println(">");

        // @author Frank M. Carrano, Timothy M. Henry
        // @version 5.0
        /*
        GraphInterface<String> roadMap = new DirectedGraph<String>();
        roadMap.addVertex("Provincetown");
        roadMap.addVertex("Truro");
        // . . .

        roadMap.addVertex("Falmouth");
        roadMap.addEdge("Provincetown", "Truro", 10);
        // . . .

        roadMap.addEdge("Hyannis", "Falmouth", 20);

        StackInterface<String> bestRoute = new LinkedStack<String>();
        double distance = roadMap.getCheapestPath("Truro", "Falmouth", bestRoute);
        System.out.println("The shortest route from Truro to Falmouth is " + distance + " miles long and " + "passes through the following towns:");
        while (!bestRoute.isEmpty())
            System.out.println(bestRoute.pop());
        */
    }
}
