package GraphPackage;
import ADTPackage.*;

public class DirectedGraph<T> implements GraphInterface<T> 
{
	private boolean[][] edges;
	private T[] labels;
	
	public DirectedGraph (int n)
	{
		edges = new boolean [n][n];
		labels = (T[]) new Object[n];
	}
    public DirectedGraph() 
    {
		edges = new boolean [5][5];
		labels = (T[]) new Object[5];
	}
    
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

    public QueueInterface<Vertex> getDepthFirstTraversal(Vertex origin) 
    {
        // TODO Auto-generated method stub
        LinkedStack<Vertex> vertexStack = new LinkedStack<Vertex>();
        LinkedQueue<Vertex> traversalOrder = new LinkedQueue<Vertex>();

        origin.visit();
        traversalOrder.enqueue(origin);
        vertexStack.push(origin);

        Vertex topVertex = null;
        while (!vertexStack.isEmpty())
        {
            topVertex = vertexStack.peek();
            if (topVertex != null && topVertex.getUnvisitedNeighbor() != null)
            {
                Vertex nextNeighbor = (Vertex)topVertex.getUnvisitedNeighbor();
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor);
                vertexStack.push(nextNeighbor);
            }
            else
                vertexStack.pop();
        }
        return traversalOrder;
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
}
