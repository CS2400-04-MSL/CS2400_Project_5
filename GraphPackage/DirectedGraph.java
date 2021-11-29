package GraphPackage;
import ADTPackage.*;

import java.util.Iterator;

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

    public QueueInterface<VertexInterface<T>> getBreadthFirstTraversal(VertexInterface<T> origin)
    {
        LinkedQueue<VertexInterface<T>> traversalQueue = new LinkedQueue<VertexInterface<T>>();
        LinkedQueue<VertexInterface<T>> returnQueue = new LinkedQueue<VertexInterface<T>>();

        // add origin vertex to the traversal queue
        origin.visit();
        traversalQueue.enqueue(origin);

        //
        while (traversalQueue.getFront() != null) //check if the traversal queue is not empty
        {
            //pop the first item from queue and add to returnQueue
            VertexInterface<T> currentVertex = traversalQueue.dequeue();
            returnQueue.enqueue(currentVertex);

            //create neighbor iterator
            Iterator<VertexInterface<T>> neighborIterator = currentVertex.getNeighborIterator();

            //iterate through neighbors. add unvisited vertices to the traversalQueue and mark as visited.
            while (neighborIterator.hasNext())
            {
                VertexInterface<T> neighborVertex = neighborIterator.next();

                if (neighborVertex.isVisited())
                {
                    //add neighborVertex to traversal queue and mark as visited
                    traversalQueue.enqueue(neighborVertex);
                    neighborVertex.visit();
                }
            }
        }
        return returnQueue;
    }


    public QueueInterface<T> getBreadthFirstTraversal(T origin) 
    {
        //LinkedQueue<T> traversalOrder = new LinkedQueue<T>();

        return null;
    }



    /*
    public QueueInterface<Vertex> getDepthFirstTraversal(Vertex origin) 
    {
        // 
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

    public int[] neighbors(int vertex)
    {
    	int i;
    	int count = 0;
    	int[] answer;
    	
    	for (i=0; i < labels.length; i++)
    	{
    		if(edges[vertex][i])
    			count++;
    	}
    	
    	answer = new int[count];
    	count = 0;
    	for (i=0; i < labels.length; i++)
    	{
    		if(edges[vertex][i])
    			answer[count++] = i;
    	}
    	
    	return answer;
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

    public boolean isEdge(int source, int target)
    {
    	return edges[source][target];
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

    public boolean addEdge(T begin, T end) 
    {
        // connect vertices using connect function



        int beginIndex = getIndex(begin), endIndex = getIndex(end);
        if (beginIndex != -1 && endIndex != -1)
        {
            edges[beginIndex][endIndex] = true;
            return true;
        }
        return false;
    }

    public void removeEdge(int source, int target)
    {
    	edges[source][target] = false;
    }
    
    public void setLabel(int vertex, T newLabel)
    {
    	labels[vertex] = newLabel;
    }
    
    public int size()
    {
    	return labels.length;
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
    
    //ignore everything under this comment
	@Override
	public boolean addEdge(T begin, T end, double edgeWeight) {
		// 
		return false;
	}
	@Override
	public boolean hasEdge(T begin, T end) {
		// 
		return false;
	}
	@Override
	public boolean isEmpty() {
		// 
		return false;
	}
	@Override
	public int getNumberOfVertices() {
		// 
		return 0;
	}
	@Override
	public int getNumberOfEdges() {
		// 
		return 0;
	}
	@Override
	public void clear() {
		// 
		
	}
	@Override
	public StackInterface<T> getTopologicalOrder() {
		// 
		return null;
	}
	@Override
	public int getShortestPath(T begin, T end, StackInterface<T> path) {
		// 
		return 0;
	}
	@Override
	public double getCheapestPath(T begin, T end, StackInterface<T> path) {
		// 
		return 0;
	}
}
