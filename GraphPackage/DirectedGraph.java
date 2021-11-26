package GraphPackage;
import ADTPackage.*;

public class DirectedGraph<T> implements GraphInterface<T> 
{
    public int getShortestPath(T begin, T end, StackInterface<T> path) 
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public QueueInterface<T> getBreadthFirstTraversal(T origin) 
    {
        // TODO Auto-generated method stub
        return null;
    }

    public QueueInterface<T> getDepthFirstTraversal(T origin) 
    {
        // TODO Auto-generated method stub
        LinkedStack depthStack = new LinkedStack();
        return null;
    }

    public void clear() 
    {
        // TODO Auto-generated method stub
        
    }

    public int getNumberOfVertices() 
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public StackInterface<T> getTopologicalOrder() 
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean addVertex(T vertexLabel) 
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean hasEdge(T begin, T end) 
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isEmpty() 
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean addEdge(T begin, T end) 
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean addEdge(T begin, T end, double edgeWeight) 
    {
        // TODO Auto-generated method stub
        return false;
    }

    public int getNumberOfEdges() 
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getCheapestPath(T begin, T end, StackInterface<T> path) 
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
