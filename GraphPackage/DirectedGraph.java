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

    public QueueInterface<Vertex> getBreadthFirstTraversal(Vertex origin)
    {
        LinkedQueue<Vertex> traversalOrder = new LinkedQueue<Vertex>();

        origin.visit();
        traversalOrder.enqueue(origin);

        Vertex currentVertex = origin;

        // if no neighbors, return the queue
        if (!currentVertex.hasNeighbor()) {return traversalOrder;}

        while (currentVertex != null)
        {

        }

        return traversalOrder;
    }

    public QueueInterface<T> getBreadthFirstTraversal(T origin) 
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
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
    */

    public QueueInterface<T> getDepthFirstTraversal(T origin) 
    {
        LinkedStack<T> vertexStack = new LinkedStack<T>();
        LinkedQueue<T> traversalOrder = new LinkedQueue<T>();
        boolean[] visited = new boolean[labels.length];

        visit(origin, visited);//visited[getIndex(origin)] = true; //mark as visited
        traversalOrder.enqueue(origin);
        vertexStack.push(origin);

        T topVertex = null;
        while(!vertexStack.isEmpty())
        {
            topVertex = vertexStack.peek();
            if (hasUnvisitedNeighbor(topVertex, visited))
            {
                T nextNeighbor = getUnvisitedNeighbor(topVertex, visited);
                visit(nextNeighbor, visited);//visited[getIndex(nextNeighbor)] = true;
                traversalOrder.enqueue(nextNeighbor);
                vertexStack.push(nextNeighbor);
            }
            else
                vertexStack.pop();
        }
        return traversalOrder;
    }

    private boolean hasUnvisitedNeighbor(T vertex, boolean[] visited)
    {
        if(getUnvisitedNeighbor(vertex, visited) != null)
            return true;
        return false;
    }

    private T getUnvisitedNeighbor(T vertex, boolean[] visited)
    {
        int vertexIndex = getIndex(vertex);
        if (vertexIndex < 0)
            return null;
        
        for (int j = 0; j < edges[vertexIndex].length; j++)
        {
            if (edges[vertexIndex][j] == true && visited[j] == false)
                return labels[j];
        }
        return null;
    }

    private boolean visit(T vertex, boolean[] visited)
    {
        int index = getIndex(vertex);
        if (index != -1)
        {
            visited[index] = true;
            return true;
        }
        return false;
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
        int index = ensureCapacity();
        if (index != -1)
        {
            labels[index] = vertexLabel;
            return true;
        }
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
        int beginIndex = getIndex(begin), endIndex = getIndex(end);
        if (beginIndex != -1 && endIndex != -1)
        {
            edges[beginIndex][endIndex] = true;
            return true;
        }
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

    private int ensureCapacity()
    {
        int index = -1;
        if (labels[labels.length-1] != null) //last index of labels is full
        {
            T temp[] = (T[]) new Object[labels.length*2];
            index = labels.length;
            
            for (int i = 0; i < labels.length; i++)
                temp[i] = labels[i];
            
            labels = temp;
        }
        else //labels is not full
        {
            int i = 0;
            while(labels[i] != null)
                i++;
            index = i;
        }
        return index;
    }

    private int getIndex(T label)
    {
        int i = 0, index = -1;
        while (index == -1 && i < labels.length)
        {
            if (labels[i] != null && labels[i].equals(label))
                index = i;
            i++;
        }

        return index;
    }

    public T[] getLabels()
    {
        return labels;
    }
}
