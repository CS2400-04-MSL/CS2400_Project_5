package GraphPackage;
import ADTPackage.*;

import java.util.Iterator;

public class DirectedGraph<T> implements GraphInterface<T> 
{
	private boolean[][] edges;
	private T[] labels;
	private Vertex[] vertices;
	
	public DirectedGraph (int n)
	{
		edges = new boolean [n][n];
		labels = (T[]) new Object[n];
        vertices = new Vertex[n];
	}
    public DirectedGraph() 
    {
		edges = new boolean [5][5];
		labels = (T[]) new Object[5];
        vertices = new Vertex[5];
	}

    /** Gets the Breadth-first traversal of the graph starting from a certain node and implementing Vertex class
     @return  a LinkedQueue containing the traversal order. */
    public QueueInterface<Vertex> getBreadthFirstTraversal(Vertex origin)
    {
        LinkedQueue<Vertex> traversalQueue = new LinkedQueue<Vertex>();
        LinkedQueue<Vertex> returnQueue = new LinkedQueue<Vertex>();

        // add origin vertex to the traversal queue
        origin.visit();
        traversalQueue.enqueue(origin);

        //
        while (!traversalQueue.isEmpty()) //check if the traversal queue is not empty
        {
            //pop the first item from queue and add to returnQueue
            Vertex currentVertex = traversalQueue.dequeue();
            returnQueue.enqueue(currentVertex);

            //create neighbor iterator
            Iterator<Vertex> neighborIterator = currentVertex.getNeighborIterator();

            //iterate through neighbors. add unvisited vertices to the traversalQueue and mark as visited.
            while (neighborIterator.hasNext())
            {
                Vertex neighborVertex = neighborIterator.next();

                if (!neighborVertex.isVisited())
                {
                    //add neighborVertex to traversal queue and mark as visited
                    traversalQueue.enqueue(neighborVertex);
                    neighborVertex.visit();
                }
            }
        }
        return returnQueue;
    }

    /** Implements the getBreadthFirstTraversal using vertex method.
     * @param origin  An object that labels the origin vertex of the traversal.
     * @return
     */
    public QueueInterface<T> getBreadthFirstTraversal(T origin) 
    {
        int originIndex = getIndex(origin);
        QueueInterface<Vertex> vertexQueue = getBreadthFirstTraversal(vertices[originIndex]);

        //vertexQueue.dequeue();

        LinkedQueue<T> returnQueue = new LinkedQueue<T>();

        while (!vertexQueue.isEmpty())
        {
            Vertex currentVertex = vertexQueue.dequeue();
            Object vertexLabel = currentVertex.getLabel();
            returnQueue.enqueue((T) vertexLabel);
        }

        return returnQueue;
    }

    /** Gets the Depth-first traversal of the graph starting from a certain node
      @return  a LinkedQueue containing the traversal order. */
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
    
    /** Determines if a certain node has any neighbors that are unvisited
      @return  true if has an unvisited neighbor, otherwise false. */
    private boolean hasUnvisitedNeighbor(T vertex, boolean[] visited)
    {
        if(getUnvisitedNeighbor(vertex, visited) != null)
            return true;
        return false;
    }

    /** Gets an unvisited neighbor belonging to a certain node, if any
      @return  T unvisited neighbor. */
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

    /** Updates a given boolean array of visited nodes, marking the given node as visited/true
      @return  true if successful. */
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
    
    /** Adds a vertex with a given label to the graph
      @return  true if successful. */
    public boolean addVertex(T vertexLabel)
    {
        int index = ensureCapacity();
        if (index != -1)
        {
            labels[index] = vertexLabel;
            vertices[index] = new Vertex(vertexLabel);
            return true;
        }
        return false;
    }

    /** Adds an edge between two vertices, begin pointing to end
      @return  true if successful. */
    public boolean addEdge(T begin, T end) 
    {
        ensureCapacity();

        int beginIndex = getIndex(begin), endIndex = getIndex(end);
        if (beginIndex != -1 && endIndex != -1)
        {
            edges[beginIndex][endIndex] = true;
            vertices[beginIndex].connect(vertices[endIndex]);
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
    
    /** Ensures that labels[], edges[][], and vertices[] aren't full
      @return  index of the first null value of labels[]. */
    private int ensureCapacity()
    {
        int index = -1;
        if (labels[labels.length-1] != null) //last index of labels is full
        {
            T temp[] = (T[]) new Object[labels.length*2];
            Vertex temp2[] = new Vertex[labels.length*2];

            index = labels.length;

            // copy contents of both labels[] and vertices[] to temp arrays
            for (int i = 0; i < labels.length; i++)
            {
                temp[i] = labels[i];
                temp2[i] = vertices[i];
            }
            
            labels = temp;
            vertices = temp2;

            boolean tempEdge[][] = new boolean[edges.length*2][edges.length*2];
            for (int i = 0; i < edges.length; i++)
            {
                for (int j = 0; j < edges[i].length; j++)
                    tempEdge[i][j] = edges[i][j];
            }
            edges = tempEdge;
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

    /** Gets the index of a label in labels[]
      @return  int index of label in labels[]. */
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

    /** Gets array of labels
      @return  labels[]. */
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
