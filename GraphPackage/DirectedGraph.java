package GraphPackage;

import ADTPackage.QueueInterface;
import ADTPackage.StackInterface;

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

    private class Node
	{
      private T    data; // Entry in stack
      private Node[] next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node[] linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node[] getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node[] nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
}
