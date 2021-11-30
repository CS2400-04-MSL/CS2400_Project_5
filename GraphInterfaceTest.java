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
        airMap.addVertex("A");
        airMap.addVertex("B");
        airMap.addVertex("C");
        airMap.addVertex("D");
        airMap.addVertex("E");
        airMap.addVertex("F");
        airMap.addVertex("G");
        airMap.addVertex("H");
        airMap.addVertex("I");

        airMap.addEdge("A", "B");
        airMap.addEdge("A", "D");
        airMap.addEdge("A", "E");
        airMap.addEdge("B", "E");
        airMap.addEdge("C", "B");
        airMap.addEdge("D", "G");
        airMap.addEdge("E", "F");
        airMap.addEdge("E", "H");
        airMap.addEdge("F", "C");
        airMap.addEdge("F", "H");
        airMap.addEdge("G", "H");
        airMap.addEdge("H", "I");
        airMap.addEdge("I", "F");

        System.out.println("Labels = " + Arrays.toString(airMap.getLabels()));

        System.out.print("Breadth-first Traversal from A: <");
        QueueInterface breadthFirst = airMap.getBreadthFirstTraversal("A");
        while(!breadthFirst.isEmpty())
            System.out.print(" " + breadthFirst.dequeue() + " ");
        System.out.println(">");

        System.out.print("Depth-first Traversal from A: <");
        QueueInterface depthFirst = airMap.getDepthFirstTraversal("A");
        while(!depthFirst.isEmpty())
            System.out.print(" " + depthFirst.dequeue() + " ");
        System.out.println(">");
    }
}
