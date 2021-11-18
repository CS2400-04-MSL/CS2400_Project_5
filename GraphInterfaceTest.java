import GraphPackage.*;
import ADTPackage.*;
import java.io.*;

public class GraphInterfaceTest {
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    public static void main(String[] args)
    {
        BasicGraphInterface<String> airMap = new DirectedGraph<String>();
        airMap.addVertex("Boston");
        airMap.addVertex("Provincetown");
        airMap.addVertex("Nantucket");
        airMap.addEdge("Boston", "Provincetown");
        airMap.addEdge("Boston", "Nantucket");

        // @author Frank M. Carrano, Timothy M. Henry
        // @version 5.0
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
    }
}
